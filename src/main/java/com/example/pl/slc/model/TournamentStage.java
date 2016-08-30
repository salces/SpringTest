package com.example.pl.slc.model;

import com.example.pl.slc.model.enums.TournamentPhase;
import org.crsh.cli.Man;

import javax.persistence.*;

@Entity
public class TournamentStage {

    //<editor-fold desc="Fields">

    @Id
    @GeneratedValue
    private Long ID;

    @ManyToOne
    private Player firstPlayer;
    @ManyToOne
    private Player secondPlayer;

    @Enumerated(EnumType.STRING)
    private TournamentPhase phase;

    @ManyToOne
    private TournamentStage nextStage;
    @ManyToOne
    private Player winner;

    //</editor-fold desc="Fields">

    //<editor-fold desc="Own methods">

        public void firstWins(){
            this.winner = firstPlayer;
        }

        public void secondWins(){
            this.winner = secondPlayer;
        }
    //</editor-fold desc="Own methods">

    //<editor-fold desc="Constructors/Eq/toString">

    public TournamentStage() {
    }

    public TournamentStage(TournamentPhase phase) {
        this.phase = phase;
    }

    //</editor-fold desc="Constructors/Eq/toString">

    //<editor-fold desc="Get/Set">

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Player getFirstPlayer() {
        return firstPlayer;
    }

    public void setFirstPlayer(Player firstPlayer) {
        this.firstPlayer = firstPlayer;
    }

    public Player getSecondPlayer() {
        return secondPlayer;
    }

    public void setSecondPlayer(Player secondPlayer) {
        this.secondPlayer = secondPlayer;
    }

    public TournamentPhase getPhase() {
        return phase;
    }

    public void setPhase(TournamentPhase phase) {
        this.phase = phase;
    }

    public TournamentStage getNextStage() {
        return nextStage;
    }

    public void setNextStage(TournamentStage nextStage) {
        this.nextStage = nextStage;
    }

    public Player getWinner() {
        return winner;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }


    //</editor-fold desc="Get/Set">

}
