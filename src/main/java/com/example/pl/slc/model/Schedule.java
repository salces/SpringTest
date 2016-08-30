package com.example.pl.slc.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Entity
public class Schedule {

    //<editor-fold desc="Fields">

    @Id
    @GeneratedValue
    private Long ID;

    @OneToMany
    private List<Club> clubs = new ArrayList<>();

    @OneToMany
    private List<Round> rounds = new ArrayList<>();

    //</editor-fold desc="Fields">

    //<editor-fold desc="Get/Set">

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public List<Club> getClubs() {
        return clubs;
    }

    public void setClubs(List<Club> clubs) {
        this.clubs = clubs;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }


    //</editor-fold desc="Get/Set">

}
