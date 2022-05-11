package de.rode.sudoku.logic;

import com.google.common.collect.Lists;
import de.rode.sudoku.dto.Sudoku;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
@Component
public class Solver {

    private final Displayer displayer;

    /**
     * Wir gehen Feld fuer Feld vor und pruefen mit getOptionsForField welche moeglichen Werte wir hier eintragen koennen.
     * Wenn es nur 1 Moeglichkeit gibt, so tragen wir sie ein. Wenn nicht, machen wir einfach mit dem naechsten Feld weiter.
     *
     * @param sudoku ist der Eingabeparameter
     * @return gibt einen String zurueck, der den kompletten Loesungsverlauf als HTML darstellt
     */
    public String solve(Sudoku sudoku) {
        int[][] fields = sudoku.getFields();
        List<String> sudokuStrings = Lists.newArrayList(displayer.writeTable(sudoku));

        // default wert auf true setzen, damit wir den loop betreten
        boolean didSomethingInsideTheLoop = true;

        // wenn sich nix aendert, sind wir fertig und brauchen den loop nicht mehr
        while (didSomethingInsideTheLoop) {
            didSomethingInsideTheLoop = false;
            for (int j = 0; j < 9; j++) {
                for (int i = 0; i < 9; i++) {
                    if (fields[j][i] == 0) {
                        Set<Integer> options = getOptionsForField(fields, i, j);
                        if (options.isEmpty()) {
                            return "Das Sudoku ist unmoeglich.";
                        } else if (options.size() == 1) {
                            fields[j][i] = options.iterator().next();
                            didSomethingInsideTheLoop = true;
                        }
                    }
                }
            }
            if (didSomethingInsideTheLoop)
                sudokuStrings.add(displayer.writeTable(sudoku));
        }
        return displayer.wrapWithCss("Wir haben angefangen mit" + String.join("Das wurde zu", sudokuStrings));
    }

    /**
     * Um rauszufinden, welche Werte wir in ein einzelnes Feld schreiben koennten, fangen wir mit allen Werten 1-9 an.
     * Wenn wir in der Spalte / in der Zeile / im Quadrat schon vorhandene Felder finden, streichen wir die Werte.
     * Das Set an Werten, die auf diese Weise nicht gestrichen wurden, geben wir zurueck.
     */
    private Set<Integer> getOptionsForField(int[][] fields, int column, int row) {
        // 1. lasse erstmal alle Zahlen 1-9 zu
        HashSet<Integer> output = new HashSet<>();
        for (int i = 1; i < 10; i++) {
            output.add(i);
        }

        // 2. streiche alles weg, was in der selben Zeile ist
        for (int i = 0; i < 9; i++) {
            output.remove(fields[row][i]);
        }

        // 3. streiche alles weg, was in der selben Spalte ist
        for (int j = 0; j < 9; j++) {
            output.remove(fields[j][column]);
        }

        // 4. streiche alles weg, was im selben Quadrat ist
        int imin = column < 3 ? 0 : (column < 6 ? 3 : 6);
        int imax = imin + 3;
        int jmin = row < 3 ? 0 : (row < 6 ? 3 : 6);
        int jmax = jmin + 3;
        for (int j = jmin; j < jmax; j++) {
            for (int i = imin; i < imax; i++) {
                output.remove(fields[j][i]);
            }
        }

        return output;
    }
}
