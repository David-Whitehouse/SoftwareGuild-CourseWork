/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.vendingmachine.ui;

import java.math.BigDecimal;
import java.util.List;
import sg.vendingmachine.dtos.Change;
import sg.vendingmachine.dtos.Item;

/**
 *
 * @author ddubs
 */
public class VendingMachineView {
    
        private UserIO io;

    public VendingMachineView(UserIO io) {
        this.io = io;
    }
        
    public int printMenuAndGetSelection() {
        io.print("Vending Machine Menu");
        io.print("1. Insert Money ");
        io.print("2. Purchase Item ");
        io.print("3. Return Change ");
        io.print("4. Exit");

        return io.readInt("Please select from the " + "above choices", 1, 4);
    }
    
    public int getVendingSelection(){
        
        return   io.readInt("Please enter ID for item you would like to purchase", 1, 5);
    }
    
    public void displayUnknownCommandBanner() {
        io.print("Unknown Command!!!");
    }
    
    public void displayErrorMessage(String message){
        io.print("Error: " + message);
    }

    public void displayWelcome() {
       io.print("Welcome to the Virtual Vending Machine!");
    }
    
    public void displayItemList(List<Item> itemList) {
    io.print("______________________________________");    
        for (Item currentItem : itemList) {
            if(currentItem.getQty() <= 0){
            io.print(currentItem.getId() + ": "
                + currentItem.getName() + " "
                + "$" + currentItem.getPrice()
                + " - Item currently out of stock!");    
            }else{    
            io.print(currentItem.getId() + ": "
                + currentItem.getName() + " "
                + "$" + currentItem.getPrice());
        
            }
        }
    io.print("______________________________________");

    
    }
    
    public void goodByeMessage() {
        io.print("Thank you, good bye!");
    }

    public BigDecimal insertMoneyMessage() {
        io.print("Please insert funds to purchase an item.");
        return io.readBigDecimal("how much money would you like to insert?");
    }

    public void purchaseMessage(Item item) {
        io.print("Thank you for your Selection. enjoy your " + item.getName());
    }

    public void returnChangeMessage(Change change) {
        io.print("Please take your change!");
        io.print("Dollars: " + change.getDollars());
        io.print("Quarters: " + change.getQuarters());
        io.print("Dimes: " + change.getDimes());
        io.print("Nickels: " + change.getNickels());
        io.print("Pennies: " + change.getPennies());
    }

}
