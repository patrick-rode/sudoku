package de.rode.sudoku.logic;

import de.rode.sudoku.dto.Sudoku;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Parser {

    public Sudoku parseFromString(final String string) {

        int[][] fields = new int[9][9];

        for (int j = 0; j < 9; j++) {
            for (int i = 0; i < 9; i++) {
                int start = j * 9 + i;
                int number = Integer.parseInt(string.substring(start, start + 1));
                fields[i][j] = number;
            }
        }
        return new Sudoku(fields, string);
    }

}
