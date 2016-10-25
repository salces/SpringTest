package com.example.pl.slc.model;

import com.example.pl.slc.model.enums.DominantHand;
import com.fasterxml.jackson.annotation.*;
import com.google.gson.annotations.Expose;
import lombok.*;
import org.hibernate.annotations.ManyToAny;
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

    @ManyToOne
    private ImageFile image;

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

    public String getHtmlImage(){
        if(image != null){
            return this.image.getHtmlImage();
        } else {
            return "http://vignette4.wikia.nocookie.net/mrmen/images/5/52/Small.gif/revision/latest?cb=20100731114437";
        }
    }

}
