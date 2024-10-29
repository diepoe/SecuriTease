# SecuriTease

The Sassy Security Checker

## Development setup

> **Requirements:** Java 23 & Maven

### Installation

1. Install dependencies:
```sh
mvn clean install
```

2. Get classpath of dependencies:
```sh
mvn dependency:build-classpath -Dmdep.outputFile=.classpath
```

### Runing the source code

3. Compile the project:
```sh
mvn clean compile
```

4. Run the project (classpath was exported in step *2* into the `.classpath` file):
```sh
java -cp "target/classes:CONTENT_OF_.classpath_FILE" com.diepoe.SecuriTeaseApp
```

### Testing
-[] TODO: implement testing