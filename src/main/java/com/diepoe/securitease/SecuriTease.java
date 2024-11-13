package com.diepoe.securitease;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.cthiebaud.passwordvalidator.PasswordValidator;
import com.cthiebaud.passwordvalidator.ValidationResult;

/**
 * This class bundles the application logic while implementing the
 * PasswordValidator interface
 * 
 * @implNote PasswordValidator prerequisite for the project design
 */
public class SecuriTease implements PasswordValidator {
    private List<Rule> rules;

    // List of European countries in German
    private static final List<String> EUROPEAN_COUNTRIES_DE = Arrays.asList(
        "Albanien", "Andorra", "Belarus", "Belgien", "Bosnien und Herzegowina", "Bulgarien", "Dänemark", 
        "Deutschland", "Estland", "Finnland", "Frankreich", "Griechenland", "Irland", "Island", "Italien", 
        "Kosovo", "Kroatien", "Latvia", "Liechtenstein", "Litauen", "Luxemburg", "Malta", "Moldawien", 
        "Monaco", "Montenegro", "Niederlande", "Nordmazedonien", "Norwegen", "Österreich", "Polen", "Portugal", 
        "Rumänien", "Russland", "San Marino", "Schweden", "Schweiz", "Serbien", "Slowakei", "Slowenien", "Spanien", 
        "Tschechien", "Türkei", "Ukraine", "Ungarn", "Vatikanstadt", "Vereinigtes Königreich");

    // List of European countries in English
    private static final List<String> EUROPEAN_COUNTRIES_EN = Arrays.asList(
        "Albania", "Andorra", "Belarus", "Belgium", "Bosnia and Herzegovina", "Bulgaria", "Denmark", "Germany", 
        "Estonia", "Finland", "France", "Greece", "Ireland", "Iceland", "Italy", "Kosovo", "Croatia", 
        "Latvia", "Liechtenstein", "Lithuania", "Luxembourg", "Malta", "Moldova", "Monaco", "Montenegro", "Netherlands", 
        "North Macedonia", "Norway", "Austria", "Poland", "Portugal", "Romania", "Russia", "San Marino", "Sweden", 
        "Switzerland", "Serbia", "Slovakia", "Slovenia", "Spain", "Czech Republic", "Turkey", "Ukraine", "Hungary", 
        "Vatican City", "United Kingdom");

    // List of Roman values
    private static final Map<Character, Integer> ROMAN_VALUES = Map.of(
            'I', 1,
            'V', 5,
            'X', 10,
            'L', 50,
            'C', 100,
            'D', 500,
            'M', 1000);

    // List of famous Composers
    private static final List<String> Composers = Arrays.asList(
        "Bach", "Beethoven", "Mozart", "Chopin", "Schubert", "Brahms",
        "Vivaldi", "Verdi", "Wagner", "Tschaikowski", "Stravinsky",
        "Mahler", "Debussy", "Haydn", "Mendelssohn", "Händel", "Liszt",
        "Rachmaninoff", "Ravel");

            

    public SecuriTease() {
        // TODO: implement randomized setting of the rules
        rules = new ArrayList<>();

        rules.add(new Rule(this::checkLength, new String[] { "Password must be at least 8 characters long" }, 8));
        rules.add(new Rule(this::checkRomanLiteralSum,
                new String[] { "The roman literals in your password have to sum up to 3"}, 3));
        rules.add(new Rule(this::checkContainsEuropeanCountry, 
                new String[] { "Password must contain the name of a European country" }, 1)); 
        rules.add(new Rule(this::checkContainsComposer, 
        new String[] { "Password must contain the name of a famous composer" }, 1));
}
    

    /**
     * main validation method
     * 
     * @param potentialPassord the password to validate
     */
    public ValidationResult validate(String potentialPassword) {
        // NEU: Durchlaufen der Regeln in der richtigen Reihenfolge
        for (Rule rule : rules) {
            CheckingFunction checker = rule.getCheckingFunction();
            boolean valid = checker.check(potentialPassword, rule.getThreshold());
            String message = rule.getFeedbackMessage()[0];

            // Wenn eine Regel nicht erfüllt ist, gib das Ergebnis sofort zurück
            if (!valid) {
                return new ValidationResult(valid, message);
            }
        }

        // Wenn alle Regeln erfüllt sind, return "valid"
        return new ValidationResult(true, "Password is valid");
    }

    // TODO create various types of checking methods for different yet-to-specify
    // rules

    /**
     * Checks if a given password meets a certain length criteria
     * 
     * @param password String - the password to be checked
     * @param requiredLength int - the minimal length of the password string
     * @return boolean - String.length >= int
     */
    private boolean checkLength(String password, int requiredLength) {
        return password.length() >= requiredLength;
    }

    private boolean checkRomanLiteralSum(String password, int requiredSum) {
       
        int sum = 0;
        int prevValue = 0;

        // Iterate right to left to handle subtraction cases
        for (int i = password.length() - 1; i >= 0; i--) {
            char c = password.charAt(i);
            int currentValue = ROMAN_VALUES.getOrDefault(c, 0);

            // If previous value is greater, add normally
            // If previous value is smaller, subtract (like IV = 4)
            if (currentValue >= prevValue) {
                sum += currentValue;
            } else {
                sum -= currentValue;
            }

            prevValue = currentValue;
        }

        return sum == requiredSum;
    }

    private boolean checkContainsEuropeanCountry(String password, int threshold) {
        // Checks if password contains a European Country, case-insensitive
        boolean containsCountryDE = EUROPEAN_COUNTRIES_DE.stream().anyMatch(country -> password.toLowerCase().contains(country.toLowerCase()));
        boolean containsCountryEN = EUROPEAN_COUNTRIES_EN.stream().anyMatch(country -> password.toLowerCase().contains(country.toLowerCase()));
        return containsCountryDE || containsCountryEN;
    }
    
    private boolean checkContainsComposer(String password, int threshold) {
        // Checks if password contains a famous composer, case-insensitive
        return Composers.stream().anyMatch(composer -> password.toLowerCase().contains(composer.toLowerCase()));
    }
}
