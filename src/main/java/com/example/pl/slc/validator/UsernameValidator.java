package com.example.pl.slc.validator;

import com.example.pl.slc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by slc on 13.07.16.
 */
//public class UsernameValidator{}
public class UsernameValidator implements ConstraintValidator<Username,String> {

    @Autowired
    UserRepository userRepository;

    @Override
    public void initialize(Username username) {
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(s == null) return true;
        return !userRepository.existsByUsername(s);
    }
}
