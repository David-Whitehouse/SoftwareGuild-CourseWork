/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.vendingmachine.Service;

import java.math.BigDecimal;
import java.util.List;
import sg.vendingmachine.Dao.VendingMachineDaoException;
import sg.vendingmachine.dtos.Change;
import sg.vendingmachine.dtos.Item;

/**
 *
 * @author ddubs
 */
public interface VendingMachineServiceLayer {
    
    List<Item> grabAllItems() throws VendingMachineServiceLayerException;
    
    Item getItemById(int id) throws InvalidIdException, VendingMachineServiceLayerException;
    
    Change returnChange();

    public void insertMoney(BigDecimal userMoney) throws VendingMachineServiceLayerException;
    
    public void vendItem(int id) throws VendingMachineServiceLayerException, 
            OutOfStockException, 
            InsufficientFundsException, 
            InvalidIdException;
}
