package com.example.pl.slc.model;

import com.example.pl.slc.model.enums.TournamentPhase;


public class TournamentStage {

    private Player firstPlayer;
    private Player secondPlayer;

    private TournamentPhase phase;

    private TournamentStage nextStage;
    private Player winner;

    public TournamentStage(TournamentPhase phase){
        this.phase=phase;
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

    private void setPhase(TournamentPhase phase) {
        this.phase = phase;
    }

    public TournamentStage getNextStage() {
        return nextStage;
    }

    public void setNextStage(TournamentStage nextStage) {
        this.nextStage = nextStage;
    }

    public void secondWins() {
        this.winner = secondPlayer;
    }

    public void firstWins() {
        this.winner = firstPlayer;
    }

    public Player getWinner() {
        return winner;
    }
}
