package de.rode.sudoku.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SudokuValidateError {

    private ZonedDateTime timestamp;

    private int status;

    private String error;

    private String message;

    private String path;

}
