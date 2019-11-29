/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.GuessTheNumber.Service;

import java.util.List;
import sg.GuessTheNumber.Models.Game;
import sg.GuessTheNumber.Models.Guess;

/**
 *
 * @author ddubs
 */
public interface ServiceLayer {

    public List<Game> getAllGames();

    public Game getGameById(int gameId) throws GameDoesNotExistException;

    public int startNewGame();

    public Guess checkGuess(Guess guess) throws GameDoesNotExistException, InvalidGuessException, GameIsOverException;

    public List<Guess> getGuessesForId(Integer gameId) throws GameDoesNotExistException;
    
}
