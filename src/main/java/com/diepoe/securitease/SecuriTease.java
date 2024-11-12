package com.diepoe.securitease;

import java.util.ArrayList;
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

    public SecuriTease() {
        // TODO: implement randomized setting of the rules
        rules = new ArrayList<>();

        rules.add(new Rule(this::checkLength, new String[] { "Password must be at least 8 characters long" }, 8));
        rules.add(new Rule(this::checkRomanLiteralSum,
                new String[] { "The roman literals in your password have to sum up to 3" }, 3));
    }

    /**
     * main validation method
     * 
     * @param potentialPassord the password to validate
     */
    public ValidationResult validate(String potentialPassord) {
        // TODO implement usage of the rules list

        Rule rule = rules.get(1);
        CheckingFunction checker = rule.getCheckingFunction();
        boolean valid = checker.check(potentialPassord, rule.getThreshold());
        String message = rule.getFeedbackMessage()[0];

        return new ValidationResult(valid, message);
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
        Map<Character, Integer> romanValues = Map.of(
                'I', 1,
                'V', 5,
                'X', 10,
                'L', 50,
                'C', 100,
                'D', 500,
                'M', 1000);

        int sum = 0;
        int prevValue = 0;

        // Iterate right to left to handle subtraction cases
        for (int i = password.length() - 1; i >= 0; i--) {
            char c = password.charAt(i);
            int currentValue = romanValues.getOrDefault(c, 0);

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
}
