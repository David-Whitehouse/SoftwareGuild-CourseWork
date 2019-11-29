/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.GuessTheNumber.Models;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 *
 * @author ddubs
 */
public class Guess {
    private int guessId;
    private int guessNum;
    private int partialMatchCount;
    private int exactMatchCount;
    private int gameId;
    private LocalDateTime timeStamp;

    /**
     * @return the guessId
     */
    public int getGuessId() {
        return guessId;
    }

    /**
     * @param guessId the guessId to set
     */
    public void setGuessId(int guessId) {
        this.guessId = guessId;
    }

    /**
     * @return the guessNum
     */
    public int getGuessNum() {
        return guessNum;
    }

    /**
     * @param guessNum the guessNum to set
     */
    public void setGuessNum(int guessNum) {
        this.guessNum = guessNum;
    }

    /**
     * @return the partialMatchCount
     */
    public int getPartialMatchCount() {
        return partialMatchCount;
    }

    /**
     * @param partialMatchCount the partialMatchCount to set
     */
    public void setPartialMatchCount(int partialMatchCount) {
        this.partialMatchCount = partialMatchCount;
    }

    /**
     * @return the exactMatchCount
     */
    public int getExactMatchCount() {
        return exactMatchCount;
    }

    /**
     * @param exactMatchCount the exactMatchCount to set
     */
    public void setExactMatchCount(int exactMatchCount) {
        this.exactMatchCount = exactMatchCount;
    }

    /**
     * @return the gameId
     */
    public int getGameId() {
        return gameId;
    }

    /**
     * @param gameId the gameId to set
     */
    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    /**
     * @return the timeStamp
     */
    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the timeStamp
     */

    
}
