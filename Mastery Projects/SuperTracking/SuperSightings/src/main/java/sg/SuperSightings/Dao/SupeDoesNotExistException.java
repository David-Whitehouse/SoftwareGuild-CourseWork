/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.SuperSightings.Dao;

/**
 *
 * @author ddubs
 */
public class SupeDoesNotExistException extends Exception {

    public SupeDoesNotExistException(String message) {
        super(message);
    }

    public SupeDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

}
