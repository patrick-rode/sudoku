package de.rode.sudoku.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationResult {

    private Boolean legit;

}
