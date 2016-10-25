package com.example.pl.slc.controller;

import com.example.pl.slc.model.ImageFile;
import com.example.pl.slc.model.Player;
import com.example.pl.slc.model.Tournament;
import com.example.pl.slc.model.Tournament.ProceedingExternalTournamentStageNotAllowedException;
import com.example.pl.slc.model.Tournament.TooManyPhasesOfSameTypeException;
import com.example.pl.slc.model.TournamentStage;
import com.example.pl.slc.model.enums.TournamentPhase;
import com.example.pl.slc.repository.ImageFileRepository;
import com.example.pl.slc.repository.PlayerRepository;
import com.example.pl.slc.repository.TournamentRepository;
import com.example.pl.slc.repository.TournamentStageRepository;
import com.example.pl.slc.service.ImageUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.xml.internal.ws.message.stream.StreamMessage;
import lombok.Getter;
import lombok.Setter;
import org.aspectj.util.FileUtil;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;

import javax.xml.ws.RequestWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/tournament")
public class TournamentController {

    Gson gson;

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TournamentStageRepository tournamentStageRepository;

    @Autowired
    TournamentRepository tournamentRepository;

    @Autowired
    ImageFileRepository imageFileRepository;

    @Autowired
    ImageUtil imageUtil;


    public TournamentController() {
        gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    }

    //TODO: przykladowy turniej od 1/8
    private Tournament getExampleTournament() {
        Tournament tournament = new Tournament();
        TournamentStage[] sixteens = new TournamentStage[16];

        for (int i = 0; i < 16; i++) {
            Player firstPlayer = new Player();
            firstPlayer.setName("Name" + i);
            firstPlayer.setSurname("Surname" + i);
            Player secondPlayer = new Player();
            secondPlayer.setName("Name" + i * 10 + 10);
            secondPlayer.setSurname("Surname" + i * 10 + 10);
            sixteens[i] = new TournamentStage(TournamentPhase.ONE_SIXTEENTH);
            sixteens[i].setID(new Long(i));

            sixteens[i].setFirstPlayer(firstPlayer);
            sixteens[i].setSecondPlayer(secondPlayer);

            try {
                tournament.addStage(sixteens[i]);
            } catch (TooManyPhasesOfSameTypeException e) {
                e.printStackTrace();
            }
        }


        TournamentStage[] eights = new TournamentStage[8];
        int a = 0;
        for (int i = 0; i < 16; i += 2) {
            sixteens[i].firstWins();
            sixteens[i + 1].secondWins();

            try {
                eights[a++] = tournament.proceedNextStage(sixteens[i], sixteens[i + 1]);
            } catch (TooManyPhasesOfSameTypeException e) {
                e.printStackTrace();
            } catch (ProceedingExternalTournamentStageNotAllowedException e) {
                e.printStackTrace();
            }
        }
        TournamentStage[] fours = new TournamentStage[4];
        int j = 0;
        for (int i = 0; i < 8; i += 2) {
            try {
                eights[i].firstWins();
                eights[i + 1].secondWins();
                fours[j++] = tournament.proceedNextStage(eights[i], eights[i + 1]);
            } catch (TooManyPhasesOfSameTypeException e) {
                e.printStackTrace();
            } catch (ProceedingExternalTournamentStageNotAllowedException e) {
                e.printStackTrace();
            }
        }
        TournamentStage[] finall = new TournamentStage[2];
        for (int i = 0; i < 2; i++) {
            try {
                fours[i].firstWins();
                fours[i + 2].secondWins();
                finall[i] = tournament.proceedNextStage(fours[i], fours[i + 2]);
            } catch (TooManyPhasesOfSameTypeException e) {
                e.printStackTrace();
            } catch (ProceedingExternalTournamentStageNotAllowedException e) {
                e.printStackTrace();
            }
        }
        finall[0].firstWins();
        finall[1].secondWins();
        try {
            tournament.proceedNextStage(finall[0], finall[1]);
        } catch (TooManyPhasesOfSameTypeException e) {
            e.printStackTrace();
        } catch (ProceedingExternalTournamentStageNotAllowedException e) {
            e.printStackTrace();
        }
        return tournament;
    }

    @RequestMapping("/draw")
    public ModelAndView drawTournament() {
        ModelAndView mav = new ModelAndView("/tournament/draw");
        mav.addObject("tournament", gson.toJson(getExampleTournament()));

        return mav;
    }

    @RequestMapping("/create")
    public ModelAndView getView() {
        ModelAndView mav = new ModelAndView("/tournament/createTournament");
        List<TournamentPhase> phases = Arrays.asList(TournamentPhase.values());
        mav.addObject("availablePhases", phases);
        mav.addObject("allPlayers", playerRepository.findAll());
        mav.addObject("someObj", new Object());
        return mav;
    }

    @RequestMapping(value = "/create", params = "create", method = POST)
    public ModelAndView createTournament(@RequestParam("phase") TournamentPhase phase,
                                         @RequestParam("players") Long[] playersID,
                                         @RequestParam("name") String name,
                                         @RequestParam("tournamentImg") MultipartFile file,
                                         @ModelAttribute Object object) throws IOException {
        ModelAndView mav;
        if (!isModelCorrect(phase, playersID, name, file)) {
            String failureMessage = getFailureMessage(phase, playersID, name, file);
            mav = new ModelAndView("tournament/createTournament");
            List<TournamentPhase> phases = Arrays.asList(TournamentPhase.values());
            mav.addObject("availablePhases", phases);
            mav.addObject("allPlayers", playerRepository.findAll());
            mav.addObject("someObj", new Object());
            mav.addObject("failureMessage", failureMessage);
        } else {
            mav = new ModelAndView("/tournament/draw");
            mav.addObject("tournament", gson.toJson(getTournament(phase, playersID, name, file)));
        }
        return mav;
    }

    private String getFailureMessage(TournamentPhase phase, Long[] playersID, String name, MultipartFile file) {
        if (phase.getLvl() * 2 != playersID.length) {
            return "Wrong number of players selected!";
        }

        if (name == null || name.equals("")) {
            return "Name of tournament cannot be empty";
        }

        return "Unknown problem, please try again!";
    }

    private boolean isModelCorrect(TournamentPhase phase, Long[] playersID, String name, MultipartFile file) {
        if (phase.getLvl() * 2 != playersID.length) {
            return false;
        }

        if (name == null || name.equals("")) {
            return false;
        }
        return true;
    }

    private Tournament getTournament(TournamentPhase phase, Long[] playersID, String name, MultipartFile file) throws IOException {
        List<Player> players = new ArrayList<>();
        Arrays.asList(playersID)
                .stream()
                .forEach(id -> {
                    players.add(playerRepository.findOne(id));
                });
        Collections.shuffle(players);

        ImageFile savedImage = imageFileRepository.save(imageUtil.toImageFile(file));


        Tournament tournament = new Tournament(name, savedImage);
        for (int i = 0; i < phase.getLvl(); i++) {
            TournamentStage stage = new TournamentStage(phase);
            stage.setFirstPlayer(players.get(i));
            stage.setSecondPlayer(players.get(i + phase.getLvl()));
            TournamentStage savedStage = tournamentStageRepository.saveAndFlush(stage);
            try {
                tournament.addStage(savedStage);
            } catch (TooManyPhasesOfSameTypeException e) {
                e.printStackTrace();
            }
        }
        Tournament savedTournament = tournamentRepository.saveAndFlush(tournament);
        return savedTournament;
    }

    @RequestMapping("/presentation")
    public ModelAndView presentAllTournaments() {
        ModelAndView mav = new ModelAndView("tournament/presentation");
        mav.addObject("tournamentList", tournamentRepository.findAll());
        return mav;
    }

    @RequestMapping("/draw/{ID}")
    public ModelAndView drawTournamentById(@PathVariable Long ID) {
        ModelAndView mav = new ModelAndView("tournament/draw");
        mav.addObject("tournament", gson.toJson(tournamentRepository.findOne(ID)));
        return mav;
    }


}
