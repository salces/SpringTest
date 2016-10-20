package com.example.pl.slc.model;

import com.example.pl.slc.model.enums.DominantHand;
import com.fasterxml.jackson.annotation.*;
import com.google.gson.annotations.Expose;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
//@ToString
@EqualsAndHashCode
@Entity
public class Player {

    @Id
    @GeneratedValue
    @Expose
    private Long ID;

    @NotBlank(message = "Required field")
    @Expose
    private String name;
    @NotBlank(message = "Required field")
    @Expose
    private String surname;

    @Min(value = 140, message = "Player is too short")
    @Expose
    private int height;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Expose
    private DominantHand dominantHand;

    @NotBlank(message = "Required field")
    @Expose
    private String citizienship;

    @ManyToOne
    private Club currentClub;

    @ManyToOne
    private User createdBy;



    public String getFullName(){
        StringBuilder fullName = new StringBuilder();

        fullName
                .append(this.name)
                .append(" ")
                .append(this.surname)
                .append(" (")
                .append(this.citizienship)
                .append(")");

        return fullName.toString();
    }

}
