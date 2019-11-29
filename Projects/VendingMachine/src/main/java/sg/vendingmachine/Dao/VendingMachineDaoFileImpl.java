/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.vendingmachine.Dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.vendingmachine.Service.InvalidIdException;
import sg.vendingmachine.dtos.Item;

/**
 *
 * @author ddubs
 */
public class VendingMachineDaoFileImpl implements VendingMachineDao {
        
    String path;

    public VendingMachineDaoFileImpl(String path) {

        this.path = path;
    }

    @Override
    public List<Item> getAllItems() throws VendingMachineDaoException{
        
        
            List<Item> goods = new ArrayList<>();
            
            FileReader reader = null;
        try{ 
            reader = new FileReader(path);
            Scanner scn = new Scanner(reader);
            
            while(scn.hasNextLine()){
                String line = scn.nextLine();
                if (line.length() > 0) {
                    String[] cells = line.split(",");
                    Item inventoryItem = new Item();
                    inventoryItem.setId(Integer.parseInt(cells[0]));
                    inventoryItem.setName(cells[1]);
                    inventoryItem.setPrice(new BigDecimal(cells[2]));
                    inventoryItem.setQty(Integer.parseInt(cells[3]));
                    
                    goods.add(inventoryItem);
                }
            }
                return goods;
            }catch (FileNotFoundException ex) {
            }finally{
            try{
                if (reader != null) {
                    reader.close();
                } 
            }catch (IOException ex) {
                throw new VendingMachineDaoException("Could not close reader for " + path, ex);
                }
        }
            return goods;
        
    }
                    
    @Override
    public Item getItemById(int id) throws VendingMachineDaoException, InvalidIdException {
                Item toReturn = null;

        List<Item> allItems = getAllItems();

        for (Item toCheck : allItems) {
            if (toCheck.getId() == id) {
                toReturn = toCheck;
                break;
            }
        }

        if (toReturn == null) {
            throw new InvalidIdException("Invalid ID selected.");
        }
        return toReturn;
    }

    @Override
    public void reduceQtyOfItem(int itemId) throws VendingMachineDaoException {
        List<Item> allItems = getAllItems();

        int matchIndex = -1;

        for (int i = 0; i < allItems.size(); i++) {
            Item toReduce = allItems.get(i);

            if (toReduce.getId() == itemId) {
                matchIndex = i;
                toReduce.setQty(toReduce.getQty() - 1);
                break;
            }
            

            
        }
            
            
            writeFile(allItems);
    }

    @Override
    public void writeFile(List<Item> allItems) throws VendingMachineDaoException {
                FileWriter writer = null;

        try {
            writer = new FileWriter(path);
            PrintWriter pw = new PrintWriter(writer);

            for (Item toWrite : allItems) {
                String line = convertToLine(toWrite);
                pw.println(line);
            }

        } catch (IOException ex) {
            throw new  VendingMachineDaoException("Error: could not write to " + path, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                throw new VendingMachineDaoException("Error: could not close writer for " + path, ex);
            }
        }

        
    }
    
        private String convertToLine(Item toWrite) {
        String line
                = toWrite.getId() + ","
                + toWrite.getName()+ ","
                + toWrite.getPrice() + ","
                + toWrite.getQty();
        return line;
    }

    }
    
    
