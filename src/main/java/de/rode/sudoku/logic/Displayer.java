package de.rode.sudoku.logic;

import de.rode.sudoku.dto.Sudoku;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Displayer {

    private final String css = "<!DOCTYPE html>" +
            "<html>" +
            "<head>" +
            "<style>" +
            "table {" +
            "  margin:1em auto;" +
            "}" +
            "td {" +
            "  height:30px;" +
            "  width:30px;" +
            "  border:1px solid;" +
            "  text-align:center;" +
            "}" +
            "td:first-child {" +
            "  border-left:solid;" +
            "}" +
            "td:nth-child(3n) {" +
            "  border-right:solid ;" +
            "}" +
            "tr:first-child {" +
            "  border-top:solid;" +
            "}" +
            "tr:nth-child(3n) td {" +
            "  border-bottom:solid ;" +
            "}" +
            "</style>" +
            "</head>";

    public String display(Sudoku sudoku) {
        return css + "<body><center>...Und hier ist Ihr Herzblatt:" + writeTable(sudoku) + "</center></body></html>";
    }

    public String wrapWithCss(String string) {
        return css + "<body><center>" + string + "</center></body></html>";
    }

    public String writeTable(Sudoku sudoku) {
        final StringBuilder stringBuilder = new StringBuilder("<table>\n");

        for (int j=0; j<9; j++) {
            stringBuilder.append("<tr>\n");
            for (int i=0; i<9; i++) {
                int number = sudoku.getFields()[j][i];
                stringBuilder
                        .append("<td>")
                        .append(number)
                        .append("</td>\n");
            }
            stringBuilder.append("</tr>\n");
        }
        return stringBuilder
                .append("</table>\n")
                .toString();
    }

}
