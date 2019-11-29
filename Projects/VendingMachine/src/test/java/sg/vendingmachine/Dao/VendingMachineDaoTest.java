/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.vendingmachine.Dao;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sg.vendingmachine.Service.InvalidIdException;
import sg.vendingmachine.dtos.Item;

/**
 *
 * @author ddubs
 */
public class VendingMachineDaoTest {
    


    

    
    public VendingMachineDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws IOException {
        Path testPath = Path.of("Test.txt");
        Path seedPath = Path.of("Seed.txt");
        if(Files.exists(testPath, LinkOption.NOFOLLOW_LINKS)){
        Files.delete(Path.of("Test.txt"));
        }
        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getAllItems method, of class VendingMachineDao.
     */
    @Test
    public void testGetAllGoldenPath() throws Exception {
        VendingMachineDao toTest = new VendingMachineDaoFileImpl("Test.txt");
            try{ 
            List<Item> allItems = toTest.getAllItems();
            
            assertEquals(1, allItems.size());
            Item only = allItems.get(0);
            assertEquals(1, only.getId());
            assertEquals("Skittles", only.getName());
            assertEquals(new BigDecimal("1.25"), only.getPrice());
            assertEquals(100, only.getQty());
            
            
            }catch(VendingMachineDaoException ex){
                fail();
            }
    }
    

    /**
     * Test of getItemById method, of class VendingMachineDao.
     */
    @Test
    public void testGetItemByIdGoldenPath() throws Exception {
        VendingMachineDao toTest = new VendingMachineDaoFileImpl("Test.txt");
            try{
                Item itemById = toTest.getItemById(1);
        
                assertEquals(1, itemById.getId());
                assertEquals("Skittles", itemById.getName());
                assertEquals(new BigDecimal ("1.25"), itemById.getPrice());
                assertEquals(100, itemById.getQty());
        
            }catch(VendingMachineDaoException ex){
                fail();
            }
    }
    
        @Test
    public void testGetItemByIdWrongId(){
        try { 
            VendingMachineDao toTest = new VendingMachineDaoFileImpl("Test.txt");
           
            Item itemById = toTest.getItemById(2);
            fail();

        } catch (VendingMachineDaoException ex) {
            fail();
        } catch (InvalidIdException ex) {
    
        }
        

    }
    

    /**
     * Test of reduceQtyOfItem method, of class VendingMachineDao.
     */
    @Test
    public void testReduceQtyOfItemGoldenPath() throws Exception {
        VendingMachineDao toTest = new VendingMachineDaoFileImpl("Test.txt");
        
            try{
                Item itemToReduce = toTest.getItemById(1);
        
                toTest.reduceQtyOfItem(itemToReduce.getId());
                
                Item reducedItem = toTest.getItemById(1);
                assertEquals(99, reducedItem.getQty());
            }catch(VendingMachineDaoException ex){
                fail();
            }
    }
    
    /**
     * Test of writeFile method, of class VendingMachineDao.
     */
    @Test
    public void testWriteFile() throws Exception {
    }
    
}
