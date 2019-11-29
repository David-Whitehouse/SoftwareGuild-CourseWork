/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.flooringmastery.daos;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import static java.time.format.DateTimeFormatter.ofPattern;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sg.flooringmastery.dtos.Order;

/**
 *
 * @author ddubs
 */
public class OrdersDaoFileImplTest {

    public OrdersDaoFileImplTest() {
    }

    @BeforeAll
    public static void setUpClass() {
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() throws IOException {
        //1. delete any files that exist in the test directory
        //2. copy all files from the seed folder to the test folder

        Path testPath = Paths.get("TestOrders");
        Path seedPath = Paths.get("SeedOrders");

        File testFolder = testPath.toFile();
        File seedFolder = seedPath.toFile();

        if (!testFolder.exists()) {
            testFolder.mkdir();
        }

        File[] testFiles = testFolder.listFiles();
        for (File testFile : testFiles) {
            testFile.delete();
        }

        File[] seedFiles = seedFolder.listFiles();
        for (File seedFile : seedFiles) {
            Files.copy(seedFile.toPath(), Paths.get(testPath.toString(), seedFile.getName()), StandardCopyOption.REPLACE_EXISTING);
        }

    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of listOrdersByDate method, of class OrdersDaoFileImpl.
     */
    @Test
    public void testListOrdersByDateGoldenPath() throws OrdersDaoException {
        OrdersDao toTest = new OrdersDaoFileImpl("TestOrders", "Orders");

        String date = "01/28/2019";
        LocalDate orderDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        try {
            List<Order> allOrdersByDate = toTest.listOrdersByDate(orderDate);

            assertEquals(2, allOrdersByDate.size());

            Order orderByDate1 = allOrdersByDate.get(0);
            assertEquals(1, orderByDate1.getOrderNumber());
            assertEquals("Whitehouse", orderByDate1.getCustomerName());
            assertEquals("OH", orderByDate1.getState());
            assertEquals(new BigDecimal("6.25"), orderByDate1.getTaxRate());
            assertEquals("Wood", orderByDate1.getProductType());
            assertEquals(new BigDecimal("100.00"), orderByDate1.getArea());
            assertEquals(new BigDecimal("5.15"), orderByDate1.getCostPerSquareFoot());
            assertEquals(new BigDecimal("4.75"), orderByDate1.getLaborCostPerSquareFoot());
            assertEquals(new BigDecimal("515.00"), orderByDate1.getMaterialCost());
            assertEquals(new BigDecimal("475.00"), orderByDate1.getLaborCost());
            assertEquals(new BigDecimal("61.88"), orderByDate1.getTax());
            assertEquals(new BigDecimal("1051.88"), orderByDate1.getTotal());

            Order orderByDate2 = allOrdersByDate.get(1);
            assertEquals(2, orderByDate2.getOrderNumber());
            assertEquals("Doe", orderByDate2.getCustomerName());
            assertEquals("MN", orderByDate2.getState());
            assertEquals(new BigDecimal("4.75"), orderByDate2.getTaxRate());
            assertEquals("Vinyl", orderByDate2.getProductType());
            assertEquals(new BigDecimal("200.00"), orderByDate2.getArea());
            assertEquals(new BigDecimal("3.25"), orderByDate2.getCostPerSquareFoot());
            assertEquals(new BigDecimal("4.00"), orderByDate2.getLaborCostPerSquareFoot());
            assertEquals(new BigDecimal("650.00"), orderByDate2.getMaterialCost());
            assertEquals(new BigDecimal("800.00"), orderByDate2.getLaborCost());
            assertEquals(new BigDecimal("68.88"), orderByDate2.getTax());
            assertEquals(new BigDecimal("1518.88"), orderByDate2.getTotal());

        } catch (OrdersDaoException ex) {
            fail();
        }
    }

    @Test
    public void testListOrdersByDateWithNoOrders() throws OrdersDaoException {
        OrdersDao toTest = new OrdersDaoFileImpl("TestOrders", "Orders");

        String date = "01/28/2020";
        LocalDate orderDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        try {
            List<Order> allOrdersByDate = toTest.listOrdersByDate(orderDate);

            assertEquals(0, allOrdersByDate.size());

        } catch (OrdersDaoException ex) {
            fail();
        }
    }

    /**
     * Test of addOrder method, of class OrdersDaoFileImpl.
     */
    @Test
    public void testAddOrderGoldenPath() throws Exception {
        OrdersDao toTest = new OrdersDaoFileImpl("TestOrders", "orders");

        Order orderToAdd = new Order();
        String date = "06/01/2013";
        LocalDate orderDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        orderToAdd.setOrderDate(orderDate);
        orderToAdd.setCustomerName("Whitehouse");
        orderToAdd.setState("MI");
        orderToAdd.setTaxRate(new BigDecimal("5.75"));
        orderToAdd.setProductType("Wood");
        orderToAdd.setCostPerSquareFoot(new BigDecimal("5.15"));
        orderToAdd.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        orderToAdd.setArea(new BigDecimal("100.00"));

        toTest.addOrder(orderToAdd);

        List<Order> orders = toTest.listOrdersByDate(orderDate);
        Order newOrder = orders.get(1);

        assertEquals(2, orders.size());
        assertEquals("06/01/2013", newOrder.getOrderDate().format(ofPattern("MM/dd/yyyy")));
        assertEquals("Whitehouse", newOrder.getCustomerName());
        assertEquals("MI", newOrder.getState());
        assertEquals(new BigDecimal("5.75"), newOrder.getTaxRate());
        assertEquals("Wood", newOrder.getProductType());
        assertEquals(new BigDecimal("5.15"), newOrder.getCostPerSquareFoot());
        assertEquals(new BigDecimal("4.75"), newOrder.getLaborCostPerSquareFoot());
        assertEquals(new BigDecimal("100.00"), newOrder.getArea());
        assertEquals(new BigDecimal("515.00"), newOrder.getMaterialCost());
        assertEquals(new BigDecimal("475.00"), newOrder.getLaborCost());
        assertEquals(new BigDecimal("56.93"), newOrder.getTax());
        assertEquals(new BigDecimal("1046.93"), newOrder.getTotal());
    }

    /**
     * Test of removeOrder method, of class OrdersDaoFileImpl.
     */
    @Test
    public void testRemoveOrder() throws Exception {
        OrdersDao toTest = new OrdersDaoFileImpl("TestOrders", "orders");
        String date = "01/28/2019";
        LocalDate orderDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        assertEquals(2, toTest.listOrdersByDate(orderDate).size());

        toTest.removeOrder(orderDate, 2);

        assertEquals(1, toTest.listOrdersByDate(orderDate).size());
    }

    
    
    //TODO: Add unit test for trying to remove an order on wrong day or order number 
        @Test
    public void testRemoveOrderBadDate(){
        try {
            OrdersDao toTest = new OrdersDaoFileImpl("TestOrders", "orders");
            LocalDate orderDate = LocalDate.of(2050, 1, 28);
            toTest.removeOrder(orderDate, 2);
            fail();
        } catch (OrdersDaoException ex) {    
        }
    }
    
        @Test
    public void testRemoveOrderInvalidOrderNumber(){
            OrdersDao toTest = new OrdersDaoFileImpl("TestOrders", "orders");
            LocalDate orderDate = LocalDate.of(2019, 1, 28);
        try {

            
            assertEquals(2, toTest.listOrdersByDate(orderDate).size());
            
            toTest.removeOrder(orderDate, 3);
            fail();
            
        } catch (OrdersDaoException ex) {
                try {
                    assertEquals(2, toTest.listOrdersByDate(orderDate).size());
                } catch (OrdersDaoException ex1) {
                    fail();
                }
        }
    }
    
    /**
     *
     * Test of editOrder method, of class OrdersDaoFileImpl.
     */
    @Test
    public void testEditOrder() throws Exception {
        OrdersDao toTest = new OrdersDaoFileImpl("TestOrders", "orders");
        String date = "01/28/2019";
        LocalDate orderDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        List<Order> orders = toTest.listOrdersByDate(orderDate);

        Order originalOrder = orders.get(1);

        Order editedOrder = new Order();
        editedOrder.setOrderNumber(2);
        editedOrder.setOrderDate(orderDate);
        editedOrder.setCustomerName("Smith");
        editedOrder.setState("OH");
        editedOrder.setProductType("Carpet");
        editedOrder.setArea(new BigDecimal("150.00"));
        editedOrder.setTaxRate(new BigDecimal("5.75"));
        editedOrder.setCostPerSquareFoot(new BigDecimal("5.15"));
        editedOrder.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
        editedOrder.setArea(new BigDecimal("100.00"));

        toTest.editOrder(originalOrder, editedOrder);

        assertEquals(2, toTest.listOrdersByDate(orderDate).size());

        Order orderToAssert = toTest.getOrderById(orderDate, 2);

        assertEquals("01/28/2019", orderToAssert.getOrderDate().format(ofPattern("MM/dd/yyyy")));
        assertEquals("Smith", orderToAssert.getCustomerName());
        assertEquals("OH", orderToAssert.getState());
        assertEquals(new BigDecimal("5.75"), orderToAssert.getTaxRate());
        assertEquals("Carpet", orderToAssert.getProductType());
        assertEquals(new BigDecimal("5.15"), orderToAssert.getCostPerSquareFoot());
        assertEquals(new BigDecimal("4.75"), orderToAssert.getLaborCostPerSquareFoot());
        assertEquals(new BigDecimal("100.00"), orderToAssert.getArea());
        assertEquals(new BigDecimal("515.00"), orderToAssert.getMaterialCost());
        assertEquals(new BigDecimal("475.00"), orderToAssert.getLaborCost());
        assertEquals(new BigDecimal("56.93"), orderToAssert.getTax());
        assertEquals(new BigDecimal("1046.93"), orderToAssert.getTotal());

    }

    //TODO: test for messing up the original order date/ordernumber so that it can't be found
        @Test
    public void testEditOrderBadDate(){
        try {
            OrdersDao toTest = new OrdersDaoFileImpl("TestOrders", "orders");
            LocalDate orderDate = LocalDate.of(2050, 1, 28);
            
            Order originalOrder = toTest.getOrderById(orderDate, 1);
            
            Order editedOrder = new Order();
            editedOrder.setOrderNumber(2);
            editedOrder.setOrderDate(orderDate);
            editedOrder.setCustomerName("Smith");
            editedOrder.setState("OH");
            editedOrder.setProductType("Carpet");
            editedOrder.setArea(new BigDecimal("150.00"));
            editedOrder.setTaxRate(new BigDecimal("5.75"));
            editedOrder.setCostPerSquareFoot(new BigDecimal("5.15"));
            editedOrder.setLaborCostPerSquareFoot(new BigDecimal("4.75"));
            editedOrder.setArea(new BigDecimal("100.00"));
            
            toTest.editOrder(originalOrder, editedOrder);
            fail();

            
        } catch (OrdersDaoException ex) {
        
        }
    }
    
    
    @Test
    public void getOrderByIdGoldenPath() throws OrdersDaoException {
        OrdersDao toTest = new OrdersDaoFileImpl("TestOrders", "orders");
        String date = "01/28/2019";
        LocalDate orderDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        int orderNumber = 1;

        Order orderToAssert = toTest.getOrderById(orderDate, orderNumber);

        assertEquals(1, orderToAssert.getOrderNumber());
        assertEquals("Whitehouse", orderToAssert.getCustomerName());
        assertEquals("OH", orderToAssert.getState());
        assertEquals(new BigDecimal("6.25"), orderToAssert.getTaxRate());
        assertEquals("Wood", orderToAssert.getProductType());
        assertEquals(new BigDecimal("100.00"), orderToAssert.getArea());
        assertEquals(new BigDecimal("5.15"), orderToAssert.getCostPerSquareFoot());
        assertEquals(new BigDecimal("4.75"), orderToAssert.getLaborCostPerSquareFoot());
        assertEquals(new BigDecimal("515.00"), orderToAssert.getMaterialCost());
        assertEquals(new BigDecimal("475.00"), orderToAssert.getLaborCost());
        assertEquals(new BigDecimal("61.88"), orderToAssert.getTax());
        assertEquals(new BigDecimal("1051.88"), orderToAssert.getTotal());

    }

    @Test
    public void getOrderByInvalidId() {
        try {
            OrdersDao toTest = new OrdersDaoFileImpl("TestOrders", "orders");
            String date = "01/28/2019";
            LocalDate orderDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            int orderNumber = 100;

            Order orderToAssert = toTest.getOrderById(orderDate, orderNumber);

            assertNull(orderToAssert);

        } catch (OrdersDaoException ex) {
            
        }

    }

}
