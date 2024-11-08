package com.diepoe.securitease;

import com.cthiebaud.passwordvalidator.PasswordValidator;
import com.cthiebaud.passwordvalidator.ValidationResult;

/**
 * This class bundles the application logic while implementing the
 * PasswordValidator interface
 * 
 * @implNote PasswordValidator prerequisite for the project design
 */
public class SecuriTease implements PasswordValidator {
    /**
     * main validation method
     * 
     * @param potentialPassord the password to validate
     */
    public ValidationResult validate(String potentialPassord) {
        return new ValidationResult(true, "Passwordo e perfecto!");
    }

    // TODO create various types of checking methods for different yet-to-specify rules
}
