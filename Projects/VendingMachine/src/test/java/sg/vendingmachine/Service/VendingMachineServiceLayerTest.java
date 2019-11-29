/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.vendingmachine.Service;

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
import sg.vendingmachine.Dao.AlwaysFailDao;
import sg.vendingmachine.Dao.InMemoDao;
import sg.vendingmachine.Dao.VendingMachineDao;
import sg.vendingmachine.Dao.VendingMachineDaoException;
import sg.vendingmachine.Dao.VendingMachineDaoFileImpl;
import sg.vendingmachine.dtos.Change;
import sg.vendingmachine.dtos.Item;

/**
 *
 * @author ddubs
 */
public class VendingMachineServiceLayerTest {

    public VendingMachineServiceLayerTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of grabAllItems method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGrabAllItems() throws Exception {

        VendingMachineDao dao = new InMemoDao();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao);

        List itemList = service.grabAllItems();
        assertEquals(2, itemList.size());

    }

    @Test
    public void testGrabAllItemsBadDao() {

        VendingMachineDao dao = new InMemoDao();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao);

        List itemList = null;
        try {
            itemList = service.grabAllItems();
        } catch (VendingMachineServiceLayerException ex) {
            assertNull(itemList);
        }

    }

    /**
     * Test of getItemById method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testGetItemById() throws Exception {

        VendingMachineDao dao = new InMemoDao();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao);

        Item itemToCheck = service.getItemById(1);

        assertEquals(1, itemToCheck.getId());
        assertEquals("Skittles", itemToCheck.getName());
        assertEquals(new BigDecimal("1.25"), itemToCheck.getPrice());
        assertEquals(10, itemToCheck.getQty());

    }

    @Test
    public void testGetItemByIdBadDao() {
        VendingMachineDao dao = new AlwaysFailDao();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao);

        Item itemToCheck = null;

        try {
            itemToCheck = service.getItemById(1);
            fail();
        } catch (InvalidIdException ex) {
            fail();
        } catch (VendingMachineServiceLayerException ex) {
            assertNull(itemToCheck);
        }

    }

    /**
     * Test of returnChange method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testReturnChangeGoldenPath() throws Exception {

        VendingMachineDao dao = new InMemoDao();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao);

        Change changeToCheck = null;
        service.insertMoney(new BigDecimal("1.41"));
        changeToCheck = service.returnChange();

        assertEquals(1, changeToCheck.getDollars());
        assertEquals(1, changeToCheck.getQuarters());
        assertEquals(1, changeToCheck.getDimes());
        assertEquals(1, changeToCheck.getNickels());
        assertEquals(1, changeToCheck.getPennies());

    }

    /**
     * Test of insertMoney method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testInsertMoneyGoldenPath() throws Exception {

        VendingMachineDao dao = new InMemoDao();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao);

        BigDecimal userInsertedMoney = new BigDecimal("1.50");
        service.insertMoney(userInsertedMoney);
    }
    
     /**
     * Test of insertMoney method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testInsertNegativeMoney(){

        VendingMachineDao dao = new InMemoDao();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao);

        BigDecimal userInsertedMoney = new BigDecimal("-1.50");
        try {
            service.insertMoney(userInsertedMoney);
            fail();
        } catch (VendingMachineServiceLayerException ex) {
            Change changeToReturn = service.returnChange();
                assertEquals(0, changeToReturn.getDollars());
                assertEquals(0, changeToReturn.getQuarters());
                assertEquals(0, changeToReturn.getDimes());
                assertEquals(0, changeToReturn.getNickels());
                assertEquals(0, changeToReturn.getPennies());
        }
    }

    /**
     * Test of vendItem method, of class VendingMachineServiceLayer.
     */
    @Test
    public void testVendItemGoldenPath() throws Exception {
        VendingMachineDao dao = new InMemoDao();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao);

        BigDecimal userInsertedMoney = new BigDecimal("2.00");
        service.insertMoney(userInsertedMoney);
        service.vendItem(1);

        Change changeToReturn = service.returnChange();
        assertEquals(0, changeToReturn.getDollars());
        assertEquals(3, changeToReturn.getQuarters());
        assertEquals(0, changeToReturn.getDimes());
        assertEquals(0, changeToReturn.getNickels());
        assertEquals(0, changeToReturn.getPennies());

        Item toCheckQty = dao.getItemById(1);
        assertEquals(9, toCheckQty.getQty());
    }

    @Test
    public void testVendInsufficientFunds() {
        VendingMachineDao dao = new InMemoDao();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao);

        try {

            BigDecimal userInsertedMoney = new BigDecimal("1.24");
            service.insertMoney(userInsertedMoney);
            service.vendItem(1);
            fail();

        } catch (VendingMachineServiceLayerException | OutOfStockException | InvalidIdException ex) {
            fail();
        } catch (InsufficientFundsException ex) {
            try {
                Change changeToReturn = service.returnChange();
                assertEquals(1, changeToReturn.getDollars());
                assertEquals(0, changeToReturn.getQuarters());
                assertEquals(2, changeToReturn.getDimes());
                assertEquals(0, changeToReturn.getNickels());
                assertEquals(4, changeToReturn.getPennies());

                Item toCheckQty = dao.getItemById(1);
            } catch (VendingMachineDaoException | InvalidIdException ex1) {
                fail();
            }
        }
    }

    @Test
    public void testVendItemOutOfStock() {
        VendingMachineDao dao = new InMemoDao();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao);

        try {
            BigDecimal userInsertedMoney = new BigDecimal("2.00");
            service.insertMoney(userInsertedMoney);
            service.vendItem(2);
            fail();

        } catch (InsufficientFundsException | InvalidIdException | VendingMachineServiceLayerException ex) {
            fail();
        } catch (OutOfStockException ex) {
            try {
                Change changeToReturn = service.returnChange();
                assertEquals(2, changeToReturn.getDollars());
                assertEquals(0, changeToReturn.getQuarters());
                assertEquals(0, changeToReturn.getDimes());
                assertEquals(0, changeToReturn.getNickels());
                assertEquals(0, changeToReturn.getPennies());

                Item toCheckQty = dao.getItemById(2);
                assertEquals(0, toCheckQty.getQty());
            } catch (InvalidIdException | VendingMachineDaoException ex1) {
                fail();
            }
        }
    }

    @Test
    public void testVendItemInvalidId() {
        VendingMachineDao dao = new InMemoDao();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao);
        try {

            BigDecimal userInsertedMoney = new BigDecimal("2.00");
            service.insertMoney(userInsertedMoney);
            service.vendItem(25);
            fail();

        } catch (VendingMachineServiceLayerException | OutOfStockException | InsufficientFundsException ex) {
            fail();
        } catch (InvalidIdException ex) {
            Change changeToReturn = service.returnChange();
            assertEquals(2, changeToReturn.getDollars());
            assertEquals(0, changeToReturn.getQuarters());
            assertEquals(0, changeToReturn.getDimes());
            assertEquals(0, changeToReturn.getNickels());
            assertEquals(0, changeToReturn.getPennies());
        }
    }

    @Test
    public void testVendItemBadDao() {
        VendingMachineDao dao = new AlwaysFailDao();
        VendingMachineServiceLayer service = new VendingMachineServiceLayerImpl(dao);
        try {

            BigDecimal userInsertedMoney = new BigDecimal("2.00");
            service.insertMoney(userInsertedMoney);
            service.vendItem(1);
            fail();

        } catch (VendingMachineServiceLayerException ex) {

            Change changeToReturn = service.returnChange();
            assertEquals(2, changeToReturn.getDollars());
            assertEquals(0, changeToReturn.getQuarters());
            assertEquals(0, changeToReturn.getDimes());
            assertEquals(0, changeToReturn.getNickels());
            assertEquals(0, changeToReturn.getPennies());
        } catch (OutOfStockException | InsufficientFundsException | InvalidIdException ex) {
            fail();
        }

    }
}
