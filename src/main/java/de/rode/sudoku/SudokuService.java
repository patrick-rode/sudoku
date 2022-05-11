package de.rode.sudoku;

import de.rode.sudoku.dto.Sudoku;
import de.rode.sudoku.dto.SudokuValidateError;
import de.rode.sudoku.logic.Displayer;
import de.rode.sudoku.logic.Parser;
import de.rode.sudoku.logic.Solver;
import de.rode.sudoku.logic.Validator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Optional;

@Validated
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RestController
@Slf4j
public class SudokuService {

    private final Parser parser;
    private final Validator validator;
    private final Displayer displayer;
    private final Solver solver;

    @GetMapping("/validate")
    public ResponseEntity validate(@RequestParam("sudokuParam")
                                   @Size(min = 81, max = 81)
                                   @Pattern(regexp = "^[0-9]*$") String sudokuParam) {

        final Sudoku sudoku = parser.parseFromString(sudokuParam);

        final Optional<SudokuValidateError> optionalError = validator.validate(sudoku);
        if (optionalError.isPresent()) {
            return ResponseEntity.status(400)
                    .body(optionalError.get());
        }
        return ResponseEntity.status(200)
                .body("Ok - Der Eingabewert ist valide.");
    }

    @GetMapping("/display")
    public ResponseEntity display(@RequestParam("sudokuParam")
                                  @Size(min = 81, max = 81)
                                  @Pattern(regexp = "^[0-9]*$") String sudokuParam) {

        final Sudoku sudoku = parser.parseFromString(sudokuParam);

        return ResponseEntity.status(200)
                .body(displayer.display(sudoku));
    }

    @GetMapping("/solve")
    public ResponseEntity solve(@RequestParam("sudokuParam")
                                @Size(min = 81, max = 81)
                                @Pattern(regexp = "^[0-9]*$") String sudokuParam) {

        final Sudoku sudoku = parser.parseFromString(sudokuParam);

        return ResponseEntity.status(200)
                .body(solver.solve(sudoku));

    }

}
