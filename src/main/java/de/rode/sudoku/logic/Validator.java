package de.rode.sudoku.logic;

import de.rode.sudoku.dto.Sudoku;
import de.rode.sudoku.dto.SudokuValidateError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Component
public class Validator {

    public boolean columnLegit(Sudoku sudoku, int columnNumber) {
        Set<Integer> numbers = new HashSet<>();
        for (int i = 0; i < 9; i++) {
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
        for (int i = 0; i < 9; i++) {
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
        for (int i = 0; i < 9; i++) {
            if (fields[i].length != 9) {
                return false;
            }
        }
        return true;
    }

    public Optional<SudokuValidateError> validate(final Sudoku sudoku) {

        if (!sizeLegit(sudoku)) {
            return Optional.of(SudokuValidateError.builder()
                    .timestamp(ZonedDateTime.now())
                    .status(400)
                    .error("Bad Request")
                    .message("Groesse vom Sudoku entspricht nicht den Vorschriften.")
                    .path("/sudoku/validate")
                    .build());
        }

        for (int i = 0; i < 9; i++) {
            if (!rowLegit(sudoku, i)) {
                return Optional.of(SudokuValidateError.builder()
                        .timestamp(ZonedDateTime.now())
                        .status(400)
                        .error("Bad Request")
                        .message(String.format("Zeile %s entspricht nicht den Vorschriften.", i))
                        .path("/sudoku/validate")
                        .build());
            } else if (!columnLegit(sudoku, i)) {
                return Optional.of(SudokuValidateError.builder()
                        .timestamp(ZonedDateTime.now())
                        .status(400)
                        .error("Bad Request")
                        .message(String.format("Spalte %s entspricht nicht den Vorschriften.", i))
                        .path("/sudoku/validate")
                        .build());
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!squareLegit(sudoku, i, j)) {
                    return Optional.of(SudokuValidateError.builder()
                            .timestamp(ZonedDateTime.now())
                            .status(400)
                            .error("Bad Request")
                            .message(String.format("Quadrat (%s|%s) entspricht nicht den Vorschriften.", i, j))
                            .path("/sudoku/validate")
                            .build());
                }
            }
        }

        return Optional.empty();
    }
}
