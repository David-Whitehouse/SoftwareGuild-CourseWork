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
public class GameDoesNotExistException extends Exception {
        public GameDoesNotExistException(String message) {
        super(message);
    }

    public GameDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
