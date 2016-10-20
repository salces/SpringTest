package com.example.pl.slc.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
public class ClubMatch {

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

    public ClubMatch(Club home, Club away, LocalDateTime startDatetime) {
        this.home = home;
        this.away = away;
        this.startDatetime = startDatetime;
    }

}
