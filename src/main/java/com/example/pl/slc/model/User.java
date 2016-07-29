package com.example.pl.slc.model;

import com.example.pl.slc.controller.RegisterController;
import com.example.pl.slc.security.SimpleUserDetails;
import com.example.pl.slc.validator.Password;
import com.fasterxml.jackson.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Set;

/**
 * Created by slc on 13.07.16.
 */

@Entity
public class User {

    @Id
    @GeneratedValue
    private Long ID;
    private String name;
    private String surname;
    private String email;

    @OneToOne
    @JoinColumn
    @JsonIgnore
    private SimpleUserDetails userDetails;

    @OneToMany(mappedBy = "createdBy")
    @JsonManagedReference
    Set<Club> ownedClubs;

    @OneToMany(mappedBy = "createdBy")
    @JsonManagedReference
    Set<Player> ownedPlayers;


    public User() {
    }

    public User(RegisterController.RegisterForm registerForm) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        this.name = registerForm.getName();
        this.surname = registerForm.getSurname();
        this.email = registerForm.getEmail();

        SimpleUserDetails userDetails = new SimpleUserDetails();
        userDetails.setUsername(registerForm.getUsername());
        String encodedPassword = passwordEncoder.encode(registerForm.getPassword());
        userDetails.setPassword(encodedPassword);
        this.userDetails = userDetails;

    }

    @Override
    public String toString() {
        return ID.toString();
    }

    public Long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public UserDetails getUserDetails() {
        return userDetails;
    }

    public Set<Club> getOwnedClubs() {
        return ownedClubs;
    }

    public Set<Player> getOwnedPlayers() {
        return ownedPlayers;
    }
}
