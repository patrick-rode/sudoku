package de.rode.sudoku;

import de.rode.sudoku.display.Displayer;
import de.rode.sudoku.dto.Sudoku;
import de.rode.sudoku.dto.SudokuValidateError;
import de.rode.sudoku.parse.Parser;
import de.rode.sudoku.solve.Solver;
import de.rode.sudoku.validate.Validator;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@RequestMapping("/sudoku")
@RestController
@Slf4j
public class SudokuService {

    private final Parser parser;
    private final Validator validator;
    private final Displayer displayer;
    private final Solver solver;

    @GetMapping("/validate/{string}")
    public ResponseEntity validate(@PathVariable String string) {

        final Either<SudokuValidateError, Sudoku> either = parser.parseFromString(string);

        if (either.isLeft()) {
            return ResponseEntity.status(either.getLeft().getStatus())
                    .body(either.getLeft());
        } else {
            final Optional<SudokuValidateError> optionalError = validator.validate(either.get());
            if (optionalError.isPresent()) {
                return ResponseEntity.status(400)
                        .body(optionalError.get());
            }
            return ResponseEntity.status(200)
                    .body("Ok - Der Eingabewert ist valide.");
        }
    }

    @GetMapping("/display")
    public ResponseEntity display(@PathVariable String string) {
        return null;
    }

    @GetMapping("/solve")
    public ResponseEntity solve(@PathVariable String string) {
        return null;
    }


}
