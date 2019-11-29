/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.vendingmachine.Dao;

/**
 *
 * @author ddubs
 */
public class VendingMachinePersistenceException extends Exception {
        
    public VendingMachinePersistenceException(String message) {
        super(message);
    }

    public VendingMachinePersistenceException(String message, Throwable inner) {
        super(message, inner);
    }
}
