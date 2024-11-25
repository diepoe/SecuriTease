<center>
    <img src="https://github.com/diepoe/SecuriTease/blob/main/.github/SecuriTease-Logo.png?raw=true" alt="securitease logo">
</center>

# SecuriTease

The Sassy Security Checker.
Bored by the wastelands of password creators? We have the solution: SecuriTease - the password validator that drives you even crazier, and that in three languages. Have fun and...

don't die...

## Validation rules

- **Length**
	- A modifiable minimal length of the password, ensures safety against brute-force attacks - because short passwords are for amateurs
- **RomanLiteralSum**
    - A modifiable number that is required to be represtented in the String as Roman numerals - Caesar would be proud!
- **ContainsEuropeanCountry**
    - A European country — even the hacker has to let his inner traveler shine!
- **ContainsComposer**
    - A name of a famous composer must be included - this is in fact no classic prerequisite!
- **ContainsEiffelTowerHeight**
    - Est-ce que tu parles français? no? -  me neither. Let´s leave this French-inspired riddle to numbers...
- **SpecialCharacters**
    - A few special characters must be sprinkled in - passwords without these are like cakes without frosting.
- **Meaning Of Life**
    - A tricky one! What is the meaning of life? - If Douglas Adams approves, so will the password validator.

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

#### Run unit tests

```sh
mvn clean test
```

> **Note -** The following tests are ran: 
> 1. complete check of a invalid password
> 2. complete check of a valid password
> 3. one test at a time for each `CheckingFunction`

#### Run the grading testing program ([`com.cthiebaud.PasswordValidatorTester`](https://github.com/athenaeum-brew/password-validator/blob/main/src/main/java/com/cthiebaud/PasswordValidatorTester.java))

1. Package the project
```sh
mvn clean package
```

2. Run the test program
```sh
java -cp "target/classes:/Users/$(whoami)/.m2/repository/com/cthiebaud/password-validator/1.0-SNAPSHOT/password-validator-1.0-SNAPSHOT.jar" com.cthiebaud.PasswordValidatorTester $PWD/target/securitease-1.0.0-SNAPSHOT.jar
```


## Project structure

```mermaid
---
title: SecuriTease
---
classDiagram
    ValidationResult -- PasswordValidator
    PasswordValidator <|.. SecuriTease : implements
    SecuriTease <.. SecuriTeaseApp
    Rule -- CheckingFunction
    SecuriTease "1" *-- "1..*" Rule
    
    namespace com.diepoe {
        class SecuriTeaseApp {
            +main()$
            -printWelcome()$
        }

        class com.diepoe.securitease
    }

    namespace com.diepoe.securitease {
        class SecuriTease {
            -List~Rule~ rules
            -List~String~ EUROPEAN_COUNTRIES_DE$
            -List~String~ EUROPEAN_COUNTRIES_EN$
            -Map~Character, Integer~ ROMAN_VALUES$
            +validate(String potentialPassword) ValidationResult
            ~checkLength(String password, int requiredLength) boolean
            ~checkRomanLiteralSum(String password, int requiredSum) boolean
            ~checkContainsEuropeanCountry(String password, int threshold) boolean
            ~checkContainsComposer(String password, int threshold) boolean
            ~checkContainsEiffelTowerHeight(String password, int threshold) boolean
            ~checkSpecialCharacters(String password, int threshold) boolean
            ~checkMeaningOfLife(String password, int threshold) boolean
        }

        class CheckingFunction {
            check(String password, int threshold) boolean
        }

        class Rule {
            -CheckingFunction checkingFunction;
            -String[] feedbackMessage;
            -int threshold;
            +getCheckingFunction() CheckingFunction
            +getFeedbackMessages() String[]
            +getFeedbackMessage(int index) String
            +getRandomFeedbackMessage() String
            +getThreshold() int
        }
    }

    <<FunctionalInterface>> CheckingFunction

    %% given by @cthiebaud:

    namespace com.cthiebaud{
        class PasswordValidatorTester {
            +main(String[] args)$
            -findPasswordValidatorClasses() List~Class~?~~$
            -printBigOK()$
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
            validate(String potentialPassword) ValidationResult
        }
    }

    <<record>> ValidationResult
    <<interface>> PasswordValidator

```

---

**Group members:** Mikail & Dietrich