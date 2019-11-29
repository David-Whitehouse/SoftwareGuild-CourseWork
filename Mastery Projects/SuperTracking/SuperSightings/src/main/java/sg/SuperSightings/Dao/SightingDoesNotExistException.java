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
public class SightingDoesNotExistException extends Exception {
            public SightingDoesNotExistException(String message) {
        super(message);
    }

    public SightingDoesNotExistException(String message, Throwable cause) {
        super(message, cause);
    }
}
