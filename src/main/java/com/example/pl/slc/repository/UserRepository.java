package com.example.pl.slc.repository;

import com.example.pl.slc.model.User;
import com.example.pl.slc.repository.custom.UserRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by slc on 13.07.16.
 */

@Repository
public interface UserRepository extends JpaRepository<User,Long>,UserRepositoryCustom {
    User findByUserDetailsUsername(String username);

    @Query("SELECT CASE WHEN COUNT(u) > 0 THEN 'true' ELSE 'false' END FROM User u WHERE u.userDetails.username = ?1")
    boolean existsByUsername(String username);

    User getByUserDetailsID(Long ID);
}
