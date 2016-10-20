package com.example.pl.slc.model;

import com.example.pl.slc.validator.CreationYear;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private User createdBy;

    @OneToMany(mappedBy = "currentClub")
    Set<Player> currentPlayers;

    public void addPlayers(Set<Player> players){
        currentPlayers.addAll(players);
    }

    public void addPlayer(Player player){
        currentPlayers.add(player);
    }

    public String getFullName(){
        return new StringBuilder(this.prefix)
                    .append(" ")
                    .append(this.location)
                    .append(" ")
                    .append(this.year)
                    .toString();
    }

    @Override
    public String toString(){
        return this.getFullName();
    }


}
