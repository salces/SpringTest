package com.example.pl.slc.model;


import com.example.pl.slc.model.enums.TournamentPhase;
import com.google.gson.annotations.Expose;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
public class Tournament implements Serializable{

    @Id
    @GeneratedValue
    @Expose
    private Long ID;

    @Expose
    private String name;

    @Expose
    @ManyToOne
    private ImageFile image;

    @OneToMany
    @Expose
    private List<TournamentStage> stages = new ArrayList<>();



    public Tournament(String name, ImageFile image){
        this.name = name;
        this.image = image;
    }

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

    public String getHtmlImage(){
        if(this.image != null){
            return this.image.getHtmlImage();
        } else {
            return "http://vignette4.wikia.nocookie.net/mrmen/images/5/52/Small.gif/revision/latest?cb=20100731114437";
        }
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


    public static class TooManyPhasesOfSameTypeException extends Exception {

    }

    public static class NotEqualPhasesPassedException extends IllegalArgumentException {

    }

    public static class ProceedingExternalTournamentStageNotAllowedException extends Exception {
    }

}
