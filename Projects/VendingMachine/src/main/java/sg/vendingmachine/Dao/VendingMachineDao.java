/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.vendingmachine.Dao;

import java.util.List;
import sg.vendingmachine.Service.InvalidIdException;
import sg.vendingmachine.dtos.Item;

/**
 *
 * @author ddubs
 */
public interface VendingMachineDao {

    List <Item> getAllItems()throws VendingMachineDaoException;
    
    Item getItemById(int id)throws VendingMachineDaoException, InvalidIdException;
    
    void reduceQtyOfItem(int id) throws VendingMachineDaoException;
    
    void writeFile(List<Item> allItems) throws VendingMachineDaoException;
}
