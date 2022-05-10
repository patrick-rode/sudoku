package de.rode.sudoku.display;

import de.rode.sudoku.dto.Sudoku;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Displayer {

    public String display(Sudoku sudoku) {

        final StringBuilder stringBuilder = new StringBuilder("<html><table border=\"1\"><h2>");

        for (int j=0; j<9; j++) {
            stringBuilder.append("<tr>");
            for (int i=0; i<9; i++) {
                stringBuilder
                        .append("<th>")
                        .append(sudoku.getFields()[i][j])
                        .append("</th>");
            }
            stringBuilder.append("</tr>");
        }
        return stringBuilder
                .append("</h2></table></html>")
                .toString();
    }

}
