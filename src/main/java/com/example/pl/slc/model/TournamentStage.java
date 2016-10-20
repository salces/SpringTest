package com.example.pl.slc.model;

import com.example.pl.slc.model.enums.TournamentPhase;
import com.google.gson.annotations.Expose;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
@Entity
public class TournamentStage implements Serializable {

    @Id
    @GeneratedValue
    @Expose
    private Long ID;

    @ManyToOne
    @Expose
    private Player firstPlayer;
    @ManyToOne
    @Expose
    private Player secondPlayer;

    @Enumerated(EnumType.STRING)
    @Expose
    private TournamentPhase phase;

    @Expose
    private int phaseCode;

    @ManyToOne
    private TournamentStage nextStage;

    @ManyToOne
    @Expose
    private Player winner;

    public void firstWins(){
            this.winner = firstPlayer;
        }

    public void secondWins(){
            this.winner = secondPlayer;
        }

    public TournamentStage(TournamentPhase phase) {
        this.setPhase(phase);
    }

    public void setPhase(TournamentPhase phase){
        this.phase = phase;
        this.phaseCode = phase.getLvl();
    }
}
