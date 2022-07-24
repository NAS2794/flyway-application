package com.flwaway.Validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GenderValidatior implements ConstraintValidator<IsValidGender, String> {


    @Override
    public void initialize(IsValidGender isValidGender) {
        isValidGender.message();
    }

    @Override
    public boolean isValid(String gender, ConstraintValidatorContext constraintValidatorContext) {

        if(gender.equalsIgnoreCase("male")|| (gender.equalsIgnoreCase("female"))) {

            return true;
        }

        return false;
    }
}
