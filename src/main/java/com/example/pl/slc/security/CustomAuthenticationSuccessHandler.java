package com.example.pl.slc.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class CustomAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {


    @Autowired
    LoggedUser loggedUser;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, org.springframework.security.core.Authentication authentication) throws IOException, ServletException {
        super.onAuthenticationSuccess(request, response, authentication);
        loggedUser.initLoggedUser();
    }
}
