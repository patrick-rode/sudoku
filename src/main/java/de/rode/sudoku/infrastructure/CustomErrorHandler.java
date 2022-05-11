package de.rode.sudoku.infrastructure;

import de.rode.sudoku.dto.SudokuValidateError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;

@ControllerAdvice
public class CustomErrorHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<SudokuValidateError> handleConstraintViolationException(
            final ConstraintViolationException exception,
            final ServletWebRequest webRequest) {

        return ResponseEntity
                .badRequest()
                .body(SudokuValidateError.builder()
                        .timestamp(ZonedDateTime.now())
                        .status(400)
                        .error("Bad Request")
                        .message(exception.getMessage())
                        .path(webRequest.getRequest().getRequestURI())
                        .build());
    }
}