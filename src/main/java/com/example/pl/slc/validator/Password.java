package com.example.pl.slc.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by slc on 13.07.16.
 */

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Password {
    String message() default "Password must be at least 8 letters long, contain uppercase and special character!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
