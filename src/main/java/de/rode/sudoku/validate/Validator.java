package de.rode.sudoku.validate;

import de.rode.sudoku.dto.Sudoku;
import de.rode.sudoku.dto.ValidationResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class Validator {

    public boolean columnLegit(Sudoku sudoku, int columnNumber) {
        Set<Integer> numbers = new HashSet<>();
        for (int i=0; i<9; i++) {
            int number = sudoku.getFields()[i][columnNumber];
            if (numbers.contains(number)) {
                return false;
            }
            numbers.add(number);
        }
        return true;
    }

    public boolean rowLegit(Sudoku sudoku, int rowNumber) {
        Set<Integer> numbers = new HashSet<>();
        for (int i=0; i<9; i++) {
            int number = sudoku.getFields()[rowNumber][i];
            if (numbers.contains(number)) {
                return false;
            }
            numbers.add(number);
        }
        return true;
    }

    public boolean squareLegit(Sudoku sudoku, int columnNumber, int rowNumber) {
        return false;
    }

    public boolean sizeLegit(Sudoku sudoku) {
        int[][] fields = sudoku.getFields();
        if (fields.length != 9) {
            return false;
        }
        for (int i=0; i<9; i++) {
            if (fields[i].length != 9) {
                return false;
            }
        }
        return true;
    }

    public ValidationResult validate(Sudoku sudoku) {

        if (!sizeLegit(sudoku)) {
            log.info("Groesse vom Sudoku entspricht nicht den Vorschriften.");
            return ValidationResult.builder()
                    .legit(false)
                    .build();
        }

        for (int i=0; i<9; i++) {
            if (!rowLegit(sudoku, i)) {
                log.info("Zeile {} entspricht nicht den Vorschriften.", i);
                return ValidationResult.builder()
                        .legit(false)
                        .build();
            } else if (!columnLegit(sudoku, i)) {
                log.info("Spalte {} entspricht nicht den Vorschriften.", i);
                return ValidationResult.builder()
                        .legit(false)
                        .build();
            }
        }

        for (int i=0; i<3; i++) {
            for (int j=0; j<3; j++) {
                if (!squareLegit(sudoku, i,j)) {
                    log.info("Quadrat ({}|{}) entspricht nicht den Vorschriften.", i, j);
                    return ValidationResult.builder()
                            .legit(false)
                            .build();
                }
            }
        }

        return ValidationResult.builder()
                .legit(true)
                .build();
    }
}
