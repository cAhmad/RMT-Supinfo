package com.supinfo.rmt.validator;

import com.supinfo.rmt.validator.annotation.CheckPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * @author Ahmad
 * @version $Id$
 */
public class PasswordValidator implements ConstraintValidator<CheckPassword, String> {

    private Pattern pattern;

    @Override
    public void initialize(CheckPassword checkPassword) {
        final String passwordPattern = "^[(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)]{5,50}.+$";
        pattern = Pattern.compile(passwordPattern);
    }

    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintValidatorContext) {
        return pattern.matcher(object).matches();
    }
}
