/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.GuessTheNumber.Dao;

/**
 *
 * @author ddubs
 */
public class InvalidGuessDaoException extends Exception {
        public InvalidGuessDaoException(String message) {
        super(message);
    }

    public InvalidGuessDaoException(String message, Throwable cause) {
        super(message, cause);
    }
}
