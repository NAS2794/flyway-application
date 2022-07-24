package com.flwaway.Validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FlightValidator.class)
@Target( {ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidFlight {

    String message() default "A valid flight number starts with { IN- } followed by alphanumeric code of 5 characters.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}


