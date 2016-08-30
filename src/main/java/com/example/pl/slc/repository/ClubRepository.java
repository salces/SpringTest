package com.example.pl.slc.repository;

import com.example.pl.slc.model.Club;
import com.example.pl.slc.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

/**
 * Created by slc on 14.07.16.
 */
public interface ClubRepository extends JpaRepository<Club,Long> {

    Set<Club> findByCreatedByID(Long ID);
}
