/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.vendingmachine.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.vendingmachine.Dao.VendingMachineDao;
import sg.vendingmachine.Dao.VendingMachineDaoException;
import sg.vendingmachine.dtos.Change;
import sg.vendingmachine.dtos.Item;

/**
 *
 * @author ddubs
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer {

    VendingMachineDao dao;

    BigDecimal storedMoney = new BigDecimal("0");

    public VendingMachineServiceLayerImpl(VendingMachineDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Item> grabAllItems() throws VendingMachineServiceLayerException {
        try {
            return dao.getAllItems();
        } catch (VendingMachineDaoException ex) {
            throw new VendingMachineServiceLayerException("Could not locate items");
        }

    }

    @Override
    public Item getItemById(int id) throws InvalidIdException, VendingMachineServiceLayerException {

        Item itemToVend = null;
        try {
            itemToVend = dao.getItemById(id);
        } catch (VendingMachineDaoException ex) {
            throw new VendingMachineServiceLayerException("Could not locate Items.");
        }
        if (itemToVend == null) {
            throw new InvalidIdException("Selected ID: " + id + " is not a valid selection.");
        }

        return itemToVend;
    }

    @Override
    public Change returnChange() {

        Change changeToReturn = new Change(storedMoney);
        storedMoney = BigDecimal.ZERO;
        return changeToReturn;
    }

    @Override
    public void insertMoney(BigDecimal insertedFunds) throws VendingMachineServiceLayerException {
        if(insertedFunds.compareTo(new BigDecimal("0")) == -1){
            throw new VendingMachineServiceLayerException("Insert positive money only please.");
        }else{   
            storedMoney = storedMoney.add(insertedFunds);    
        }
    }
    

    private boolean checkQuantity(Item itemToVend) {
        boolean hasError = false;
        if (itemToVend.getQty() <= 0) {
            hasError = true;
        }
        return hasError;
    }

    private boolean checkSufficientFunds(Item itemToVend) {
        boolean hasError = false;
        if (itemToVend.getPrice().compareTo(storedMoney) > 0) {
            hasError = true;
        }
        return hasError;
    }

    private BigDecimal checkBalance() {
        return storedMoney;
    }

    @Override
    public void vendItem(int id) throws OutOfStockException, 
            InsufficientFundsException, InvalidIdException, VendingMachineServiceLayerException {
        try{
            boolean hasErrors = false;
           
            Item purchasedItem = dao.getItemById(id);
            if (purchasedItem == null) {
                hasErrors = true;
                throw new InvalidIdException("Selected ID: " + id + " is not a valid selection.");
            }
            
            if (checkQuantity(purchasedItem) == true) {
                hasErrors = true;
                throw new OutOfStockException("Item currently out of stock. Please make another selection.");
            }
            
            if (hasErrors == false) {
                if (checkSufficientFunds(purchasedItem) == true) {
                    hasErrors = true;
                    throw new InsufficientFundsException(
                            "Insufficient funds. Please add additional money to continue with purchase. "
                                    + "Your current balance: " + checkBalance());
                }
            }
            
            if (hasErrors == false) {
                try {
                    storedMoney = storedMoney.subtract(purchasedItem.getPrice());
                    dao.reduceQtyOfItem(purchasedItem.getId());
                } catch (VendingMachineDaoException ex) {
                    throw new VendingMachineServiceLayerException("Could not locate item.");
                }
            }
        } catch (VendingMachineDaoException ex) {
            throw new VendingMachineServiceLayerException("Could not locate item to vend ID " + id + ". " + ex.getMessage(), ex);
        }
        

    }

}
