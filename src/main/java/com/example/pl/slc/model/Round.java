package com.example.pl.slc.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Round {

    //<editor-fold desc="Fields">


    @Id
    @GeneratedValue
    private Long ID;

    @OneToMany
    private Set<ClubMatch> matches;

    //</editor-fold desc="Fields">

    //<editor-fold desc="Own methods">


    public LocalDateTime getBeginning(){
        return matches.stream().min((m1,m2) -> {
            LocalDateTime firstDate = m1.getStartDatetime();
            LocalDateTime secondDate = m2.getStartDatetime();
            return firstDate.isAfter(secondDate) ?
                    1  :
                    -1 ;
        }).get().getStartDatetime();
    }

    public LocalDateTime getEnd(){
        return matches.stream().max((m1,m2) -> {
            LocalDateTime firstDate = m1.getStartDatetime();
            LocalDateTime secondDate = m2.getStartDatetime();
            return firstDate.isAfter(secondDate) ?
                    1  :
                    -1 ;
        }).get().getStartDatetime();
    }

    //</editor-fold desc="Own methods">

    //<editor-fold desc="Get/Set">

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Set<ClubMatch> getMatches() {
        return matches;
    }

    public void setMatches(Set<ClubMatch> matches) {
        this.matches = matches;
    }

    //</editor-fold desc="Get/Set">

}
