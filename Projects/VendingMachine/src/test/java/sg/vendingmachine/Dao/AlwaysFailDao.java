/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.vendingmachine.Dao;

import java.util.List;
import sg.vendingmachine.dtos.Item;

/**
 *
 * @author ddubs
 */
public class AlwaysFailDao implements VendingMachineDao {
    

    @Override
    public List<Item> getAllItems() throws VendingMachineDaoException {
        throw new VendingMachineDaoException("ALWAYS FAIL DAO."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Item getItemById(int id) throws VendingMachineDaoException {
        throw new VendingMachineDaoException("ALWAYS FAIL DAO."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reduceQtyOfItem(int id) throws VendingMachineDaoException {
        throw new VendingMachineDaoException("ALWAYS FAIL DAO."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void writeFile(List<Item> allItems) throws VendingMachineDaoException {
        throw new VendingMachineDaoException("ALWAYS FAIL DAO."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
