package com.example.pl.slc.controller;

import com.example.pl.slc.model.Club;
import com.example.pl.slc.model.ImageFile;
import com.example.pl.slc.model.Player;
import com.example.pl.slc.repository.ClubRepository;
import com.example.pl.slc.repository.ImageFileRepository;
import com.example.pl.slc.repository.PlayerRepository;
import com.example.pl.slc.security.LoggedUser;
import com.example.pl.slc.service.ImageUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Set;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/club")
public class ClubController {

    @Autowired
    private ClubRepository clubRepository;
    @Autowired
    private PlayerRepository playerRepository;
    @Autowired
    private LoggedUser loggedUser;
    @Autowired
    private ImageFileRepository imageFileRepository;
    @Autowired
    private ImageUtil imageUtil;


    @RequestMapping("/register")
    public ModelAndView getRegisterView() {
        ModelAndView mav = new ModelAndView("club/register");
        mav.addObject("club", new Club());
        return mav;
    }

    @RequestMapping(value = "/register", params = "register", method = POST)
    public ModelAndView registerNewClub(@Valid @ModelAttribute Club club, @RequestParam("clubImg") MultipartFile file, BindingResult result) throws IOException {
        ModelAndView mav = new ModelAndView("club/register");
        if (result.hasErrors()) {
            mav.addObject("club", club);
        } else {
            mav.addObject("club", new Club());
            ImageFile savedImage = imageFileRepository.save(imageUtil.toImageFile(file));
            club.setCreatedBy(loggedUser.getLoggedUser());
            club.setImage(savedImage);
            Club savedClub = clubRepository.save(club);
            mav.addObject("successMessage", "New club " + savedClub.getFullName() + " has been registered");
        }
        return mav;
    }

    @RequestMapping("/addPlayer")
    public ModelAndView getView() {
        ModelAndView mav = new ModelAndView("/club/add_player");
        mav.addObject("clubList", clubRepository.findByCreatedByID(loggedUser.getLoggedUserID()));
        mav.addObject("playersList", playerRepository.findByCreatedByIDWithNoClub(loggedUser.getLoggedUserID()));
        mav.addObject("bindingForm", new BindPlayersToClubForm());
        return mav;
    }

    @RequestMapping(value = "/addPlayer", params = "add")
    public ModelAndView addPlayersToClub(@ModelAttribute BindPlayersToClubForm bindingForm, BindingResult result) {

        performBinding(bindingForm);

        ModelAndView mav = new ModelAndView("/club/add_player");
        Set<Club> clubList = clubRepository.findByCreatedByID(loggedUser.getLoggedUserID());
        Set<Player> playersList = playerRepository.findByCreatedByIDWithNoClub(loggedUser.getLoggedUserID());
        mav.addObject("clubList", clubList);
        mav.addObject("playersList", playersList);
        mav.addObject("bindingForm", new BindPlayersToClubForm());
        mav.addObject("successMessage", "Players (" + bindingForm.players.size()
                + ") have been added to " + bindingForm.club.getFullName());
        return mav;
    }

    @RequestMapping(value = "/presentation")
    public ModelAndView getAllClubsPresentation() {
        ModelAndView mav = new ModelAndView("/club/presentation");
        mav.addObject("clubList", clubRepository.findAll());
        return mav;
    }

    private void performBinding(BindPlayersToClubForm bindingForm) {
        Club club = bindingForm.getClub();
        club.addPlayers(bindingForm.getPlayers());
        for (Player p : bindingForm.getPlayers()) {
            p.setCurrentClub(club);
        }
        clubRepository.flush();
    }

    @Getter
    @Setter
    @NoArgsConstructor
    public static class BindPlayersToClubForm {
        private Club club;
        private Set<Player> players;
    }

}
