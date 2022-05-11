package de.rode.sudoku.logic;

import de.rode.sudoku.dto.Sudoku;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SolverTest {

    private final Solver solver = new Solver(new Displayer());

    private final Parser parser = new Parser();

    @Test
    void solveTest() {
        // vorbereiten
        Sudoku sudoku = parser.parseFromString(
                "005600020038207640006980530050032090400090700093040208010300962360409000800001300");

        // ausfuehren
        String htmlString = solver.solve(sudoku);

        // pruefen
        String[] tables = htmlString.split("Das wurde zu");

        // noch nicht fertig (enthalten mind. einmal die 0)
        assertThat(tables[0])
                .contains("<td>0</td>");
        assertThat(tables[1])
                .contains("<td>0</td>");
        assertThat(tables[2])
                .contains("<td>0</td>");
        assertThat(tables[3])
                .contains("<td>0</td>");

        // fertig (enthaelt keine 0 mehr)
        assertThat(tables[4])
                .doesNotContain("<td>0</td>");
    }
}