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

    //<editor-fold desc="Fields">

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

    //</editor-fold desc="Fields">

    //<editor-fold desc="Constructors/Eq/toString">

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

    //</editor-fold desc="Constructors/Eq/toString">

    //<editor-fold desc="Get/Set">

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SimpleUserDetails getUserDetails() {
        return userDetails;
    }

    public void setUserDetails(SimpleUserDetails userDetails) {
        this.userDetails = userDetails;
    }

    public Set<Club> getOwnedClubs() {
        return ownedClubs;
    }

    public void setOwnedClubs(Set<Club> ownedClubs) {
        this.ownedClubs = ownedClubs;
    }

    public Set<Player> getOwnedPlayers() {
        return ownedPlayers;
    }

    public void setOwnedPlayers(Set<Player> ownedPlayers) {
        this.ownedPlayers = ownedPlayers;
    }


    //</editor-fold desc="Get/Set">

}
