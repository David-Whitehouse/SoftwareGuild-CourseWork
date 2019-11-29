/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.GuessTheNumber.Dao;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import sg.GuessTheNumber.Models.Game;
import sg.GuessTheNumber.Models.Guess;

/**
 *
 * @author ddubs
 */
public class InMemoDao implements GuessTheNumberDao {
    
    private List<Game> games = new ArrayList();
    private List<Guess> guesses = new ArrayList();
    
    public InMemoDao (){
        Game game1 = new Game();
        game1.setGameId(1);
        game1.setTargetNum(1234);
        game1.setIsGameOver(true);
        
        Game game2 = new Game();
        game2.setGameId(2);
        game2.setTargetNum(4567);
        game2.setIsGameOver(false);
        
        Game game3 = new Game();
        game3.setGameId(3);
        game3.setTargetNum(7890);
        game3.setIsGameOver(false);
        
        games.add(game1);
        games.add(game2);
        games.add(game3);
        
        Guess guess1 = new Guess();
        guess1.setGuessId(1);
        guess1.setGameId(1);
        guess1.setGuessNum(1240);
        guess1.setExactMatchCount(2);
        guess1.setPartialMatchCount(1);
        guess1.setTimeStamp(LocalDateTime.of(2019, 10, 4, 13, 30, 0));
        
        Guess guess2 = new Guess();
        guess2.setGuessId(2);
        guess2.setGameId(1);
        guess2.setGuessNum(1234);
        guess2.setExactMatchCount(4);
        guess2.setPartialMatchCount(0);
        guess2.setTimeStamp(LocalDateTime.of(2019, 10, 4, 13, 31, 0));
        
        Guess guess3 = new Guess();
        guess3.setGuessId(3);
        guess3.setGameId(2);
        guess3.setGuessNum(1234);
        guess3.setExactMatchCount(0);
        guess3.setPartialMatchCount(1);
        guess3.setTimeStamp(LocalDateTime.of(2019, 10, 4, 13, 32, 0));
        
        Guess guess4 = new Guess();
        guess4.setGuessId(4);
        guess4.setGameId(2);
        guess4.setGuessNum(2345);
        guess4.setExactMatchCount(0);
        guess4.setPartialMatchCount(2);
        guess4.setTimeStamp(LocalDateTime.of(2019, 10, 4, 13, 33, 0));
        
        Guess guess5 = new Guess();
        guess5.setGuessId(5);
        guess5.setGameId(3);
        guess5.setGuessNum(1234);
        guess5.setExactMatchCount(0);
        guess5.setPartialMatchCount(0);
        guess5.setTimeStamp(LocalDateTime.of(2019, 10, 4, 13, 34, 0));
        
        guesses.add(guess1);
        guesses.add(guess2);
        guesses.add(guess3);
        guesses.add(guess4);
        guesses.add(guess5);
        
    }

    @Override
    public List<Game> getAllGames() {
        return games;
    }

    @Override
    public Game gameById(int gameId) {
        Game toReturn = null;
        
        for(Game toCheck: games){
            if(toCheck.getGameId() == gameId){
               toReturn = toCheck;
            }
        }
        
        return toReturn;
    }

    @Override
    public Guess addNewGuess(Guess newGuess) {
        guesses.add(newGuess);
        
        return newGuess;
    }

    @Override
    public int createNewGame(Game newGame) {
        int maxId = 0;
        for(Game toCheck : games){
            
            if(toCheck.getGameId() > maxId){
                maxId = toCheck.getGameId();
        }
        }
        int newGameId = maxId + 1;
        
        newGame.setGameId(newGameId);
        games.add(newGame);
      
        return newGameId;
    }

    @Override
    public List<Guess> getAllGuessesForId(int GameId) {
        List<Guess> guessesById = new ArrayList();
        for(Guess toCheck : guesses){
            if(toCheck.getGameId() == GameId){
                guessesById.add(toCheck);
            }
        }
        return guessesById;
    }

    @Override
    public void gameComplete(int gameId) {
        Game gameToRemove = gameById(gameId);
        
        Game completedGame = gameById(gameId);
        completedGame.setIsGameOver(true);
        
        games.remove(gameToRemove);
        games.add(completedGame);
    }
}
