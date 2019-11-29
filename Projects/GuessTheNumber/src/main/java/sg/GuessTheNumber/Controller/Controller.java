/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.GuessTheNumber.Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sg.GuessTheNumber.Models.Game;
import sg.GuessTheNumber.Models.Guess;
import sg.GuessTheNumber.Service.GameDoesNotExistException;
import sg.GuessTheNumber.Service.GameIsOverException;
import sg.GuessTheNumber.Service.InvalidGuessException;
import sg.GuessTheNumber.Service.ServiceLayer;

/**
 *
 * @author ddubs
 */
@RestController
@RequestMapping("/api")
public class Controller {
    
    @Autowired
    ServiceLayer service;

    @PostMapping ("/begin")
    public int begin() {
        return service.startNewGame();
        
    }

    @PostMapping ("/guess")
    public Guess guess(@RequestBody Guess guess) throws GameDoesNotExistException, InvalidGuessException, GameIsOverException {
        return service.checkGuess(guess);
    }

    @GetMapping ("/game")
    public List<Game> getAllGames() {
        List<Game> games = new ArrayList();
                games = service.getAllGames();
        
        return games; 
    }

    @GetMapping ("/game/{gameId}")
    public Game getGameById(@PathVariable Integer gameId) {
        Game g = new Game();
        try {
            g = service.getGameById(gameId);
        } catch (GameDoesNotExistException ex) {
        }
        
        return g;
    }

    @GetMapping ("/guesses/{id}")
    public List<Guess> getGuessesByGameId(@PathVariable Integer id) throws GameDoesNotExistException {
        List<Guess> guesses = new ArrayList();
        guesses = service.getGuessesForId(id);
        return guesses; 
    }

}
