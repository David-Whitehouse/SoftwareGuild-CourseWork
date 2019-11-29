/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.GuessTheNumber.Models;

/**
 *
 * @author ddubs
 */
public class Game {
    private int gameId;
    private int TargetNum;
    private boolean isGameOver;

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
     * @return the TargetNum
     */
    public int getTargetNum() {
        return TargetNum;
    }

    /**
     * @param TargetNum the TargetNum to set
     */
    public void setTargetNum(int TargetNum) {
        this.TargetNum = TargetNum;
    }

    /**
     * @return the isGameOver
     */
    public boolean getIsGameOver() {
        return isGameOver;
    }

    /**
     * @param isGameOver the isGameOver to set
     */
    public void setIsGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }
           
}
