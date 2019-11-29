/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.GuessTheNumber.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import sg.GuessTheNumber.Models.Game;
import sg.GuessTheNumber.Models.Guess;

/**
 *
 * @author ddubs
 */
@Component
public class TemplateDaoImpl implements GuessTheNumberDao {
    @Autowired
    private JdbcTemplate template;

    @Override
    public List<Game> getAllGames() {
        String query = "SELECT * FROM games";
        List<Game> allGames = template.query(query, new GameMapper());
        
        return allGames;  
    }
    
    @Override
    public Game gameById(int gameId) throws GameDoesNotExistDaoException{
        String query = "Select * from games where GameId = ?";
        Game toReturn = null;
        
        try{
        toReturn = template.queryForObject(query, new GameMapper(), gameId);
        }catch(EmptyResultDataAccessException ex){
            throw new GameDoesNotExistDaoException("Game does not Exist.");
        }
        return toReturn;
    }

    @Override
    @Transactional
    public Guess addNewGuess(Guess newGuess) throws GameDoesNotExistDaoException, InvalidGuessDaoException {
        if(gameById(newGuess.getGameId()) == null){
            throw new GameDoesNotExistDaoException("Cannot place guess for game that does not exist.");
        }
        
        String insertNewGuess = "insert into Guesses (GuessNum, PartialMatchCount, ExactMatchCount, GameId, timeStamp) values (?,?,?,?,?)";
         
         GeneratedKeyHolder holder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
                PreparedStatement toReturn = con.prepareStatement(insertNewGuess, Statement.RETURN_GENERATED_KEYS);  
            
                toReturn.setInt(1, newGuess.getGuessNum());
                toReturn.setInt(2, newGuess.getPartialMatchCount());
                toReturn.setInt(3, newGuess.getExactMatchCount());
                toReturn.setInt(4, newGuess.getGameId());
                toReturn.setString(5, newGuess.getTimeStamp().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                
                
                return toReturn;
            }
        };    
        
        if(newGuess.getTimeStamp() == null){
            throw new InvalidGuessDaoException("Cannot place guess with invalid timestamp.");
        }
        
        

        template.update(psc, holder);
        
        int generatedId = holder.getKey().intValue();
//        int generatedId = (Integer)(template.queryForObject("SELECT MAX(guessId) FROM Guesses", Integer.class));
        
        newGuess.setGuessId(generatedId);
         
         return newGuess;
    }

    @Override
    public int createNewGame(Game newGame) {
        String createNewGame = "insert into Games (TargetNum, isGameOver) values (?,?)";
        
        
        GeneratedKeyHolder holder = new GeneratedKeyHolder();

        PreparedStatementCreator psc = new PreparedStatementCreator(){
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException{
                PreparedStatement toReturn = con.prepareStatement(createNewGame, Statement.RETURN_GENERATED_KEYS);  
            
                toReturn.setInt(1, newGame.getTargetNum());
                toReturn.setBoolean(2, false);
                
                return toReturn;
            }
        };    
        template.update(psc, holder);
        
        int generatedId = holder.getKey().intValue();
        
        newGame.setGameId(generatedId);
        
        return newGame.getGameId();
        }

    @Override
    public List<Guess> getAllGuessesForId(int gameId) throws GameDoesNotExistDaoException {
    String query = "SELECT * FROM guesses WHERE GameId = ?";
        List<Guess> allGuesses = null;
        
        allGuesses = template.query(query, new GuessMapper(), gameId);
        
        if(gameById(gameId) == null){
            throw new GameDoesNotExistDaoException("Cannot place guess for game that doesn't exist.");
        }
        
        return allGuesses;      
    }

    @Override
    public void gameComplete(int gameId) {
        String query = "UPDATE games SET IsGameOver = '1' WHERE gameId = ?";
        
        template.update(query, gameId);
    }

    private class GuessMapper implements RowMapper<Guess> {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        @Override
        public Guess mapRow(ResultSet rs, int i) throws SQLException {
            Guess toAdd = new Guess();
            toAdd.setGuessId(rs.getInt("GuessId"));
            toAdd.setGameId(rs.getInt("GameId"));
            toAdd.setGuessNum(rs.getInt("GuessNum"));
            toAdd.setPartialMatchCount(rs.getInt("PartialMatchCount"));
            toAdd.setExactMatchCount(rs.getInt("ExactMatchCount"));
            toAdd.setTimeStamp(LocalDateTime.parse(rs.getString("TimeStamp"), formatter));
            return toAdd;
        }
    }
    
    
    private class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int i) throws SQLException {
            Game toAdd = new Game();
            toAdd.setGameId(rs.getInt("GameId"));
            toAdd.setTargetNum(rs.getInt("TargetNum"));
            toAdd.setIsGameOver(rs.getBoolean("IsGameOver"));
            return toAdd;
        }

    }
}

