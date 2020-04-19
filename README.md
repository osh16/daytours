# daytours

### Óskar Höskuldsson - osh16@hi.is
### Viktor Sigbjörn Víðisson - vsv8@hi.is
### Þorsteinn Sigurðsson - ths251@hi.is

Til að sækja project:

`$ git clone https://github.com/osh16/daytours.git`

Ef það vantar .class skrár:

`$ javac *.java`

Ef vesen er með gagnagrunn:

`$ rm daytours.db; sqlite3 daytours.db ".read db.sql"`

Til að nota skrár:

`$ javac [java skrá]`

`$ java -cp .:sqlite-jdbc-3.18.0.jar [java skrá]`

Fyrir windows:

`$ java -cp .;sqlite-jdbc-3.18.0.jar [java skrá]`
