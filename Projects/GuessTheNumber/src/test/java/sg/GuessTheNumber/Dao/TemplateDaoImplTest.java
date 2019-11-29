/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.GuessTheNumber.Dao;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import sg.GuessTheNumber.Models.Game;
import sg.GuessTheNumber.Models.Guess;
import sg.GuessTheNumber.TestApplicationConfiguration;

/**
 *
 * @author ddubs
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestApplicationConfiguration.class)
@Profile("daoTest")
public class TemplateDaoImplTest {
        @Autowired
    private JdbcTemplate template;
    
        @Autowired
        GuessTheNumberDao toTest;
    public TemplateDaoImplTest() {
        
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        
        String removeGuesses = "delete from guesses";
        template.update(removeGuesses);
        
        String RemoveGames = "delete from games";
        template.update(RemoveGames);      
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllGames method, of class TemplateDaoImpl.
     */
    @Test
    public void testGetAllGamesGoldenPath() {
        Game newGame = new Game();
        newGame.setTargetNum(1234);
        newGame.setIsGameOver(false);
        toTest.createNewGame(newGame);
        
        Game newGame2 = new Game();
        newGame2.setTargetNum(2345);
        newGame2.setIsGameOver(false);
        toTest.createNewGame(newGame2);
        
        List<Game> games = toTest.getAllGames();
        
        assertEquals(2, games.size());
    }

    /**
     * Test of GameById method, of class TemplateDaoImpl.
     */
    @Test
    public void testGameByIdGoldenPath() throws GameDoesNotExistDaoException{
        Game newGame = new Game();
        newGame.setTargetNum(1234);
        newGame.setIsGameOver(false);
        int gameId = toTest.createNewGame(newGame);
        
        Game gameToAssert = toTest.gameById(gameId);
        
        assertEquals(gameId, gameToAssert.getGameId());
        assertEquals(1234, gameToAssert.getTargetNum());
        assertEquals(false, gameToAssert.getIsGameOver());    
    }

        @Test
    public void testGameByInvalidId() {
            try {
                Game newGame = new Game();
                newGame.setTargetNum(1234);
                newGame.setIsGameOver(false);
                int gameId = toTest.createNewGame(newGame);
                
                Game gameToAssert = toTest.gameById(1000);
                fail();
            } catch (GameDoesNotExistDaoException ex) {
                
            }
    }
    
    
    /**
     * Test of addNewGuess method, of class TemplateDaoImpl.
     */
    @Test
    public void testAddNewGuessGoldenPath() throws GameDoesNotExistDaoException, InvalidGuessDaoException {
        Game newGame = new Game();
        newGame.setTargetNum(1234);
        newGame.setIsGameOver(false);
        int gameId = toTest.createNewGame(newGame);
        
        
        Guess newGuess = new Guess();
        newGuess.setGameId(gameId);
        newGuess.setGuessNum(1243);
        newGuess.setExactMatchCount(2);
        newGuess.setPartialMatchCount(2);
        newGuess.setTimeStamp(LocalDateTime.of(2019, 10, 7, 13, 30, 0));
        toTest.addNewGuess(newGuess);
        
        List<Guess> guessesForGame = toTest.getAllGuessesForId(newGuess.getGameId());
        assertEquals(1, guessesForGame.size());  
    }
    
        @Test
    public void testAddNewGuessInvalidTime(){
            try {
                Game newGame = new Game();
                newGame.setTargetNum(1234);
                newGame.setIsGameOver(false);
                int gameId = toTest.createNewGame(newGame);
                
                
                Guess newGuess = new Guess();
                newGuess.setGameId(gameId);
                newGuess.setGuessNum(1243);
                newGuess.setExactMatchCount(2);
                newGuess.setPartialMatchCount(2);
              
                toTest.addNewGuess(newGuess);
                fail();
                List<Guess> guessesForGame = toTest.getAllGuessesForId(newGuess.getGameId());  
                assertEquals(1, guessesForGame.size());
            } catch (GameDoesNotExistDaoException ex) {
                fail();
            } catch (InvalidGuessDaoException ex) {
            }
    }

    @Test
    public void testAddNewGuessForInvalidGame() {
            try {
                Game newGame = new Game();
                newGame.setTargetNum(1234);
                newGame.setIsGameOver(false);
                int gameId = toTest.createNewGame(newGame);
                Guess newGuess = new Guess();
                newGuess.setGameId(1000);
                newGuess.setGuessNum(1243);
                newGuess.setExactMatchCount(2);
                newGuess.setPartialMatchCount(2);
                newGuess.setTimeStamp(LocalDateTime.of(2019, 10, 7, 13, 30, 0));
                toTest.addNewGuess(newGuess);
                fail();
            } catch (GameDoesNotExistDaoException ex) {
                
            } catch (InvalidGuessDaoException ex) {
                fail();
            }
    }
    
    /**
     * Test of createNewGame method, of class TemplateDaoImpl.
     */
    @Test
    public void testCreateNewGameGoldenPath() {
        Game newGame = new Game();
        newGame.setTargetNum(1234);
        newGame.setIsGameOver(false);
        toTest.createNewGame(newGame);
        
        List<Game> games = toTest.getAllGames();
        
        assertEquals(1, games.size());
    }

    /**
     * Test of getAllGuessesForId method, of class TemplateDaoImpl.
     */
    @Test
    public void testGetAllGuessesForIdGoldenPath() throws GameDoesNotExistDaoException, InvalidGuessDaoException {
        Game newGame = new Game();
        newGame.setTargetNum(1234);
        newGame.setIsGameOver(false);
        int gameId = toTest.createNewGame(newGame);
        
        
        Guess newGuess = new Guess();
        newGuess.setGameId(gameId);
        newGuess.setGuessNum(1243);
        newGuess.setExactMatchCount(2);
        newGuess.setPartialMatchCount(2);
        newGuess.setTimeStamp(LocalDateTime.of(2019, 10, 7, 13, 30, 0));
        toTest.addNewGuess(newGuess);
        
        List<Guess> guessesForGame = toTest.getAllGuessesForId(newGuess.getGameId());
        
        
        assertEquals(1, guessesForGame.size());
        assertEquals(1243, guessesForGame.get(0).getGuessNum());
        assertEquals(2, guessesForGame.get(0).getExactMatchCount());
        assertEquals(2, guessesForGame.get(0).getPartialMatchCount());
        assertEquals(LocalDateTime.of(2019, 10, 7, 13, 30, 0), guessesForGame.get(0).getTimeStamp());
    }

        @Test
    public void testGetAllGuessesForInvalidId(){
            try {
                Game newGame = new Game();
                newGame.setTargetNum(1234);
                newGame.setIsGameOver(false);
                int gameId = toTest.createNewGame(newGame);
                Guess newGuess = new Guess();
                newGuess.setGameId(gameId);
                newGuess.setGuessNum(1243);
                newGuess.setExactMatchCount(2);
                newGuess.setPartialMatchCount(2);
                newGuess.setTimeStamp(LocalDateTime.of(2019, 10, 7, 13, 30, 0));
                toTest.addNewGuess(newGuess);
                List<Guess> guessesForGame = toTest.getAllGuessesForId(1000);
                fail();
            } catch (GameDoesNotExistDaoException ex) {
            } catch (InvalidGuessDaoException ex) {
                fail();
            }
    }
    
    /**
     * Test of gameComplete method, of class TemplateDaoImpl.
     */
    @Test
    public void testGameCompleteGoldenPath() throws GameDoesNotExistDaoException, InvalidGuessDaoException{
        Game newGame = new Game();
        newGame.setTargetNum(1234);
        newGame.setIsGameOver(false);
        int gameId = toTest.createNewGame(newGame);
        
        Guess newGuess = new Guess();
        newGuess.setGameId(gameId);
        newGuess.setGuessNum(1234);
        newGuess.setExactMatchCount(4);
        newGuess.setPartialMatchCount(0);
        newGuess.setTimeStamp(LocalDateTime.of(2019, 10, 7, 13, 30, 0));
        toTest.addNewGuess(newGuess);
        
        toTest.gameComplete(newGuess.getGameId());
        
        assertEquals(true, toTest.gameById(gameId).getIsGameOver());
    }
    
}
