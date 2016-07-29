package com.example.pl.slc.controller;

import com.example.pl.slc.model.Club;
import com.example.pl.slc.model.Player;
import com.example.pl.slc.repository.ClubRepository;
import com.example.pl.slc.repository.PlayerRepository;
import com.example.pl.slc.security.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Set;

@Controller
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private LoggedUser loggedUser;

    @RequestMapping("/register")
    public ModelAndView getRegisterView() {
        ModelAndView mav = new ModelAndView("club/register");
        mav.addObject("club", new Club());
        return mav;
    }

    @RequestMapping(value = "/register", params = "register")
    public ModelAndView registerNewClub(@Valid @ModelAttribute Club club, BindingResult result) {
        ModelAndView mav = new ModelAndView("club/register");
        if (result.hasErrors()) {
            mav.addObject("club", club);
        } else {
            mav.addObject("club", new Club());
            club.setCreatedBy(loggedUser.getLoggedUser());
            clubRepository.save(club);
        }
        return mav;
    }

    @RequestMapping("/addPlayer")
    public ModelAndView getView(){
        ModelAndView mav = new ModelAndView("/club/add_player");
        mav.addObject("clubList",clubRepository.findByCreatedByID(loggedUser.getLoggedUserID()));
        mav.addObject("playersList",playerRepository.findByCreatedByIDWithNoClub(loggedUser.getLoggedUserID()));
        mav.addObject("bindingForm",new BindPlayersToClubForm());
        return mav;
    }

    @RequestMapping(value = "/addPlayer",params = "add")
    public ModelAndView addPlayersToClub(@ModelAttribute BindPlayersToClubForm bindingForm,BindingResult result){

        performBinding(bindingForm);

        ModelAndView mav = new ModelAndView("/club/add_player");
        Set<Club> clubList = clubRepository.findByCreatedByID(loggedUser.getLoggedUserID());
        Set<Player> playersList = playerRepository.findByCreatedByIDWithNoClub(loggedUser.getLoggedUserID());
        mav.addObject("clubList",clubList);
        mav.addObject("playersList",playersList);
        mav.addObject("bindingForm",new BindPlayersToClubForm());
        return mav;
    }

    @RequestMapping(value = "/presentation")
    public ModelAndView getAllClubsPresentation(){
        ModelAndView mav = new ModelAndView("/club/presentation");
        mav.addObject("clubList",clubRepository.findAll());
        return mav;
    }

    private void performBinding(BindPlayersToClubForm bindingForm){
        Club club = bindingForm.getClub();
        club.addPlayers(bindingForm.getPlayers());
        for(Player p:bindingForm.getPlayers()){
            p.setCurrentClub(club);
        }
        clubRepository.flush();
    }

    public static class BindPlayersToClubForm{
        private Club club;
        private Set<Player> players;

        public Club getClub() {
            return club;
        }

        public void setClub(Club club) {
            this.club = club;
        }

        public Set<Player> getPlayers() {
            return players;
        }

        public void setPlayers(Set<Player> players) {
            this.players = players;
        }
    }

}
