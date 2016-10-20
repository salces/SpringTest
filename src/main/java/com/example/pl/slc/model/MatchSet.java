package com.example.pl.slc.model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
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

}
