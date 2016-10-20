package com.example.pl.slc.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
public class League {

    @Id
    @GeneratedValue
    private Long ID;

    private String titleSponsor;
    private String name;

    @ManyToOne
    private User createdBy;


    @OneToOne
    @JoinColumn
    private Schedule schedule;

    private Schedule generateSchedule(Set<Club> clubsSet, LocalDateTime startOfLeague) throws OddClubsException {

        checkIfNumberOfClubsIsEven(clubsSet);

        Schedule schedule = new Schedule();
        List<Club> shuffledClubs = fromSetToShuffledList(clubsSet);
        schedule.setClubs(shuffledClubs);
        List<Round> seasonRounds = generateSeasonRounds(shuffledClubs);
        schedule.setRounds(seasonRounds);

        return schedule;
    }

    private void checkIfNumberOfClubsIsEven(Set<Club> clubs) throws OddClubsException {
        if (clubs.size() % 2 == 1)
            throw new OddClubsException("League can be constructed only from even number of clubs");
    }

    private List<Club> fromSetToShuffledList(Set<Club> clubsSet) {
        List<Club> clubs = new ArrayList<>();
        clubs.addAll(clubsSet);
        Collections.shuffle(clubs);
        return clubs;
    }

    private List<Round> generateSeasonRounds(List<Club> clubs) {
        List<Round> allRounds = new ArrayList<>();
        List<Round> autumnRounds = generateAutumnRounds(clubs);
        List<Round> springRounds = invertAutumnRounds(autumnRounds);

        allRounds.addAll(autumnRounds);
        allRounds.addAll(springRounds);

        return allRounds;
    }

    private List<Round> generateAutumnRounds(List<Club> clubs) {
        int nbrOfClubs = clubs.size();

        List<Round> rounds = new ArrayList<>();

        for (int i = 0; i < clubs.size() - 1; i++) {
            Round round = new Round();
            Set<ClubMatch> matches = new HashSet<>();
            for (int j = 0; j < clubs.size() / 2; j++) {
                ClubMatch match = new ClubMatch();
                match.setHome(clubs.get(j));
                match.setAway(clubs.get(nbrOfClubs - j - 1));
                matches.add(match);
            }
            round.setMatches(matches);
            roundRobin(clubs);

            rounds.add(round);
        }
        return rounds;
    }

    private List<Round> invertAutumnRounds(List<Round> autumn) {
        List<Round> spring = new ArrayList<>();

        for (Round r : autumn) {
            Round springRound = new Round();
            Set<ClubMatch> matches = new HashSet<>();
            for (ClubMatch m : r.getMatches()) {
                ClubMatch invertedMatch = new ClubMatch();
                invertedMatch.setHome(m.getAway());
                invertedMatch.setAway(m.getHome());
                matches.add(invertedMatch);
            }
            springRound.setMatches(matches);
            spring.add(springRound);
        }

        return spring;
    }

    private void roundRobin(List<Club> clubs) {
        int lastIndex = clubs.size() -1;
        Club lastElement = clubs.get(lastIndex);
        clubs.remove(lastIndex);
        clubs.add(1, lastElement);
    }

    public League(Set<Club> clubs) throws OddClubsException {
                 this.schedule = generateSchedule(clubs,null);

    }

    private class OddClubsException extends Throwable {
        public OddClubsException(String s) {
        }
    }
}
