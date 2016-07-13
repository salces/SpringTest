package com.example.pl.slc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by slc on 13.07.16.
 */

@Controller
public class AddNewPlayer {

    @RequestMapping("/addNewPlayer")
    public ModelAndView getView(){
        ModelAndView mav = new ModelAndView("addNewPlayer");
        return mav;
    }



}
