# OracleRun

Read Oracle SQL file and execute line by line.

Generate output file as TSV (tab-separated value) file.

## Requirement
Oracle JDBC driver (Put ojdbc*.jar to bin/.)

## Compile
```
javac src/OracleRun.java
```

## Run
Unix:
```
java -cp bin:bin/ojdbc*.jar OracleRun
```

Windows:
```
java -cp bin;bin/ojdbc*.jar OracleRun
```

## License
Public domain
