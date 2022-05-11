# Sudoku

Der Spring Boot Service kann mit
`./gradlew build`
gebaut und mit
`java -jar build/libs/sudoku-0.0.1.jar`
gestartet werden.

Testen kann man ihn dann mit Postman oder teilweise auch mit einem Internet Explorer.
Beispielsweise kann die URL
http://localhost:8080/sudoku/solve/005600020038207640006980530050032090400090700093040208010300962360409000800001300 im Browser
aufgerufen werden.
Damit wird ein Sudoku geloest, das man sich auch wie folgt aufmalen kann:

```
005  600  020
038  207  640
006  980  530

050  032  090
400  090  700
093  040  208

010  300  962
360  409  000
800  001  300
```

# Dokumentation

http://localhost:8080/sudoku/v3/api-docs.yaml/

# Indizes i und j

Wenn wir beispielsweise mit
```
new int[][]{
        {0, 0, 5, 6, 0, 0, 0, 2, 0},
        {0, 3, 8, 2, 0, 7, 6, 4, 0},
        {0, 0, 6, 9, 8, 0, 5, 3, 0},

        {0, 5, 0, 0, 3, 2, 0, 9, 0},
        {4, 0, 0, 0, 9, 0, 7, 0, 0},
        {0, 9, 3, 0, 4, 0, 2, 0, 8},

        {0, 1, 0, 3, 0, 0, 9, 6, 2},
        {3, 6, 0, 4, 0, 9, 0, 0, 0},
        {8, 0, 0, 0, 0, 1, 3, 0, 0}
}
```
ein Sudoku instanziieren, so nennen wir in der obersten Zeile die ersten drei Werte

```math
SE = \frac{\sigma}{\sqrt{n}}
```