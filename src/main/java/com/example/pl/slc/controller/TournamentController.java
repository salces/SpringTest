package com.example.pl.slc.controller;

import com.example.pl.slc.model.Player;
import com.example.pl.slc.model.Tournament;
import com.example.pl.slc.model.Tournament.ProceedingExternalTournamentStageNotAllowedException;
import com.example.pl.slc.model.Tournament.TooManyPhasesOfSameTypeException;
import com.example.pl.slc.model.TournamentStage;
import com.example.pl.slc.model.enums.TournamentPhase;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tournament")
public class TournamentController {

    //TODO: przykladowy turniej od 1/8
    private Tournament getExampleTournament() {
        Tournament tournament = new Tournament();

        TournamentStage[] eights = new TournamentStage[8];

        for (int i = 0; i < 8; i++) {
            Player firstPlayer = new Player();
                firstPlayer.setName("Name"+i);
                firstPlayer.setSurname("Surname"+i);
            Player secondPlayer = new Player();
                secondPlayer.setName("Name"+i*10+10);
                secondPlayer.setSurname("Surname"+i*10+10);
            eights[i] = new TournamentStage(TournamentPhase.ONE_EIGHT);

            eights[i].setFirstPlayer(firstPlayer);
            eights[i].setSecondPlayer(secondPlayer);

            try {
                tournament.addStage(eights[i]);
            } catch (TooManyPhasesOfSameTypeException e) {
                e.printStackTrace();
            }
        }

        for(int i=0; i<4; i++){
            try {
                eights[i].firstWins();
                eights[i+4].secondWins();
                tournament.proceedNextStage(eights[i],eights[i+4]);
            } catch (TooManyPhasesOfSameTypeException e) {
                e.printStackTrace();
            } catch (ProceedingExternalTournamentStageNotAllowedException e) {
                e.printStackTrace();
            }
        }


        return tournament;
    }

    //TODO: metoda testujaca rysowanie turnieju, do usuniecia
    @RequestMapping("/drawTest")
    public ModelAndView drawTournament() {
        ModelAndView mav = new ModelAndView("/tournament/draw");

        Tournament tournament = getExampleTournament();

        mav.addObject("tournament",tournament);

        return mav;

    }

}
