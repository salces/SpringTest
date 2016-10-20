package com.example.pl.slc.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
public class Round {


    @Id
    @GeneratedValue
    private Long ID;

    @OneToMany
    private Set<ClubMatch> matches;


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


}
