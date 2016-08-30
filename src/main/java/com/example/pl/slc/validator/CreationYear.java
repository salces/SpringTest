package com.example.pl.slc.validator;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by slc on 14.07.16.
 */
@Documented
@Constraint(validatedBy = CreationYearValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CreationYear {
    String message() default "Wrong creation year!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
