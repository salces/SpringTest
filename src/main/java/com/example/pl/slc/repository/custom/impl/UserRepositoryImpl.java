package com.example.pl.slc.repository.custom.impl;

import com.example.pl.slc.model.User;
import com.example.pl.slc.repository.custom.UserRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

/**
 * Created by slc on 13.07.16.
 */
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
    @Autowired
    EntityManager entityManager;

    @Override
    @Transactional
    public User register(User user) {
        entityManager.persist(user.getUserDetails());
        entityManager.persist(user);
        return user;
    }

}
