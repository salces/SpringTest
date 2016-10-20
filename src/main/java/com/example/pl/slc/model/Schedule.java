package com.example.pl.slc.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
public class Schedule {

    @Id
    @GeneratedValue
    private Long ID;

    @OneToMany
    private List<Club> clubs = new ArrayList<>();

    @OneToMany
    private List<Round> rounds = new ArrayList<>();
}
