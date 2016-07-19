package com.example.pl.slc.controller;

import com.example.pl.slc.model.User;
import com.example.pl.slc.repository.UserRepository;
import com.example.pl.slc.validator.Password;
import com.example.pl.slc.validator.Username;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

/**
 * Created by slc on 13.07.16.
 */

@Controller
public class RegisterController {

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = "/register")
    public ModelAndView getForm() {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("registerForm", new RegisterForm());
        return mav;
    }

    @RequestMapping(value = "/register", params = "register")
    public ModelAndView registerUser(@Valid @ModelAttribute RegisterForm registerForm, BindingResult result) {
        ModelAndView mav;

        if (result.hasErrors()) {
            mav = new ModelAndView("/register");
            mav.addObject("registerForm",registerForm);
        } else {
            User user = new User(registerForm);
            userRepository.register(user);
            System.out.println(user.getSurname());
            mav = new ModelAndView("thanksForRegistration");
        }
        return mav;
    }

    static public class RegisterForm {

        @Username
        private String username;
        @Password
        private String password;
        private String name;
        private String surname;
        @Email(message = "Wrong email format")
        @NotBlank(message = "Required field")
        private String email;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
