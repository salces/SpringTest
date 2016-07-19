package com.example.pl.slc.controller.api;

import com.example.pl.slc.model.Player;
import com.example.pl.slc.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by slc on 14.07.16.
 */
@RestController
@RequestMapping("/api")
public class SimpleInfoController {

    @Autowired
    PlayerRepository playerRepository;

    @RequestMapping("/players")
    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }
}
