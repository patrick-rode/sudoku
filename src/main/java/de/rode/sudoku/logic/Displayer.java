package de.rode.sudoku.logic;

import de.rode.sudoku.dto.Sudoku;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Displayer {

    public String display(Sudoku sudoku) {

        final StringBuilder stringBuilder = new StringBuilder("<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table {\n" +
                "  margin:1em auto;\n" +
                "}\n" +
                "td {\n" +
                "  height:30px;\n" +
                "  width:30px;\n" +
                "  border:1px solid;\n" +
                "  text-align:center;\n" +
                "}\n" +
                "td:first-child {\n" +
                "  border-left:solid;\n" +
                "}\n" +
                "td:nth-child(3n) {\n" +
                "  border-right:solid ;\n" +
                "}\n" +
                "tr:first-child {\n" +
                "  border-top:solid;\n" +
                "}\n" +
                "tr:nth-child(3n) td {\n" +
                "  border-bottom:solid ;\n" +
                "}" +
                "</style>\n" +
                "</head>\n" +
                "<body>" +

                "<table id=\"sudoku\">\n" +
                "\t<tbody>");

        for (int j=0; j<9; j++) {
            stringBuilder.append("<tr>");
            for (int i=0; i<9; i++) {
                stringBuilder
                        .append("<td>")
                        .append(sudoku.getFields()[i][j])
                        .append("</td>");
            }
            stringBuilder.append("</tr>");
        }
        return stringBuilder
                .append("\t</tbody>\n" +
                        "</table>\n" +
                        "\n" +
                        "</body>\n" +
                        "</html>")
                .toString();
    }

}
