package com.example.pl.slc.model;

import javax.persistence.*;


@Entity
public class MatchSet {

    @Id
    @GeneratedValue
    private Long ID;

    @ManyToOne
    private TennisMatch tennisMatch;

    private int firstScore;
    private int secondScore;

    private int orderInMatch;

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public TennisMatch getTennisMatch() {
        return tennisMatch;
    }

    public void setTennisMatch(TennisMatch tennisMatch) {
        this.tennisMatch = tennisMatch;
    }

    public int getFirstScore() {
        return firstScore;
    }

    public void setFirstScore(int firstScore) {
        this.firstScore = firstScore;
    }

    public int getSecondScore() {
        return secondScore;
    }

    public void setSecondScore(int secondScore) {
        this.secondScore = secondScore;
    }

    public int getOrderInMatch() {
        return orderInMatch;
    }

    public void setOrderInMatch(int orderInMatch) {
        this.orderInMatch = orderInMatch;
    }
}
