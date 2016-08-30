package com.example.pl.slc.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;


@Entity
public class ClubMatch {

    //<editor-fold desc="Fields">

    @Id
    @GeneratedValue
    private Long ID;

    @ManyToOne
    private Club home;

    @ManyToOne
    private Club away;

    private LocalDateTime startDatetime;

    @OneToMany
    private List<TennisMatch> individualTennisMatches;

    //</editor-fold desc="Fields">

    //<editor-fold desc="Constructors/Eq/toString">

    public ClubMatch() {
    }

    public ClubMatch(Club home, Club away, LocalDateTime startDatetime) {
        this.home = home;
        this.away = away;
        this.startDatetime = startDatetime;
    }

    //</editor-fold desc="Constructors+eq+toString">

    //<editor-fold desc="Get/Set">

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

    public LocalDateTime getStartDatetime() {
        return startDatetime;
    }

    public void setStartDatetime(LocalDateTime startDatetime) {
        this.startDatetime = startDatetime;
    }

    public List<TennisMatch> getIndividualTennisMatches() {
        return individualTennisMatches;
    }

    public void setIndividualTennisMatches(List<TennisMatch> individualTennisMatches) {
        this.individualTennisMatches = individualTennisMatches;
    }

    //</editor-fold desc="Get/Set">

}
