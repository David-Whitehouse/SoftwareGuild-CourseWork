/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.GuessTheNumber.Dao;

import java.util.List;
import sg.GuessTheNumber.Models.Game;
import sg.GuessTheNumber.Models.Guess;

/**
 *
 * @author ddubs
 */
public interface GuessTheNumberDao {

    public List<Game> getAllGames();

    public Game gameById(int gameId) throws GameDoesNotExistDaoException;

    public Guess addNewGuess(Guess newGuess) throws GameDoesNotExistDaoException, InvalidGuessDaoException;

    public int createNewGame(Game newGame);

    public List<Guess> getAllGuessesForId(int GameId) throws GameDoesNotExistDaoException;

    public void gameComplete(int gameId);
    
}
