package com.example.pl.slc.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by slc on 13.07.16.
 */

@Documented
@Constraint(validatedBy = UsernameValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Username {

    String message() default "The specified username already exists";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}

