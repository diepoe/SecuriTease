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

4. Run the project (classpath was exported in step *2* into the `.classpath` file, use that if the provided command isn't working):
```sh
java -cp "target/classes:/Users/$(whoami)/.m2/repository/com/cthiebaud/password-validator/1.0-SNAPSHOT/password-validator-1.0-SNAPSHOT.jar" com.diepoe.SecuriTeaseApp
```

### Testing

#### run unit tests

```sh
mvn clean test
```

#### run grading testing program

1. Package the project
```sh
mvn clean package
```

2. Run the test program
```sh
java -cp "target/classes:CONTENT_OF_.classpath_FILE" com.cthiebaud.PasswordValidatorTester $PWD/target/securitease-1.0.0-SNAPSHOT.jar
```


## Project structure
(Draft)
```mermaid
---
title: SecuriTease
---
classDiagram
    ValidationResult -- PasswordValidator
    PasswordValidator <|-- SecuriTease : implements
    SecuriTease <.. SecuriTeaseApp
    SecuriTease -- CheckingFunction
    
    namespace com.diepoe {
        class SecuriTeaseApp {
            +void main()$
        }

        class com.diepoe.securitease
    }

    namespace com.diepoe.securitease {
        class SecuriTease {
            -Map&lt;CheckingFunction, String[]&gt; rules
            +ValidationResult validate(String potentialPassword)
            -boolean checkLength(String password, int requiredLength)
            -boolean checkRomanLiteralSum(String password, int requiredSum)
        }

        class CheckingFunction {
            boolean check(String password, int threshold)
        } 
    }

    <<interface>> CheckingFunction

    %% given by @cthiebaud:

    namespace com.cthiebaud{
        class PasswordValidatorTester {
            +void main()$
            -List&lt;Class&lt;?&gt;&gt; findPasswordValidatorClasses()$
            -void printBigOK()$
        }

        class com.cthiebaud.passwordvalidator
    }
    PasswordValidator <.. PasswordValidatorTester
    SecuriTease <.. PasswordValidatorTester
    ValidationResult <.. PasswordValidatorTester

    namespace com.cthiebaud.passwordvalidator{
        class ValidationResult {
            boolean isValid
            String message
        }
        
        class PasswordValidator {
            ValidationResult validate(String potentialPassword)
        }
    }

    <<record>> ValidationResult
    <<interface>> PasswordValidator

```