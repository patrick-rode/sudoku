# Sudoku

Sudokus sind Zahlenrätsel, die in einem Gitter der Größe 9 × 9 abgebildet sind. In dem Gitter sind einige Kästchen mit
Zahlen von 1 bis 9 ausgefüllt. Die Aufgabe besteht darin, in die restlichen Kästchen Zahlen von 1 bis 9 einzutragen, so
dass folgende Bedingungen erfullt sind
<ul>
  <li>In jeder Zeile kommt jede Zahl genau einmal vor.</li>
  <li>In jedem (durch die dicken Linien abgegrenzten) 3 × 3-Quadrat kommt jede Zahl genau einmal vor.</li>
  <li>In jeder Spalte kommt jede Zahl genau einmal vor.</li>
</ul>

# Service lokal starten

Der Spring Boot Service kann mit
`./gradlew build`
gebaut und mit
`java -jar build/libs/sudoku-0.0.1.jar`
gestartet werden.

# Funktionen

Wenn der Service laeuft, kann man diese Links im Browser aufrufen und die Ergebnisse einsehen:

http://localhost:8080/sudoku/validate?sudokuParam=005600020038207640006980530050032090400090700093040208010300962360409000800001300
http://localhost:8080/sudoku/display?sudokuParam=005600020038207640006980530050032090400090700093040208010300962360409000800001300
http://localhost:8080/sudoku/solve?sudokuParam=005600020038207640006980530050032090400090700093040208010300962360409000800001300

# Schnittstelle

Die openapi yaml ist unter http://localhost:8080/sudoku/v3/api-docs.yaml/ zu finden, wenn der Service gestartet wurde.

# Indizes i und j

Wir bezeichnen hier mit `(i|j)` ein Feld im Sudoku, das vom Feld links oben `i` Schritte nach rechts und `j` Schritte
nach unten entfernt ist. Im Code haben wir den Inhalt solch eines Feldes in `field[j][i]` gespeichert.