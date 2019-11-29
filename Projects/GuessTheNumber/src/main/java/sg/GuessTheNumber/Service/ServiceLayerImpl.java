/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.GuessTheNumber.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sg.GuessTheNumber.Dao.DaoException;
import sg.GuessTheNumber.Dao.GameDoesNotExistDaoException;
import sg.GuessTheNumber.Dao.GuessTheNumberDao;
import sg.GuessTheNumber.Dao.InvalidGuessDaoException;
import sg.GuessTheNumber.Models.Game;
import sg.GuessTheNumber.Models.Guess;

/**
 *
 * @author ddubs
 */
@Component
public class ServiceLayerImpl implements ServiceLayer {

//    @Autowired
    private GuessTheNumberDao dao;

    public ServiceLayerImpl(GuessTheNumberDao Dao) {
        this.dao = Dao;
    }

    @Override
    public List<Game> getAllGames() {
        List<Game> allGames = dao.getAllGames();

        for (Game toCheck : allGames) {
            if (toCheck.getIsGameOver() == false) {
                toCheck.setTargetNum(0000);
            }
        }
        return allGames;
    }

    @Override
    public Game getGameById(int gameId) throws GameDoesNotExistException {

        try {
            Game gameToReturn = dao.gameById(gameId);
            if (gameToReturn == null) {
                throw new GameDoesNotExistException("Game does not exist for id:" + gameId);
            }

            if (!gameToReturn.getIsGameOver()) {
                gameToReturn.setTargetNum(0000);

            }

            return gameToReturn;
        } catch (GameDoesNotExistDaoException ex) {
            throw new GameDoesNotExistException("Game does not exist for id:" + gameId);
        }
    }

    @Override
    public int startNewGame() {
        Game newGame = new Game();

        newGame.setTargetNum(GenerateTargetNumber());
        newGame.setIsGameOver(false);

        int newGameId = dao.createNewGame(newGame);

        return newGameId;
    }

    private int GenerateTargetNumber() {
        Random rng = new Random();
        List<Integer> avail = new ArrayList(
                Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        int toReturn = 0;
        for (int i = 0; i < 4; i++) {
            toReturn *= 10;
            int digitIndex = rng.nextInt(avail.size());
            int digit = avail.get(digitIndex);
            toReturn += digit;
            avail.remove(digitIndex);
        }
        return toReturn;
    }

    @Override
    public Guess checkGuess(Guess newGuess) throws GameDoesNotExistException, InvalidGuessException, GameIsOverException {

        try {
            Game gameToCheck = dao.gameById(newGuess.getGameId());
            if (gameToCheck == null) {
                throw new GameDoesNotExistException("This game does not exist.");
            }

            if (gameToCheck.getIsGameOver() == true) {
                throw new GameIsOverException("cannot add guess to game that is complete.");
            }

            if (hasDuplicates(newGuess.getGuessNum())) {
                throw new InvalidGuessException("Guess cannot have duplicate digits.");
            }

            if (newGuess.getGuessNum() < 123 || newGuess.getGuessNum() > 9876) {
                throw new InvalidGuessException("Guess cannot be less than 123 or more than 9876");
            }

            int targetNum = gameToCheck.getTargetNum();

            int guessNum = newGuess.getGuessNum();
            newGuess.setPartialMatchCount(computePartialMatches(targetNum, guessNum));
            newGuess.setExactMatchCount(computeExactMatches(targetNum, guessNum));

            if (newGuess.getExactMatchCount() == 4) {
                gameToCheck.setIsGameOver(true);
                dao.gameComplete(gameToCheck.getGameId());
            }

            newGuess.setTimeStamp(LocalDateTime.now());

            dao.addNewGuess(newGuess);
            return newGuess;
        } catch (GameDoesNotExistDaoException ex) {
            throw new GameDoesNotExistException("Game does not exist.");
        } catch (InvalidGuessDaoException ex) {
            throw new InvalidGuessException("TimeStamp cannot be null.");
        }
    }

    private int computePartialMatches(int target, int guess) {
        int toReturn = 0;
        String t = target + " ";
        String g = guess + " ";
        if (target < 1000) {
            t = "0" + t;
        }
        if (guess < 1000) {
            g = "0" + g;
        }
        for (int i = 0; i < 4; i++) {
            char gc = g.charAt(i);
            for (int j = 0; j < 4; j++) {
                if (i != j) {
                    char tc = t.charAt(j);
                    if (gc == tc) {
                        toReturn++;
                    }
                }
            }
        }
        return toReturn;
    }

    private int computeExactMatches(int targetNum, Integer guess) {
        int toReturn = 0;

        for (int i = 0; i < 4; i++) {
            int targetOnes = targetNum % 10;
            int guessOnes = guess % 10;

            if (targetOnes == guessOnes) {
                toReturn++;
            }
            targetNum /= 10;
            guess /= 10;
        }
        return toReturn;
    }

    private boolean hasDuplicates(Integer guess) {
        boolean toReturn = false;
        boolean hasDuplicates = false;
        boolean[] seenDigit = new boolean[]{false, false, false, false, false, false, false, false, false, false};

        for (int i = 0; i < 4; i++) {
            int onesPlace = guess % 10;
            guess /= 10;

            boolean seenBefore = seenDigit[onesPlace];

            if (seenBefore) {
                toReturn = true;
                break;
            } else {
                seenDigit[onesPlace] = true;
            }

        }
        return toReturn;
    }

    @Override
    public List<Guess> getGuessesForId(Integer gameId) throws GameDoesNotExistException {
        List<Guess> allGuesses = null;

        if(getGameById(gameId) == null){
           throw new GameDoesNotExistException("Cannot place guess for game. ID: " + gameId + " is not a valid game." );
        }
        
        try {
            allGuesses = dao.getAllGuessesForId(gameId);
        } catch (GameDoesNotExistDaoException ex) {
            throw new GameDoesNotExistException("Cannot place guess for game that doesn't exist.");
        }

        return allGuesses;
    }
}
