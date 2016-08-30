package com.example.pl.slc.controller.api;


import com.example.pl.slc.aspect.ControllersMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/api/ex/")
public class ThrowingController {

    private static final Logger log = LoggerFactory.getLogger(ControllersMonitor.class);

    @Autowired



    @RequestMapping("/throw")
    public void throwSomeException() throws NullPointerException{
        throw new NullPointerException("My own null exc");
    }
    @RequestMapping("/notThrow")
    public String notThrowSomeException() throws NullPointerException{
        return "${test.message.example}";
    }

    @ExceptionHandler(NullPointerException.class)
    public ModelAndView handleThatException(NullPointerException ex){
        ModelAndView mav = new ModelAndView("thanksForRegistration");
        log.info("Exception handler {" + ex.getMessage() + "}");
        return mav;
    }

}
