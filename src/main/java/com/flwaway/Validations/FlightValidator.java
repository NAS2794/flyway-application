package com.flwaway.Validations;

import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FlightValidator implements ConstraintValidator<IsValidFlight, String> {


    @Override
    public void initialize(IsValidFlight isValidFlight) {
        isValidFlight.message();
    }

    @Override
    public boolean isValid(String flightName, ConstraintValidatorContext constraintValidatorContext) {

        boolean result = false;

        if(flightName==null){
            result=false;
        }
        else if(flightName.contains("IN")&&flightName.contains("-")&&flightName.length()==8){
            result = true;
        }
        else {result = false;}

        return result;



    }
}
