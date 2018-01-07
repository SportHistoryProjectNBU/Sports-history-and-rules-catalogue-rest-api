package com.nbuproject.Sportshistoryandrulescataloguerestapi.controllers;

import com.nbuproject.Sportshistoryandrulescataloguerestapi.entities.Fixture;
import com.nbuproject.Sportshistoryandrulescataloguerestapi.entities.Game;
import com.nbuproject.Sportshistoryandrulescataloguerestapi.repositories.GameRepository;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by user on 10/28/2017.
 */
@RestController
@CrossOrigin("*")
@RequestMapping(path = "/games")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> insertGames(@RequestBody Fixture[] games) {
        for (Fixture game : games) {
            Game newGame = new Game();
            newGame.setDate(game.getDate());
            newGame.setAwayTeamName(game.getAwayTeamName());
            newGame.setHomeTeamName(game.getHomeTeamName());
            newGame.setMatchday(game.getMatchday());
            newGame.setStatus(game.getStatus());
            if(game.getResult() != null){
                if (game.getResult().getGoalsHomeTeam() != null) {
                    newGame.setGoalsHomeTeamFirstHalf(game.getResult().getGoalsHomeTeam());
                }
                if (game.getResult().getGoalsAwayTeam() != null) {
                    newGame.setGoalsAwayTeamFirstHalf(game.getResult().getGoalsAwayTeam());
                }
                if(game.getResult().getHalfTime() !=null) {
                    if (game.getResult().getHalfTime().getGoalsHomeTeam() != null) {
                        newGame.setGoalsHomeTeamSecondHalf(game.getResult().getHalfTime().getGoalsHomeTeam());
                    }
                    if (game.getResult().getHalfTime().getGoalsAwayTeam() != null) {
                        newGame.setGoalsAwayTeamSecondHalf(game.getResult().getHalfTime().getGoalsAwayTeam());
                    }
                }
            }
            try{

                gameRepository.save(newGame);
            }catch (DataIntegrityViolationException dive) {

            }
        }
        return ResponseEntity.ok("Created");
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<ArrayList<Game>> getAllGames(){
        ArrayList<Game> games = gameRepository.findAll();
        return ResponseEntity.ok(games);
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Game> getGameById(@PathVariable(value="id") String id ){
        Game game = gameRepository.findGameById(id);
        return  ResponseEntity.ok(game);
    }
}
