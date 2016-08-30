package com.example.model;

import com.example.pl.slc.model.Player;
import com.example.pl.slc.model.Tournament;
import com.example.pl.slc.model.Tournament.TooManyPhasesOfSameTypeException;
import com.example.pl.slc.model.TournamentStage;
import com.example.pl.slc.model.enums.TournamentPhase;
import org.junit.Test;

import java.util.List;

import static com.example.pl.slc.model.Tournament.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class TournamentTest {

    Tournament tournament = new Tournament();

    @Test
    public void shouldGetUniqueCompetitors() {
        for (int i = 0; i < 10; i++) {
            Player firstPlayerInStage = new Player();
            Player secondPlayerInStage = new Player();
            firstPlayerInStage.setID(new Long(i));
            secondPlayerInStage.setID(new Long(i));

            TournamentStage stage = new TournamentStage(TournamentPhase.ONE_SIXTY_FOURTH);
            stage.setFirstPlayer(firstPlayerInStage);
            stage.setSecondPlayer(secondPlayerInStage);
            try {
                tournament.addStage(stage);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        List<Player> competitorsFromTournament = tournament.getCompetitors();
        assertEquals(competitorsFromTournament.size(), 10L);
    }

    @Test(expected = TooManyPhasesOfSameTypeException.class)
    public void shouldThrowExceptionWhileAddingSecondFinalStage() throws Throwable {
        TournamentStage finalStage = new TournamentStage(TournamentPhase.FINAL);

        tournament.addStage(finalStage);
        tournament.addStage(finalStage);
    }

    @Test(expected = TooManyPhasesOfSameTypeException.class)
    public void shouldThrowExceptionWhileAddingFifthQuarterStage() throws Throwable {
        TournamentStage quarterFinalStage = new TournamentStage(TournamentPhase.QUARTERFINAL);

        for(int i=0; i<4; i++)
        tournament.addStage(quarterFinalStage);

        tournament.addStage(quarterFinalStage);
    }

    @Test
    public void shouldNotThrowExceptionWhileAddingFourthQuarterStage() throws Throwable {
        TournamentStage quarterFinalStage = new TournamentStage(TournamentPhase.QUARTERFINAL);

        for(int i=0; i<4; i++)
            tournament.addStage(quarterFinalStage);

    }

    @Test
    public void shouldCreateSemiFinalStage() throws Throwable{



        Player[] quarterFinalCompetitors = new Player[4];

        for(int i=0; i<4; i++){
            Player player = new Player();
            player.setName("Name" + i);
            player.setSurname("Surname" + i);
            player.setCitizienship("Citizienship" + i);

            quarterFinalCompetitors[i] = player;
        }

        TournamentStage firstQuarterFinalStage = new TournamentStage(TournamentPhase.QUARTERFINAL);
            firstQuarterFinalStage.setFirstPlayer(quarterFinalCompetitors[0]);
            firstQuarterFinalStage.setSecondPlayer(quarterFinalCompetitors[1]);
            firstQuarterFinalStage.firstWins();

        TournamentStage secondQuarterFinalStage = new TournamentStage(TournamentPhase.QUARTERFINAL);
            secondQuarterFinalStage.setFirstPlayer(quarterFinalCompetitors[2]);
            secondQuarterFinalStage.setSecondPlayer(quarterFinalCompetitors[3]);
            secondQuarterFinalStage.secondWins();

        tournament.addStage(firstQuarterFinalStage);
        tournament.addStage(secondQuarterFinalStage);

        int currentStageSize = tournament.getStages().size();

        TournamentStage returnedStage = tournament.proceedNextStage(firstQuarterFinalStage,secondQuarterFinalStage);

        assertNotNull(returnedStage);
        assertEquals(returnedStage.getPhase(),TournamentPhase.SEMIFINAL);
        assertEquals(returnedStage.getFirstPlayer(),firstQuarterFinalStage.getWinner());
        assertEquals(returnedStage.getSecondPlayer(),secondQuarterFinalStage.getWinner());
        assertNotNull(firstQuarterFinalStage.getNextStage());
        assertNotNull(secondQuarterFinalStage.getNextStage());
        assertEquals(tournament.getStages().size(),currentStageSize+1);

    }

    @Test(expected = NotEqualPhasesPassedException.class)
    public void shouldThrowNotEqualPhasesPassedException() throws Throwable{
        TournamentStage quarterFinalStage = new TournamentStage(TournamentPhase.QUARTERFINAL);
        TournamentStage semiFinalStage = new TournamentStage(TournamentPhase.SEMIFINAL);

        tournament.proceedNextStage(quarterFinalStage,semiFinalStage);
    }


}
