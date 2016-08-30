package com.example.pl.slc.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by slc on 11.07.16.
 */

@Entity
@Table(name = "user_details")
public class SimpleUserDetails implements UserDetails {

    @Id
    @GeneratedValue
    private Long ID;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(name = "acc_non_expired")
    private boolean accountNonExpired = true;
    @Column(name = "acc_non_locked")
    private boolean accountNonLocked = true;
    @Column(name = "credentials_non_expired")
    private boolean credentialsNonExpired = true;
    private boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Authority> authorities = new ArrayList<>();

    public SimpleUserDetails() {
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public Long getID() {
        return ID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addAuthority(Authority authority){
        authorities.add(authority);
    }
}
