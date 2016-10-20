package com.example.pl.slc.model;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
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
}
