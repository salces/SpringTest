package com.example.pl.slc.validator;

import com.example.pl.slc.model.Club;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Calendar;

/**
 * Created by slc on 14.07.16.
 */
public class CreationYearValidator implements ConstraintValidator<CreationYear,Integer>{

    @Override
    public void initialize(CreationYear creationYear) {

    }

    @Override
    public boolean isValid(Integer creationYear, ConstraintValidatorContext constraintValidatorContext) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        if(creationYear < 1700 || creationYear > currentYear) return false;

        return true;
    }
}
