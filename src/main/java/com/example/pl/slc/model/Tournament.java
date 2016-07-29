package com.example.pl.slc.model;


import com.example.pl.slc.model.enums.TournamentPhase;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Tournament {

    private List<TournamentStage> stages = new ArrayList<>();

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

    public TournamentStage proceedNextStage(TournamentStage firstStage, TournamentStage secondStage) throws NotEqualPhasesPassedException, TooManySameTypePhasesException {
        checkIfPassedPhasesEqual(firstStage, secondStage);

        TournamentPhase currentPhase = firstStage.getPhase();
        TournamentStage nextStage = new TournamentStage(currentPhase.getNextPhase());


        nextStage.setFirstPlayer(firstStage.getWinner());
        nextStage.setSecondPlayer(secondStage.getWinner());
        firstStage.setNextStage(nextStage);
        secondStage.setNextStage(nextStage);

        addStage(nextStage);
        return nextStage;
    }

    public List<TournamentStage> getStages() {
        return this.stages;
    }

    public void addStage(TournamentStage s) throws TooManySameTypePhasesException {
        checkIfNotTooManySamePhases(s);
        stages.add(s);
    }

    private void checkIfPassedPhasesEqual(TournamentStage firstStage, TournamentStage secondStage) throws NotEqualPhasesPassedException {
        if (!firstStage.getPhase().equals(secondStage.getPhase())) throw new NotEqualPhasesPassedException();
    }

    private void checkIfNotTooManySamePhases(TournamentStage stage) throws TooManySameTypePhasesException {
        long stagesPerPhase = stages.stream()
                                    .filter(s -> s.getPhase() == stage.getPhase())
                                    .count();
        if (stagesPerPhase >= stage.getPhase().getLvl()) {
            throw new TooManySameTypePhasesException();
        }
    }

    public static class TooManySameTypePhasesException extends Exception {

    }

    public static class NotEqualPhasesPassedException extends IllegalArgumentException {

    }

}
