/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.vendingmachine.Dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import sg.vendingmachine.dtos.Item;

/**
 *
 * @author ddubs
 */
public class InMemoDao implements VendingMachineDao {
    
    private List<Item> itemList = new ArrayList<>();
    
    public InMemoDao() {
        Item inStockItem = new Item();
        inStockItem.setId(1);
        inStockItem.setName("Skittles");
        inStockItem.setPrice(new BigDecimal ("1.25"));
        inStockItem.setQty(10);
        
       
        
        Item outOfStockItem = new Item();
        outOfStockItem.setId(2);
        outOfStockItem.setName("Chips");
        outOfStockItem.setPrice(new BigDecimal ("1.50"));
        outOfStockItem.setQty(0);
        
        itemList.add(inStockItem);
        itemList.add(outOfStockItem);
    }
    
    @Override
    public List<Item> getAllItems() throws VendingMachineDaoException {
        return itemList;
    }
    
    @Override
    public Item getItemById(int id) throws VendingMachineDaoException {
        Item toReturn = null;
        for (Item toGetById : itemList){
            if (toGetById.getId() == id) {
                toReturn = toGetById;
                break;
            }
        }
          return toReturn;  
    }
    
    @Override
    public void reduceQtyOfItem(int id) throws VendingMachineDaoException {
        int matchIndex = -1;

        for (int i = 0; i < itemList.size(); i++) {
            Item toReduce = itemList.get(i);

            if (toReduce.getId() == id) {
                matchIndex = i;
                toReduce.setQty(toReduce.getQty() - 1);
                break;
            }
        }
    }
    
    @Override
    public void writeFile(List<Item> allItems) throws VendingMachineDaoException {
//       do nothing...  
    }
}
