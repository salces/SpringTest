package com.example.pl.slc.model;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Base64;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ImageFile {

    @Id
    @GeneratedValue
    private Long ID;

    @Lob
    @Column(length=100000)
    private byte[] data;

    private String name;

    private String extension;


    public String getHtmlImage(){
        StringBuilder htmlImage = new StringBuilder("data:image/jpeg;base64, ");
        htmlImage.append(Base64.getEncoder().encodeToString(data));
        return htmlImage.toString();
    }
}
