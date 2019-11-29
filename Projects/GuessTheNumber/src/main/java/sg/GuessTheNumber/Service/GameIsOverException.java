/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.GuessTheNumber.Service;

/**
 *
 * @author ddubs
 */
public class GameIsOverException extends Exception {
    public GameIsOverException(String message) {
        super(message);
    }

    public GameIsOverException(String message, Throwable cause) {
        super(message, cause);
    }
}
