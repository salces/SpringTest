package com.example.pl.slc.model.enums;


import java.util.HashMap;
import java.util.Map;

public enum TournamentPhase {
    FINAL(1),
    SEMIFINAL(2),
    QUARTERFINAL(4),
    ONE_EIGHT(8),
    ONE_SIXTEENTH(16),
    ONE_THIRTY_SECOND(32),
    ONE_SIXTY_FOURTH(64);

    private int lvl;

    TournamentPhase(int lvl){
        this.lvl = lvl;
    }

    public int getLvl() {
        return lvl;
    }

    private static Map<Integer,TournamentPhase> getPhaseToIntMapping(){
        Map<Integer,TournamentPhase> map = new HashMap<>();

        for(TournamentPhase phase : TournamentPhase.values()){
            map.put(phase.lvl,phase);
        }

        return map;
    }

    public TournamentPhase getNextPhase(){
        int nextStageLvl = this.lvl/2;

        return fromInt(nextStageLvl);
    }

    public static TournamentPhase fromInt(int lvl) {
        return getPhaseToIntMapping().get(lvl);
    }
}
