package de.rode.sudoku.parse;

import de.rode.sudoku.dto.Sudoku;
import de.rode.sudoku.dto.SudokuValidateError;
import io.vavr.control.Either;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Slf4j
@Component
public class Parser {

    public Either<SudokuValidateError, Sudoku> parseFromString(final String string) {

        int[][] fields = new int[9][9];

        if (string.length() != 81) {
            return Either.left(SudokuValidateError.builder()
                    .timestamp(ZonedDateTime.now())
                    .status(400)
                    .error("Bad Request")
                    .message("Sudoku war nicht genau 81 Zeichen lang.")
                    .path("/sudoku/validate")
                    .build());
        }

        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                int start = j * 9 + i;
                try {
                    int number = Integer.parseInt(string.substring(start, start + 1));
                    fields[i][j] = number;
                } catch (Exception e) {
                    return Either.left(SudokuValidateError.builder()
                            .timestamp(ZonedDateTime.now())
                            .status(400)
                            .error("Bad Request")
                            .message("Sudoku hatte nicht nur Nummern in sich.")
                            .path("/sudoku/validate")
                            .build());
                }
            }
        }
        return Either.right(new Sudoku(fields, string));
    }

}
