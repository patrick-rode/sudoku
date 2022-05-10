package de.rode.sudoku;

import de.rode.sudoku.dto.Solution;
import de.rode.sudoku.dto.Sudoku;
import de.rode.sudoku.dto.ValidationResult;
import de.rode.sudoku.dto.Visualation;

public interface SudokuApi {

    ValidationResult validate(Sudoku sudoku);

    Visualation visualize(Sudoku sudoku);

    Solution solve(Sudoku sudoku);

}
