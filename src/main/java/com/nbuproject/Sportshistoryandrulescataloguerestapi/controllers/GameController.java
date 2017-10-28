package com.nbuproject.Sportshistoryandrulescataloguerestapi.controllers;

import com.nbuproject.Sportshistoryandrulescataloguerestapi.entities.Game;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by user on 10/28/2017.
 */
@RestController
@CrossOrigin("*")
@RequestMapping(path = "/games")
public class GameController {


    @RequestMapping(path = "/all",method = RequestMethod.GET)
    public ResponseEntity<Game> getAllGames(){
        Game game = new Game("Beztashal","CSKA LITEX",5,0);
        return ResponseEntity.ok(game);
    }
}
