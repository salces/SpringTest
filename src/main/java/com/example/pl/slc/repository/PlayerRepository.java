package com.example.pl.slc.repository;

import com.example.pl.slc.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;


public interface PlayerRepository extends JpaRepository<Player,Long>{
    Set<Player> findByCreatedByID(Long ID);

    @Query("SELECT p FROM Player p WHERE p.createdBy.ID = ?1 AND p.currentClub IS NULL")
    Set<Player> findByCreatedByIDWithNoClub(Long ID);

    Set<Player> findByCurrentClubID(Long ID);
}
