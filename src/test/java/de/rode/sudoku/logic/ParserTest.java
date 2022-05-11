package de.rode.sudoku.logic;

import de.rode.sudoku.dto.Sudoku;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ParserTest {

    private final Parser parser = new Parser();

    @Test
    void parseFromStringTest1() {
        Sudoku sudoku = parser.parseFromString(
                "010000000000000000000000000000000000000000000000000000000000000000000000000000000");

        assertThat(sudoku.getFields()[0][1])
                .isEqualTo(1);
        assertThat(sudoku.getFields()[1][0])
                .isEqualTo(0);
    }

    @Test
    void parseFromStringTest2() {
        Sudoku sudoku = parser.parseFromString(
                "000000000100000000000000000000000000000000000000000000000000000000000000000000000");

        assertThat(sudoku.getFields()[0][1])
                .isEqualTo(0);
        assertThat(sudoku.getFields()[1][0])
                .isEqualTo(1);
    }
}