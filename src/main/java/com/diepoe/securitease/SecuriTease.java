package com.diepoe.securitease;

import com.cthiebaud.passwordvalidator.PasswordValidator;
import com.cthiebaud.passwordvalidator.ValidationResult;

public class SecuriTease implements PasswordValidator {
    public ValidationResult validate(String potentialPassord) {
        return new ValidationResult(true, "Passwordo e perfecto!");
    }
}
