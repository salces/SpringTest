package com.example.model;


import com.example.pl.slc.model.ClubMatch;
import com.example.pl.slc.model.Round;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class RoundTest {


    private Round round = new Round();

    @Test
    public void shouldGetEndDate(){

        Set<ClubMatch> matches = new HashSet<>();

        ClubMatch match = null;
        for(int i = 0; i < 10; i++){
            match = new ClubMatch();
            LocalDateTime dateTime = LocalDateTime.of(2000+i,1,1,12,30);
            match.setStartDatetime(dateTime);
            matches.add(match);
        }

        round.setMatches(matches);

        assertEquals(round.getEnd(),match.getStartDatetime());

    }

    @Test
    public void shouldGetBeginningDate(){

        Set<ClubMatch> matches = new HashSet<>();

        ClubMatch match = null;
        for(int i = 10; i > 0; i--){
            match = new ClubMatch();
            LocalDateTime dateTime = LocalDateTime.of(2000+i,1,1,12,30);
            match.setStartDatetime(dateTime);
            matches.add(match);
        }

        round.setMatches(matches);

        assertEquals(round.getBeginning(),match.getStartDatetime());

    }

}
