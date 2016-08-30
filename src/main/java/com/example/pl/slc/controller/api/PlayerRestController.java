package com.example.pl.slc.controller.api;

import com.example.pl.slc.model.Player;
import com.example.pl.slc.repository.PlayerRepository;
import com.example.pl.slc.security.LoggedUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping("/api/player")
public class PlayerRestController {

    @Autowired
    PlayerRepository playerRepository;
    @Autowired
    LoggedUser loggedUser;

    @RequestMapping(method = GET)
    @ResponseStatus(OK)
    public List<Player> getAllPlayers(){
        return playerRepository.findAll();
    }

    @RequestMapping(method = POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void savePlayer(@RequestBody Player player){
        player.setCreatedBy(loggedUser.getLoggedUser());
        playerRepository.save(player);
    }

    @RequestMapping(value = "/{ID}",method = GET)
    public Player getPlayerByID(@PathVariable Long ID){
        return playerRepository.findOne(ID);
    }

    @RequestMapping(value = "/{ID}",method = DELETE)
    @ResponseStatus(NO_CONTENT)
    public void deletePlayerByID(@PathVariable Long ID){
        playerRepository.delete(ID);
    }

    @RequestMapping(method = PUT)
    @ResponseStatus(OK)
    public void updatePlayer(@RequestBody Player player){
        playerRepository.save(player);
    }




}
