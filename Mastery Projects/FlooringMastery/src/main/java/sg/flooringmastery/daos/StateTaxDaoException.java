/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.flooringmastery.daos;

/**
 *
 * @author ddubs
 */
public class StateTaxDaoException extends Exception {

    public StateTaxDaoException(String message) {
        super(message);
    }

    public StateTaxDaoException(String message, Throwable inner) {
        super(message, inner);
    }
}
