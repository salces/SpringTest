package com.example.pl.slc.controller;

import com.example.pl.slc.model.Player;
import com.example.pl.slc.repository.PlayerRepository;
import com.example.pl.slc.security.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.*;

/**
 * Created by slc on 13.07.16.
 */

@Controller
@RequestMapping("/player")
public class PlayerManagingController {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    LoggedUser loggedUser;

    @RequestMapping("/register")
    public ModelAndView getView() {
        ModelAndView mav = new ModelAndView("player/register");
        mav.addObject("player", new Player());
        mav.addObject("citizienshipCodes", getCitizienships());
        return mav;
    }

    @RequestMapping(value = "/register", params = "add")
    public ModelAndView addNewPlayer(@Valid @ModelAttribute Player player, BindingResult result) {
        ModelAndView mav = new ModelAndView("player/register");

        if(result.hasErrors()){
            mav.addObject("player", player);
        } else{
            mav.addObject("player", new Player());
            player.setCreatedBy(loggedUser.getLoggedUser());
            playerRepository.save(player);
            mav.addObject("successMessage", "New player " + player.getFullName() + " has been registered");
        }

        mav.addObject("citizienshipCodes", getCitizienships());
        return mav;
    }

    private List<String> getCitizienships() {
        List<String> availableCitizienships = new ArrayList<>();
        String[] citizienshipCodes = Locale.getISOCountries();

        for (String code : citizienshipCodes) {
            Locale l = new Locale("", code);
            availableCitizienships.add(l.getDisplayCountry(l));
        }
        Collections.sort(availableCitizienships);
        return availableCitizienships;
    }


}
