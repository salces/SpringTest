package com.example.pl.slc.model;

import com.example.pl.slc.validator.CreationYear;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Entity
public class Club {

    @Id
    @GeneratedValue
    private Long ID;
    @NotBlank(message = "Required field")
    private String prefix;
    @NotBlank(message = "Required field")
    private String location;
    @CreationYear
    private int year;

    @NotBlank
    @Email
    private String email;

    @NotBlank(message = "Required field")
    @URL
    private String homePage;


    @ManyToOne
    @JsonBackReference
    private User createdBy;

    @OneToMany(mappedBy = "currentClub")
    @JsonManagedReference
    Set<Player> currentPlayers;

    @Override
    public String toString() {
        return  ID.toString();
    }

    public void addPlayers(Set<Player> players){
        currentPlayers.addAll(players);
    }

    public void addPlayer(Player player){
        currentPlayers.add(player);
    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHomePage() {
        return homePage;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public Set<Player> getCurrentPlayers() {
        return currentPlayers;
    }

    public void setCurrentPlayers(Set<Player> currentPlayers) {
        this.currentPlayers = currentPlayers;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getFullName(){
        return prefix + " " + location + " " + year;
    }
}
