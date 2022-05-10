package de.rode.sudoku;

import de.rode.sudoku.dto.Solution;
import de.rode.sudoku.dto.Sudoku;
import de.rode.sudoku.dto.ValidationResult;
import de.rode.sudoku.dto.Visualation;
import de.rode.sudoku.validate.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@Slf4j
public class SudokuService implements SudokuApi {

    private final Validator validator;

    @PostMapping("/validate")
    @Override
    public ValidationResult validate(@RequestBody Sudoku sudoku) {
        return validator.validate(sudoku);
    }

    @Override
    public Visualation visualize(Sudoku sudoku) {
        return null;
    }

    @Override
    public Solution solve(Sudoku sudoku) {
        return null;
    }

}
