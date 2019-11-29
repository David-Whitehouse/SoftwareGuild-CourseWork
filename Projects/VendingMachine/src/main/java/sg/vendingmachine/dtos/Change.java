/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.vendingmachine.dtos;

import java.math.BigDecimal;

/**
 *
 * @author ddubs
 */
public class Change {
    
    private int dollars;
    private int quarters;
    private int dimes;
    private int nickels;
    private int pennies;
    
    public Change(BigDecimal changeAmount){
    
    //move from dollars to pennies
    
    changeAmount = changeAmount.multiply(new BigDecimal("100"));
        
    
    int currentPennies = changeAmount.intValueExact();
    
    dollars = currentPennies / 100;
    currentPennies -= dollars * 100;
    
    quarters = currentPennies / 25;
    currentPennies -= quarters * 25;
    
    dimes = currentPennies / 10;
    currentPennies -= dimes * 10;
    
    nickels = currentPennies / 5;
    currentPennies -= nickels * 5;
    
    pennies = currentPennies;  
    }

    /**
     * @return the dollars
     */
    public int getDollars() {
        return dollars;
    }

    /**
     * @return the quarters
     */
    public int getQuarters() {
        return quarters;
    }

    /**
     * @return the dimes
     */
    public int getDimes() {
        return dimes;
    }

    /**
     * @return the nickels
     */
    public int getNickels() {
        return nickels;
    }

    /**
     * @return the pennies
     */
    public int getPennies() {
        return pennies;
    }
}