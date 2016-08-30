package com.example.pl.slc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by slc on 13.07.16.
 */

public class PasswordValidator implements ConstraintValidator<Password, String> {


    @Override
    public void initialize(Password password) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null) return false;
        return containsUpperCase(s) && containsSpecialCharacter(s) && hasProperLength(s);
    }

    private boolean containsUpperCase(String s){
        return !s.equals(s.toLowerCase());
    }

    private boolean hasProperLength(String s){
        return s.length() > 8;
    }

    private boolean containsSpecialCharacter(String s){
        Pattern pattern = Pattern.compile("[^a-z0-9]");
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }
}
