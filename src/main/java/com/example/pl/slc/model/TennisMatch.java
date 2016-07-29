package com.example.pl.slc.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class TennisMatch {

    @Id
    @GeneratedValue
    private Long ID;

    @ManyToOne
    private Player firstPlayer;
    @ManyToOne
    private Player secondPlayer;

    @OneToMany(mappedBy = "tennisMatch")
    private List<MatchSet> sets;

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

    public List<MatchSet> getSets() {
        return sets;
    }

    public void setSets(List<MatchSet> sets) {
        this.sets = sets;
    }
}
