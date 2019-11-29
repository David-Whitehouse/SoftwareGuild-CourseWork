/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.flooringmastery.Service;

/**
 *
 * @author ddubs
 */
public class InvalidProductTypeException extends Exception {
        public InvalidProductTypeException(String message) {
        super(message);
    }

    public InvalidProductTypeException(String message, Throwable inner) {
        super(message, inner);
    }
}
