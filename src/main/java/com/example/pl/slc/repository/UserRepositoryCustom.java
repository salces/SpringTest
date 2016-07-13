package com.example.pl.slc.repository;

import com.example.pl.slc.model.User;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by slc on 13.07.16.
 */
public interface UserRepositoryCustom {

    @Transactional
    User register(User user);
}
