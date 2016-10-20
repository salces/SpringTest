package com.example.pl.slc.repository;

import com.example.pl.slc.model.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TournamentRepository extends JpaRepository<Tournament,Long> {
}
