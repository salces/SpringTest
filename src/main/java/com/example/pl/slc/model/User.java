package com.example.pl.slc.model;

import com.example.pl.slc.controller.RegisterController;
import com.example.pl.slc.security.SimpleUserDetails;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
    private SimpleUserDetails userDetails;

    @OneToMany(mappedBy = "createdBy")
    Set<Club> ownedClubs;

    @OneToMany(mappedBy = "createdBy")
    Set<Player> ownedPlayers;

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

}
