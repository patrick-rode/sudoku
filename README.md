# sudoku

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