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
            for (int j = 0; j < 9; j++) {
            int number = sudoku.getFields()[j][columnNumber];
            if (number == 0) {
                continue; // 0 = leeres Feld
            }
            if (numbers.contains(number)) {
                return false; // Zahl kam 2x vor
            }
            numbers.add(number);
        }
        return true;
    }

    public boolean rowLegit(Sudoku sudoku, int rowNumber) {
        Set<Integer> numbers = new HashSet<>();
            for (int i = 0; i < 9; i++) {
            int number = sudoku.getFields()[rowNumber][i];
            if (number == 0) {
                continue; // 0 = leeres Feld
            }
            if (numbers.contains(number)) {
                return false; // Zahl kam 2x vor
            }
            numbers.add(number);
        }
        return true;
    }

    // pruefe ob (column, row) in einem validen Quadrat ist
    public boolean squareLegit(Sudoku sudoku, int column, int row) {
        Set<Integer> numbers = new HashSet<>();
        int imin = column < 3 ? 0 : (column < 6 ? 3 : 6);
        int imax = imin + 3;
        int jmin = row < 3 ? 0 : (row < 6 ? 3 : 6);
        int jmax = jmin + 3;
        for (int j = jmin; j < jmax; j++) {
            for (int i = imin; i < imax; i++) {
                int number = sudoku.getFields()[j][i];
                if (number == 0) {
                    continue; // 0 = leeres Feld
                }
                if (numbers.contains(number)) {
                    return false; // Zahl kam 2x vor
                }
                numbers.add(number);
            }
        }

        return true;
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

    // empty wenn validierung erfolgreich
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

        // pruefe, ob (0,0), (0,3), ... (3,6), (6,6) etc in validen Quadraten sind
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (!squareLegit(sudoku, i * 3, j * 3)) {
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
