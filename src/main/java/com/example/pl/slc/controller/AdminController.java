package com.example.pl.slc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admins")
public class AdminController {

    @RequestMapping("/test")
    public String getTestView(){
        return "thanksForRegistration";
    }
}
