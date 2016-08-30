package com.example.pl.slc.security;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by slc on 13.07.16.
 */
@Entity
public class Authority implements GrantedAuthority{

    @Id
    @GeneratedValue
    private Long ID;
    private String type;

    @Override
    public String getAuthority() {
        return type;
    }
}
