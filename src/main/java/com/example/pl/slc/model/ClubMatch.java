package com.example.pl.slc.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;



@Entity
public class ClubMatch {

    @Id
    @GeneratedValue
    private Long ID;

    @ManyToOne
    private Club home;

    @ManyToOne
    private Club away;

    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date startDatetime;


    @OneToMany
    private List<Player> homePlayersAvailableForMatches;
    @OneToMany
    private List<Player> awayPlayersAvailableForMatches;

    @OneToMany
    private List<TennisMatch> individualTennisMatches;

    public ClubMatch(){
    }

    public ClubMatch(Club home, Club away, Date startDatetime) {
        this.home = home;
        this.away = away;
        this.startDatetime = startDatetime;
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Club getHome() {
        return home;
    }

    public void setHome(Club home) {
        this.home = home;
    }

    public Club getAway() {
        return away;
    }

    public void setAway(Club away) {
        this.away = away;
    }

    public Date getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(Date startDatetime) {
        this.startDatetime = startDatetime;
    }

    public List<Player> getHomePlayersAvailableForMatches() {
        return homePlayersAvailableForMatches;
    }

    public void setHomePlayersAvailableForMatches(List<Player> homePlayersAvailableForMatches) {
        this.homePlayersAvailableForMatches = homePlayersAvailableForMatches;
    }

    public List<Player> getAwayPlayersAvailableForMatches() {
        return awayPlayersAvailableForMatches;
    }

    public void setAwayPlayersAvailableForMatches(List<Player> awayPlayersAvailableForMatches) {
        this.awayPlayersAvailableForMatches = awayPlayersAvailableForMatches;
    }

    public List<TennisMatch> getIndividualTennisMatches() {
        return individualTennisMatches;
    }

    public void setIndividualTennisMatches(List<TennisMatch> individualTennisMatches) {
        this.individualTennisMatches = individualTennisMatches;
    }

}
