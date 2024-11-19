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
 * @author Dietrich Poensgen, Mikail Demirel
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
            "Latvia", "Liechtenstein", "Lithuania", "Luxembourg", "Malta", "Moldova", "Monaco", "Montenegro",
            "Netherlands",
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
            "Rachmaninoff", "Ravel", "Kreisler");

    private static final List<Character> SPECIAL_CHARACTERS = Arrays.asList(
            '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+', '{', '}',
            '[', ']', ':', ';', '"', '\'', '<', '>', ',', '.', '?', '/', '\\', '|', '~', '`');

    public SecuriTease() {
        // TODO: implement randomized setting of the rules
        rules = new ArrayList<>();

        rules.add(new Rule(this::checkLength, new String[] {
                "[Rolls eyes dramatically] Listen honey, if you think I'm gonna accept your sad little short-as-a-stinky-fart password, you've got another thing coming. Make it 8 characters or more - I don't make the rules... oh wait, yes I do. 💅✨",
                "[Augenrollen] Hör mal, Schnegge, wenn du denkst, dass ich deinen Furz von Passwort akzeptiere, dann hast du dich geschnitten. 8 Buchstaben in deiner hässlichen Handschrift oder mehr - ich mache die Regeln nicht... oh warte, doch. 💅✨",
                "[Roule les yeux de façon dramatique] Écoute, chérie, si tu crois que je vais accepter ton petit mot de passe aussi court qu'un pet qui pue, tu te trompes. Mets 8 caractères ou plus - ce n'est pas moi qui fais les règles... oh attends, si je les fais. 💅✨" },
                8));
        rules.add(new Rule(this::checkRomanLiteralSum,
                new String[] {
                        "Oh sweetie... You really thought XLII was the answer? Darling, I need your Roman numerals to add up to 42, not whatever math disaster you just typed. Maybe take a little trip to the Forum and brush up on your arithmetic? I'll wait... [fixes toga] 🏛️" },
                42));
        rules.add(new Rule(this::checkContainsEuropeanCountry,
                new String[] { "Password must contain the name of a European country" }, 1));
        rules.add(new Rule(this::checkContainsComposer,
                new String[] { "Password must contain the name of a famous composer" }, 1));
        rules.add(new Rule(this::checkEiffelTowerHeight,
                new String[] { "How tall is the Eiffel Tower?" }, 0)); // Neu Mika
        rules.add(new Rule(this::checkSpecialCharacters, // neu mika
                new String[] { "Your password needs at least 2 fabulous special characters. ✨" }, 2)); // Neu mika
        rules.add(new Rule(this::checkMeaningOfLife, // Neu Mika
                new String[] { "What is the meaning of life?" }, 42)); // Neu Mika

    }

    /**
     * main validation method
     * 
     * @param potentialPassword the password to validate
     */
    public ValidationResult validate(String potentialPassword) {
        boolean valid = true;
        String message = "[Slow clap] Ohhh, congratulations, you finally managed to enter a valid password. Want a cookie for doing the absolute bare minimum? 🙄";

        for (Rule rule : rules) {
            boolean ruleValid = rule.getCheckingFunction().check(potentialPassword, rule.getThreshold());
            if (!ruleValid) {
                valid = false;
                message = rule.getRandomFeedbackMessage();
                break;
            }
        }

        return new ValidationResult(valid, message);
    }

    // TODO create various types of checking methods for different yet-to-specify
    // rules

    /**
     * Checks if a given password meets a certain length criteria
     * 
     * @param password       the string to be checked
     * @param requiredLength the minimal length (int) of the password string
     * @return true if the string length is bigger or equal than the required length
     */
    boolean checkLength(String password, int requiredLength) {
        return password.length() >= requiredLength;
    }

    /**
     * Checks if the sum of the Roman numeral literals in the given password equals
     * the required sum (threshold).
     *
     * @param password    the string containing Roman numeral literals to be checked
     * @param requiredSum the sum that the Roman numeral literals in the password
     *                    should equal
     * @return true if the sum of the Roman numeral literals equals the required
     *         sum, false otherwise
     */
    boolean checkRomanLiteralSum(String password, int requiredSum) {
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

    /**
     * Checks if the given password string contains the name of a European country
     * 
     * @param password  the string to be checked
     * @param threshold this param is ignored
     * @return true if the password contains the name of a European country, else
     *         false
     */
    boolean checkContainsEuropeanCountry(String password, int threshold) {
        // Checks if password contains a European Country, case-insensitive
        boolean containsCountryDE = EUROPEAN_COUNTRIES_DE.stream()
                .anyMatch(country -> password.toLowerCase().contains(country.toLowerCase()));
        boolean containsCountryEN = EUROPEAN_COUNTRIES_EN.stream()
                .anyMatch(country -> password.toLowerCase().contains(country.toLowerCase()));
        return containsCountryDE || containsCountryEN;
    }

    /**
     * Checks if the given password string contains the name of a famous composer
     * 
     * @param password  the string to be checked
     * @param threshold this param is ignored
     * @return true if the password contains the name of a famous composer, else
     *         false
     */
    boolean checkContainsComposer(String password, int threshold) {
        // Checks if password contains a famous composer, case-insensitive
        return Composers.stream().anyMatch(composer -> password.toLowerCase().contains(composer.toLowerCase()));
    }

    boolean checkEiffelTowerHeight(String password, int threshold) {
        String regex = "\\d+"; // Regex für Zahlen
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(password);

        while (matcher.find()) {
            try {
                int height = Integer.parseInt(matcher.group());
                if (height >= 300 && height <= 320) {
                    return true;
                }
            } catch (NumberFormatException e) {
                return false;
            }
        }

        // Wenn keine Zahl im gültigen Bereich gefunden wird, ist die Regel nicht
        // erfüllt
        return false;
    }

    boolean checkMeaningOfLife(String password, int threshold) {
        return password.contains("42");
    }

    boolean checkSpecialCharacters(String password, int threshold) {
        int specialCharCount = 0;
        for (char c : password.toCharArray()) {
            if (SPECIAL_CHARACTERS.contains(c)) {
                specialCharCount++;
                if (specialCharCount >= threshold) {
                    return true;
                }
            }
        }
        return false;
    }

}