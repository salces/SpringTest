package com.example.pl.slc.model;


import com.example.pl.slc.model.enums.TournamentPhase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
public class Tournament {

    //<editor-fold desc="Fields">

    @Id
    @GeneratedValue
    private Long ID;

    @OneToMany
    private List<TournamentStage> stages = new ArrayList<>();

    //</editor-fold desc="Fields">

    //<editor-fold desc="Own methods">

    public List<Player> getCompetitors() {
        return Stream.concat(
                stages.stream()
                        .map(s -> s.getFirstPlayer())
                        .distinct()
                        .collect(Collectors.toList())
                        .stream(),
                stages.stream()
                        .map(s -> s.getSecondPlayer())
                        .distinct()
                        .collect(Collectors.toList())
                        .stream()).distinct().collect(Collectors.toList());
    }

    public TournamentStage proceedNextStage(TournamentStage firstStage, TournamentStage secondStage)
            throws NotEqualPhasesPassedException,
            TooManyPhasesOfSameTypeException,
            ProceedingExternalTournamentStageNotAllowedException {

        checkIfPassedPhasesEqual(firstStage, secondStage);
        checkIfStageBelongsToThis(firstStage);
        checkIfStageBelongsToThis(secondStage);

        TournamentPhase currentPhase = firstStage.getPhase();
        TournamentStage nextStage = new TournamentStage(currentPhase.getNextPhase());


        nextStage.setFirstPlayer(firstStage.getWinner());
        nextStage.setSecondPlayer(secondStage.getWinner());
        firstStage.setNextStage(nextStage);
        secondStage.setNextStage(nextStage);

        addStage(nextStage);
        return nextStage;
    }

    public TournamentPhase getStartPhase() {
        int startingLvl = stages.stream().max((s1, s2) ->
                s1.getPhase().getLvl() > s2.getPhase().getLvl() ?
                        s1.getPhase().getLvl() :
                        s2.getPhase().getLvl()).
                get().getPhase().getLvl();
        return TournamentPhase.fromInt(startingLvl);
    }

    public void addStage(TournamentStage s) throws TooManyPhasesOfSameTypeException {
        checkIfNotTooManySamePhases(s);
        stages.add(s);
    }

    private void checkIfStageBelongsToThis(TournamentStage stage) throws ProceedingExternalTournamentStageNotAllowedException {
        if (!stages.contains(stage)) throw new ProceedingExternalTournamentStageNotAllowedException();
    }

    private void checkIfPassedPhasesEqual(TournamentStage firstStage, TournamentStage secondStage) throws NotEqualPhasesPassedException {
        if (!firstStage.getPhase().equals(secondStage.getPhase())) throw new NotEqualPhasesPassedException();
    }

    private void checkIfNotTooManySamePhases(TournamentStage stage) throws TooManyPhasesOfSameTypeException {
        long stagesPerPhase = stages.stream()
                .filter(s -> s.getPhase() == stage.getPhase())
                .count();
        if (stagesPerPhase >= stage.getPhase().getLvl()) {
            throw new TooManyPhasesOfSameTypeException();
        }
    }

    //</editor-fold desc="Own methods">

    //<editor-fold desc="Get/Set">

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public List<TournamentStage> getStages() {
        return stages;
    }

    public void setStages(List<TournamentStage> stages) {
        this.stages = stages;
    }


    //</editor-fold desc="Get/Set">

    //<editor-fold desc="Inner/Exceptions">

    public static class TooManyPhasesOfSameTypeException extends Exception {

    }

    public static class NotEqualPhasesPassedException extends IllegalArgumentException {

    }

    public static class ProceedingExternalTournamentStageNotAllowedException extends Exception {
    }

    //</editor-fold desc="Inner/Exceptions">

}
