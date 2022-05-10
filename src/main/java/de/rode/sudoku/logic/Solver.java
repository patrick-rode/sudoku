package de.rode.sudoku.logic;

import de.rode.sudoku.dto.Sudoku;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Component
public class Solver {

    private final Displayer displayer;

    public String solve(Sudoku sudoku) {
        int[][] fields = sudoku.getFields();
        String output = "";

        // default wert auf true setzen, damit wir den loop betreten
        boolean didSomethingInsideTheLoop = true;

        // wenn sich nix aendert, sind wir fertig und brauchen den loop nicht mehr
        while (didSomethingInsideTheLoop) {
            didSomethingInsideTheLoop = false;
            for (int j = 0; j < 9; j++) {
                for (int i = 0; i < 9; i++) {
                    if (fields[i][j] == 0) {
                        Set<Integer> options = getOptionsForField(fields, i, j);
                        if (options.isEmpty()) {
                            throw new IllegalStateException("shit");
                        } else if (options.size() == 1) {
                            fields[i][j] = options.iterator().next();
                            didSomethingInsideTheLoop = true;
                        }
                    }
                }
            }
            output += displayer.display(sudoku);
        }
        return output;
    }

    private Set<Integer> getOptionsForField(int[][] fields, int column, int row) {
        // 1. lasse erstmal alle Zahlen 1-9 zu
        HashSet<Integer> output = new HashSet<>();
        for (int i=1; i<10; i++) {
            output.add(i);
        }

        // 2. streiche alles weg, was in der selben Zeile ist
        for (int i=0; i<9; i++) {
            output.remove(fields[i][row]);
        }

        // 3. streiche alles weg, was in der selben Spalte ist
        for (int j=0; j<9; j++) {
            output.remove(fields[column][j]);
        }

        // 4. streiche alles weg, was im selben Quadrat ist
        int imin = column < 3 ? 0 : (column < 6 ? 3 : 6);
        int imax = imin + 3;
        int jmin = row < 3 ? 0 : (row < 6 ? 3 : 6);
        int jmax = jmin + 3;
        for (int i=imin; i<imax; i++) {
            for (int j = jmin; j<jmax; j++) {
                output.remove(fields[i][j]);
            }
        }

        return output;
    }
}
