package com.example.pl.slc.model;

import com.example.pl.slc.model.enums.DominantHand;
import com.fasterxml.jackson.annotation.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;



@Entity
public class Player {

    //<editor-fold desc="Fields">


    @Id
    @GeneratedValue
    private Long ID;

    @NotBlank(message = "Required field")
    private String name;
    @NotBlank(message = "Required field")
    private String surname;

    @Min(value = 140, message = "Player is too short")
    private int height;

    @NotNull
    @Enumerated(EnumType.STRING)
    private DominantHand dominantHand;

    @NotBlank(message = "Required field")
    private String citizienship;

    @ManyToOne
//    @JsonIgnore
    @JsonBackReference
    private Club currentClub;

    @ManyToOne
//    @JsonIgnore
    @JsonBackReference
    private User createdBy;

    //</editor-fold desc="Fields">

    //<editor-fold desc="Own methods">

    public String getFullName(){
        return name + " " + surname + " (" + citizienship + ")";
    }

    //</editor-fold desc="Own methods">

    //<editor-fold desc="Constructors/Eq/toString">


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (ID != null ? ID.equals(player.ID) : false) return true;


        if (height != player.height) return false;
        if (ID != null ? !ID.equals(player.ID) : player.ID != null) return false;
        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        if (surname != null ? !surname.equals(player.surname) : player.surname != null) return false;
        if (dominantHand != player.dominantHand) return false;
        if (citizienship != null ? !citizienship.equals(player.citizienship) : player.citizienship != null)
            return false;
        if (currentClub != null ? !currentClub.equals(player.currentClub) : player.currentClub != null) return false;
        return createdBy != null ? createdBy.equals(player.createdBy) : player.createdBy == null;

    }

    @Override
    public int hashCode() {
        int result = ID != null ? ID.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        result = 31 * result + height;
        result = 31 * result + (dominantHand != null ? dominantHand.hashCode() : 0);
        result = 31 * result + (citizienship != null ? citizienship.hashCode() : 0);
        result = 31 * result + (currentClub != null ? currentClub.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        return result;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public DominantHand getDominantHand() {
        return dominantHand;
    }

    public void setDominantHand(DominantHand dominantHand) {
        this.dominantHand = dominantHand;
    }

    public String getCitizienship() {
        return citizienship;
    }

    public void setCitizienship(String citizienship) {
        this.citizienship = citizienship;
    }

    public Club getCurrentClub() {
        return currentClub;
    }

    public void setCurrentClub(Club currentClub) {
        this.currentClub = currentClub;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }


    //</editor-fold desc="Get/Set">


}
