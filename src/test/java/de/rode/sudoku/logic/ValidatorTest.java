package de.rode.sudoku.logic;

import de.rode.sudoku.dto.Sudoku;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidatorTest {

    private final Validator validator = new Validator();

    @Test
    void everythingLegitTest() {
        final Sudoku sudoku = Sudoku.builder()
                .fields(new int[][]{
                        {0, 0, 5, 6, 0, 0, 0, 2, 0},
                        {0, 3, 8, 2, 0, 7, 6, 4, 0},
                        {0, 0, 6, 9, 8, 0, 5, 3, 0},

                        {0, 5, 0, 0, 3, 2, 0, 9, 0},
                        {4, 0, 0, 0, 9, 0, 7, 0, 0},
                        {0, 9, 3, 0, 4, 0, 2, 0, 8},

                        {0, 1, 0, 3, 0, 0, 9, 6, 2},
                        {3, 6, 0, 4, 0, 9, 0, 0, 0},
                        {8, 0, 0, 0, 0, 1, 3, 0, 0}
                }).build();

        for (int j = 0; j < 9; j++) {
            assertTrue(validator.columnLegit(sudoku, j));
            assertTrue(validator.rowLegit(sudoku, j));
            for (int i = 0; i < 9; i++) {
                assertTrue(validator.squareLegit(sudoku, i, j));
            }
        }
        assertFalse(validator.validate(sudoku).isPresent());
    }

    @Test
    void someFailuresTest() {
        final Sudoku sudoku = Sudoku.builder()
                .fields(new int[][]{
                        {0, 0, 1, 0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},

                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 2, 0, 0, 0, 4, 0, 2, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0},

                        {0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {5, 0, 5, 0, 0, 4, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0}
                }).build();

        for (int j = 0; j < 9; j++) {
            if (j == 5) {
                assertFalse(validator.columnLegit(sudoku, j));
            } else {
                assertTrue(validator.columnLegit(sudoku, j));
            }
            if (j == 4 || j == 7) {
                assertFalse(validator.rowLegit(sudoku, j));
            } else {
                assertTrue(validator.rowLegit(sudoku, j));
            }
            for (int i = 0; i < 9; i++) {
                if (
                        (i < 3 && j < 3) || (i < 3 && j > 5)
                ) {
                    assertFalse(validator.squareLegit(sudoku, i, j));
                } else {
                    assertTrue(validator.squareLegit(sudoku, i, j));
                }
            }
        }
        assertTrue(validator.validate(sudoku).isPresent());
    }

}