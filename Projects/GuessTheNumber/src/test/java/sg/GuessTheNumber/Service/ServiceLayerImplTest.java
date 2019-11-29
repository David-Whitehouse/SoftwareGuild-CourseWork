/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.GuessTheNumber.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sg.GuessTheNumber.Dao.GuessTheNumberDao;
import sg.GuessTheNumber.Dao.InMemoDao;
import sg.GuessTheNumber.Models.Game;
import sg.GuessTheNumber.Models.Guess;

/**
 *
 * @author ddubs
 */
public class ServiceLayerImplTest {
    
    public ServiceLayerImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getAllGames method, of class ServiceLayerImpl.
     */
    @Test
    public void testGetAllGamesGoldenPath()throws Exception {
        ServiceLayer toTest = new ServiceLayerImpl(new InMemoDao());
        
        List<Game> allGames = toTest.getAllGames();
        
        assertEquals(3, allGames.size());
        assertEquals(1234, allGames.get(0).getTargetNum());
        assertEquals(0, allGames.get(1).getTargetNum());
        assertEquals(0, allGames.get(2).getTargetNum());
        
    }

//        @Test
//    public void testGetAllGamesHideTargetWhenGameNotComplete()throws Exception {
//        ServiceLayer toTest = new ServiceLayerImpl(new InMemoDao());
//        
//        List<Game> allGames = toTest.getAllGames();
//        
//        assertEquals(3, allGames.size());
//        assertEquals(1234, allGames.get(0).getTargetNum());
//        assertEquals(0, allGames.get(1).getTargetNum());
//        assertEquals(0, allGames.get(2).getTargetNum());
//        
//    }
    
    
    /**
     * Test of getGameById method, of class ServiceLayerImpl.
     */
    @Test
    public void testGetGameByIdGoldenPath() throws Exception {
        ServiceLayer toTest = new ServiceLayerImpl(new InMemoDao());
        
        Game toCheck = toTest.getGameById(1);
        
        assertEquals(1, toCheck.getGameId());
        assertEquals(1234 , toCheck.getTargetNum());
        assertEquals(true , toCheck.getIsGameOver());
        
    }

        @Test
    public void testGetGameByInvalidId(){
        ServiceLayer toTest = new ServiceLayerImpl(new InMemoDao());
        
        Game toCheck;
        try {
            toCheck = toTest.getGameById(4);
            fail();
        } catch (GameDoesNotExistException ex) {
            
        }
        
    }
    
    /**
     * Test of startNewGame method, of class ServiceLayerImpl.
     */
    @Test
    public void testStartNewGameGoldenPath() {
        ServiceLayer toTest = new ServiceLayerImpl(new InMemoDao());
        
        int newGameId = toTest.startNewGame();
        
        assertEquals(4, toTest.getAllGames().size());
        assertEquals(4, newGameId);
        
    }

    /**
     * Test of checkGuess method, of class ServiceLayerImpl.
     */
    @Test
    public void testCheckGuessGoldenPath() throws Exception {
        ServiceLayer toTest = new ServiceLayerImpl(new InMemoDao());
        
        Guess guessToCheck = new Guess();
        guessToCheck.setGameId(3);
        guessToCheck.setGuessNum(7980);
        
        
        toTest.checkGuess(guessToCheck);    

    }

    @Test
    public void testCheckGuessDuplicateNumbers(){
        try {
            ServiceLayer toTest = new ServiceLayerImpl(new InMemoDao());
            
            Guess guessToCheck = new Guess();
            guessToCheck.setGameId(3);
            guessToCheck.setGuessNum(7990);
            
            
            toTest.checkGuess(guessToCheck);
            fail();
        } catch (GameDoesNotExistException | GameIsOverException ex) {
          fail(); 
        } catch (InvalidGuessException ex) {

        }

    }
    
        @Test
    public void testCheckGuessInvalidGameId(){
        try {
            ServiceLayer toTest = new ServiceLayerImpl(new InMemoDao());
            
            Guess guessToCheck = new Guess();
            guessToCheck.setGameId(1000);
            guessToCheck.setGuessNum(7980);
            
            
            
            toTest.checkGuess(guessToCheck);
            fail();
        } catch (GameDoesNotExistException ex) {
            
        } catch (InvalidGuessException | GameIsOverException ex) {
            fail();
        }

    }
    
        @Test
    public void testCheckGuessGameCompleted(){
        try {
            ServiceLayer toTest = new ServiceLayerImpl(new InMemoDao());
            
            Guess guessToCheck = new Guess();
            guessToCheck.setGameId(1);
            guessToCheck.setGuessNum(7980);
           
            
            
            toTest.checkGuess(guessToCheck);
            fail();
        } catch (GameDoesNotExistException | InvalidGuessException ex) {
          fail();
        } catch (GameIsOverException ex) {

        }

    }
    
        @Test
    public void testCheckGuessNumberTooLarge(){
        try {
            ServiceLayer toTest = new ServiceLayerImpl(new InMemoDao());
            
            Guess guessToCheck = new Guess();
            guessToCheck.setGameId(3);
            guessToCheck.setGuessNum(79805);
         
            
            
            toTest.checkGuess(guessToCheck);
            fail();
        } catch (GameDoesNotExistException | GameIsOverException ex) {
            fail();
        } catch (InvalidGuessException ex) {
        }

    }
    
            @Test
    public void testCheckGuessNumberTooSmall(){
        try {
            ServiceLayer toTest = new ServiceLayerImpl(new InMemoDao());
            
            Guess guessToCheck = new Guess();
            guessToCheck.setGameId(3);
            guessToCheck.setGuessNum(120);
         
            
            
            toTest.checkGuess(guessToCheck);
            fail();
        } catch (GameDoesNotExistException | GameIsOverException ex) {
            fail();
        } catch (InvalidGuessException ex) {
        }
    }
    
    /**
     * Test of getGuessesForId method, of class ServiceLayerImpl.
     */
    @Test
    public void testGetGuessesForIdGoldenPath() throws GameDoesNotExistException {
        ServiceLayer toTest = new ServiceLayerImpl(new InMemoDao());
 
        List<Guess> guessesToAssert = toTest.getGuessesForId(1);
        assertEquals(2, guessesToAssert.size());
    }
    
    @Test
    public void testGetGuessesForInvalidId(){
        try {
            ServiceLayer toTest = new ServiceLayerImpl(new InMemoDao());
            
            List<Guess> guessesToAssert = toTest.getGuessesForId(1000);
            fail();
            
            
        } catch (GameDoesNotExistException ex) {
            
        }
    }
}
