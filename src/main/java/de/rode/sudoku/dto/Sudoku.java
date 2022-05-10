package de.rode.sudoku.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sudoku {

    @Builder.Default
    private int[][] fields = new int[9][9];

    private String string;

}
