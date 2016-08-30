package com.example.controller;


import com.example.pl.slc.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;



public class RegisterControllerTest extends BaseControllerTest{

    @Autowired
    private WebApplicationContext wac;

    MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void shouldGetForm() throws Exception {
        mockMvc.perform(get("/register")).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(view().name("register"));
    }

    @Test
    public void shouldGetErrorsDuringRegistration() throws Exception{
        mockMvc.perform(post("/register").
                    param("register","register")).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(model().errorCount(4)).
                andExpect(view().name("register"));

    }

    @Test
    public void shouldNotGetErrorDuringRegistration() throws Exception{
        mockMvc.perform(post("/register").
                    param("register","register").
                    param("username","AbsoulteUniqueUsername").
                    param("password","UPPERCASE#withproperlength").
                    param("email","test@email.com")).
                andDo(print()).
                andExpect(status().isOk()).
                andExpect(model().hasNoErrors()).
                andExpect(view().name("thanksForRegistration"));
    }

}
