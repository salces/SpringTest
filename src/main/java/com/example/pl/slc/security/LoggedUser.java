package com.example.pl.slc.security;

import com.example.pl.slc.model.User;
import com.example.pl.slc.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.InitBinder;

import javax.annotation.PostConstruct;


@Component
public class LoggedUser {

    @Autowired
    UserRepository userRepository;

    private User user;

    public SimpleUserDetails getUserDetails() {
        SimpleUserDetails userDetails = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (auth.isAuthenticated()) {
            Object principal = auth.getPrincipal();
            if (principal instanceof SimpleUserDetails) {
                userDetails = (SimpleUserDetails) principal;
            } else {
                throw new RuntimeException("Type conversion exception: SimpleUserDetails expected");
            }
        }

        return userDetails;
    }

    public String getUsername() {
        return getUserDetails().getUsername();
    }

    public User getLoggedUser() {
       return user;
    }

    public Long getLoggedUserID() {
        if (user == null) {
            user = userRepository.getByUserDetailsID(getUserDetails().getID());
        }
        return user.getID();
    }

    public void clear() {
        this.user = null;
    }

    public void initLoggedUser() {
        user = userRepository.getByUserDetailsID(getUserDetails().getID());
    }
}
