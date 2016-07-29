package com.example.controller;

import com.example.SpringTestApplication;
import com.example.pl.slc.controller.ControllerConfig;
import com.example.pl.slc.repository.ClubRepository;
import com.example.pl.slc.repository.PlayerRepository;
import com.example.pl.slc.repository.UserRepository;
import com.example.pl.slc.security.LoggedUser;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.mockito.Mockito.mock;

/**
 * Created by slc on 19.07.16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(classes = BaseControllerTest.Config.class)
@SpringApplicationConfiguration(classes = SpringTestApplication.class)
@WebAppConfiguration
public abstract class BaseControllerTest {

//    @Configuration
//    @Import(ControllerConfig.class)
//    static class Config{
//
//        @Bean
//        public PlayerRepository playerRepository(){
//            return mock(PlayerRepository.class);
//        }
//
//        @Bean
//        public ClubRepository clubRepository(){
//            return mock(ClubRepository.class);
//        }
//
//        @Bean
//        public UserRepository userRepository(){
//            return mock(UserRepository.class);
//        }
//
//        @Bean
//        public LoggedUser loggedUser(){
//            return mock(LoggedUser.class);
//        }
//
//    }
}
