package com.flwaway.Validations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = GenderValidatior.class)
@Target( {ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsValidGender {

    String message() default "Gender must always be Male/Female";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
