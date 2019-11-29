/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.flooringmastery.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sg.flooringmastery.daos.OrdersAlwaysFailDao;
import sg.flooringmastery.daos.OrdersDao;
import sg.flooringmastery.daos.OrdersDaoException;
import sg.flooringmastery.daos.OrdersInMemoDao;
import sg.flooringmastery.daos.ProductsDao;
import sg.flooringmastery.daos.ProductsInMemoDao;
import sg.flooringmastery.daos.StateTaxDao;
import sg.flooringmastery.daos.StateTaxInMemoDao;
import sg.flooringmastery.dtos.Order;

/**
 *
 * @author ddubs
 */
public class FlooringServiceImplTest {

    public FlooringServiceImplTest() {
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
     * Test of getOrdersByDate method, of class FlooringServiceImpl.
     */
    @Test
    public void testGetOrdersByDate() throws Exception {
        OrdersDao ordersDao = new OrdersInMemoDao();
        StateTaxDao taxDao = new StateTaxInMemoDao();
        ProductsDao productsDao = new ProductsInMemoDao();
        FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);

        List<Order> ordersList = service.getOrdersByDate(LocalDate.of(2019, 01, 28));

        assertEquals(2, ordersList.size());

    }

    @Test
    public void testGetOrdersByDateBadDao() {

        OrdersDao failDao = new OrdersAlwaysFailDao();
        StateTaxDao taxDao = new StateTaxInMemoDao();
        ProductsDao productsDao = new ProductsInMemoDao();
        FlooringService service = new FlooringServiceImpl(failDao, productsDao, taxDao);

        List orderList = null;

        try {
            orderList = service.getOrdersByDate(LocalDate.MAX);
        } catch (ServiceLayerException ex) {
            assertNull(orderList);
        }

    }

    @Test
    public void testGetOrdersByDateWithNoOrders() {
        try {
            OrdersDao ordersDao = new OrdersInMemoDao();
            StateTaxDao taxDao = new StateTaxInMemoDao();
            ProductsDao productsDao = new ProductsInMemoDao();
            FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);

            List<Order> ordersList = service.getOrdersByDate(LocalDate.parse("01/28/2050", DateTimeFormatter.ofPattern("MM/dd/yyyy")));

            assertEquals(0, ordersList.size());

        } catch (ServiceLayerException ex) {
            fail();
        }

    }

    /**
     * Test of getOrderById method, of class FlooringServiceImpl.
     */
    @Test
    public void testGetOrderByIdGoldenPath() throws Exception {
        OrdersDao ordersDao = new OrdersInMemoDao();
        StateTaxDao taxDao = new StateTaxInMemoDao();
        ProductsDao productsDao = new ProductsInMemoDao();
        FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);

        Order orderToCheck1 = service.getOrderById(LocalDate.of(2019, 1, 28), 1);

        assertEquals(1, orderToCheck1.getOrderNumber());
        assertEquals("Smith", orderToCheck1.getCustomerName());
        assertEquals("MN", orderToCheck1.getState());
        assertEquals("Wood", orderToCheck1.getProductType());
        assertEquals(new BigDecimal("100"), orderToCheck1.getArea());
    }

    @Test
    public void testGetOrderByDateWithNoOrders() {
        try {
            OrdersDao ordersDao = new OrdersInMemoDao();
            StateTaxDao taxDao = new StateTaxInMemoDao();
            ProductsDao productsDao = new ProductsInMemoDao();
            FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);

            Order orderToCheck1 = service.getOrderById(LocalDate.of(2050, 1, 28), 1);
            fail();

        } catch (InvalidDateException ex) {

        } catch (ServiceLayerException | OrderDoesNotExistException ex) {
            fail();
        }
    }

    @Test
    public void testGetOrderByIdBadDao() {
        try {
            OrdersDao failDao = new OrdersAlwaysFailDao();
            StateTaxDao taxDao = new StateTaxInMemoDao();
            ProductsDao productsDao = new ProductsInMemoDao();
            FlooringService service = new FlooringServiceImpl(failDao, productsDao, taxDao);

            Order orderToCheck1 = service.getOrderById(LocalDate.of(2019, 1, 28), 1);
            fail();

        } catch (OrderDoesNotExistException | InvalidDateException ex) {
            fail();
        } catch (ServiceLayerException ex) {

        }
    }

    @Test
    public void testGetOrderByBadId() {
        try {
            OrdersDao ordersDao = new OrdersInMemoDao();
            StateTaxDao taxDao = new StateTaxInMemoDao();
            ProductsDao productsDao = new ProductsInMemoDao();
            FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);

            Order orderToCheck1 = service.getOrderById(LocalDate.of(2019, 01, 28), 3);
            fail();
        } catch (OrderDoesNotExistException ex) {

        } catch (ServiceLayerException ex) {
            fail();
        } catch (InvalidDateException ex) {
            fail();
        }
    }

    /**
     * Test of addOrder method, of class FlooringServiceImpl.
     */
    @Test
    public void testAddOrderGoldenPath() throws Exception {
        OrdersDao ordersDao = new OrdersInMemoDao();
        StateTaxDao taxDao = new StateTaxInMemoDao();
        ProductsDao productsDao = new ProductsInMemoDao();
        FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);
        boolean commit = true;

        Order toAdd = new Order();

        toAdd.setOrderDate(LocalDate.of(2020, 1, 28));
        toAdd.setCustomerName("Whitehouse");
        toAdd.setState("MN");
        toAdd.setProductType("Wood");
        toAdd.setArea(new BigDecimal("100.00"));

        service.addOrder(toAdd, commit);

        Order orderToAssert = service.getOrderById(LocalDate.of(2020, 1, 28), 2);

        assertEquals(2, orderToAssert.getOrderNumber());
        assertEquals("Whitehouse", orderToAssert.getCustomerName());
        assertEquals("MN", orderToAssert.getState());
        assertEquals(new BigDecimal("6.25"), orderToAssert.getTaxRate());
        assertEquals("Wood", orderToAssert.getProductType());
        assertEquals(new BigDecimal("100.00"), orderToAssert.getArea());
        assertEquals(new BigDecimal("3.50"), orderToAssert.getCostPerSquareFoot());
        assertEquals(new BigDecimal("4.00"), orderToAssert.getLaborCostPerSquareFoot());
        assertEquals(new BigDecimal("350.00"), orderToAssert.getMaterialCost());
        assertEquals(new BigDecimal("400.00"), orderToAssert.getLaborCost());
        assertEquals(new BigDecimal("46.88"), orderToAssert.getTax());
        assertEquals(new BigDecimal("796.88"), orderToAssert.getTotal());

        //TODO: retrieve the item from the DAO and assert that its properties look correct
    }

    @Test
    public void testAddOrderInThePast() {
        try {
            OrdersDao ordersDao = new OrdersInMemoDao();
            StateTaxDao taxDao = new StateTaxInMemoDao();
            ProductsDao productsDao = new ProductsInMemoDao();
            FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);
            boolean commit = true;

            Order toAdd = new Order();

            toAdd.setOrderDate(LocalDate.of(2010, 1, 28));
            toAdd.setCustomerName("Whitehouse");
            toAdd.setState("MN");
            toAdd.setProductType("Wood");
            toAdd.setArea(new BigDecimal("100.00"));

            service.addOrder(toAdd, commit);
            fail();
        } catch (ServiceLayerException ex) {

        } catch (InvalidStateException | InvalidProductTypeException | InvalidAreaSizeException ex) {
            fail();
        }

    }

    @Test
    public void testAddOrderBadDao() {
        try {
            OrdersDao failDao = new OrdersAlwaysFailDao();
            StateTaxDao taxDao = new StateTaxInMemoDao();
            ProductsDao productsDao = new ProductsInMemoDao();
            FlooringService service = new FlooringServiceImpl(failDao, productsDao, taxDao);
            boolean commit = true;

            Order toAdd = new Order();

            toAdd.setOrderDate(LocalDate.MAX);
            toAdd.setCustomerName("Whitehouse");
            toAdd.setState("MN");
            toAdd.setProductType("Wood");
            toAdd.setArea(new BigDecimal("100"));

            service.addOrder(toAdd, commit);
            fail();
        } catch (ServiceLayerException ex) {

        } catch (InvalidStateException | InvalidProductTypeException | InvalidAreaSizeException ex) {
            fail();
        }

    }

    @Test
    public void testAddOrderBadState() {
        try {
            OrdersDao ordersDao = new OrdersInMemoDao();
            StateTaxDao taxDao = new StateTaxInMemoDao();
            ProductsDao productsDao = new ProductsInMemoDao();
            FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);
            boolean commit = true;

            Order toAdd = new Order();

            toAdd.setOrderDate(LocalDate.MAX);
            toAdd.setCustomerName("Whitehouse");
            toAdd.setState("IL");
            toAdd.setArea(new BigDecimal("100"));

            service.addOrder(toAdd, commit);
            fail();

        } catch (ServiceLayerException | InvalidProductTypeException | InvalidAreaSizeException ex) {
            fail();
        } catch (InvalidStateException ex) {

        }

    }

    @Test
    public void testAddOrderBlankProductType() {
        try {
            OrdersDao ordersDao = new OrdersInMemoDao();
            StateTaxDao taxDao = new StateTaxInMemoDao();
            ProductsDao productsDao = new ProductsInMemoDao();
            FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);
            boolean commit = true;

            Order toAdd = new Order();

            toAdd.setOrderDate(LocalDate.MAX);
            toAdd.setCustomerName("Whitehouse");
            toAdd.setState("MN");
            toAdd.setProductType("");
            toAdd.setArea(new BigDecimal("100"));

            service.addOrder(toAdd, commit);
            fail();
        } catch (ServiceLayerException | InvalidStateException | InvalidAreaSizeException ex) {
            fail();
        } catch (InvalidProductTypeException ex) {
        }

    }

    @Test
    public void testAddOrderInvalidProductType() {
        try {
            OrdersDao ordersDao = new OrdersInMemoDao();
            StateTaxDao taxDao = new StateTaxInMemoDao();
            ProductsDao productsDao = new ProductsInMemoDao();
            FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);
            boolean commit = true;

            Order toAdd = new Order();

            toAdd.setOrderDate(LocalDate.MAX);
            toAdd.setCustomerName("Whitehouse");
            toAdd.setState("MN");
            toAdd.setProductType("Plastic");
            toAdd.setArea(new BigDecimal("100"));

            service.addOrder(toAdd, commit);
            fail();
        } catch (ServiceLayerException | InvalidStateException | InvalidAreaSizeException ex) {
            fail();
        } catch (InvalidProductTypeException ex) {
        }

    }

    @Test
    public void testAddOrderNegativeArea() {
        try {
            OrdersDao ordersDao = new OrdersInMemoDao();
            StateTaxDao taxDao = new StateTaxInMemoDao();
            ProductsDao productsDao = new ProductsInMemoDao();
            FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);
            boolean commit = true;

            Order toAdd = new Order();

            toAdd.setOrderDate(LocalDate.MAX);
            toAdd.setCustomerName("Whitehouse");
            toAdd.setState("MN");
            toAdd.setProductType("Wood");
            toAdd.setArea(new BigDecimal("-100"));

            service.addOrder(toAdd, commit);
            fail();
        } catch (ServiceLayerException | InvalidStateException | InvalidProductTypeException ex) {
            fail();
        } catch (InvalidAreaSizeException ex) {
        }

    }

    /**
     * Test of editOrder method, of class FlooringServiceImpl.
     */
    @Test
    public void testEditOrderGoldenPath() throws Exception {
        OrdersDao ordersDao = new OrdersInMemoDao();
        StateTaxDao taxDao = new StateTaxInMemoDao();
        ProductsDao productsDao = new ProductsInMemoDao();
        FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);

        LocalDate orderDate = LocalDate.of(2019, 01, 28);
        int orderNumber = 1;
        Order orderToEdit = service.getOrderById(orderDate, orderNumber);

        Order editedOrder = new Order();
        editedOrder.setOrderDate(LocalDate.of(2019, 01, 28));
        editedOrder.setCustomerName("Whitehouse");
        editedOrder.setState("WI");
        editedOrder.setProductType("Vinyl");
        editedOrder.setArea(new BigDecimal("200.00"));

        service.editOrder(orderDate, editedOrder, orderNumber);

        assertEquals(LocalDate.of(2019, 01, 28), editedOrder.getOrderDate());
//removed users ability to edit orderNumber        
//assertEquals(1, editedOrder.getOrderNumber());
        assertEquals("Whitehouse", editedOrder.getCustomerName());
        assertEquals("WI", editedOrder.getState());
        assertEquals(new BigDecimal("5.5"), editedOrder.getTaxRate());
        assertEquals("Vinyl", editedOrder.getProductType());
        assertEquals(new BigDecimal("200.00"), editedOrder.getArea());
        assertEquals(new BigDecimal("2.50"), editedOrder.getCostPerSquareFoot());
        assertEquals(new BigDecimal("3.50"), editedOrder.getLaborCostPerSquareFoot());
        assertEquals(new BigDecimal("500.00"), editedOrder.getMaterialCost());
        assertEquals(new BigDecimal("700.00"), editedOrder.getLaborCost());
        assertEquals(new BigDecimal("66.00"), editedOrder.getTax());
        assertEquals(new BigDecimal("1266.00"), editedOrder.getTotal());
    }

    @Test
    public void testEditOrderBadDao() {
        try {
            OrdersDao failDao = new OrdersAlwaysFailDao();
            StateTaxDao taxDao = new StateTaxInMemoDao();
            ProductsDao productsDao = new ProductsInMemoDao();
            FlooringService service = new FlooringServiceImpl(failDao, productsDao, taxDao);

            LocalDate orderDate = LocalDate.of(2019, 01, 28);
            int orderNumber = 1;
            Order orderToEdit = service.getOrderById(orderDate, orderNumber);

            Order editedOrder = new Order();
            editedOrder.setOrderDate(LocalDate.of(2019, 01, 28));
            editedOrder.setCustomerName("Whitehouse");
            editedOrder.setState("WI");
            editedOrder.setProductType("Vinyl");
            editedOrder.setArea(new BigDecimal("200.00"));

            service.editOrder(orderDate, editedOrder, orderNumber);
            fail();

        } catch (OrderDoesNotExistException | InvalidDateException | InvalidProductTypeException | InvalidStateException ex) {
            fail();
        } catch (ServiceLayerException ex) {

        } catch (InvalidAreaSizeException ex) {
            fail();
        }
    }

    @Test
    public void testEditOrderAllBlank() throws Exception {
        OrdersDao ordersDao = new OrdersInMemoDao();
        StateTaxDao taxDao = new StateTaxInMemoDao();
        ProductsDao productsDao = new ProductsInMemoDao();
        FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);

        LocalDate orderDate = LocalDate.of(2019, 01, 28);
        int orderNumber = 1;
        Order orderToEdit = service.getOrderById(orderDate, orderNumber);
        LocalDate adjustedOrderDate = null;

        Order editedOrder = new Order();
        editedOrder.setOrderDate(adjustedOrderDate);
        editedOrder.setCustomerName("");
        editedOrder.setState("");
        editedOrder.setProductType("");
        editedOrder.setArea(null);

        service.editOrder(orderDate, editedOrder, orderNumber);

//        removed users Ability to edit orderNumber        
//        assertEquals(1, editedOrder.getOrderNumber());
        assertEquals("Smith", editedOrder.getCustomerName());
        assertEquals("MN", editedOrder.getState());
        assertEquals("Wood", editedOrder.getProductType());
        assertEquals(new BigDecimal("6.25"), editedOrder.getTaxRate());
        assertEquals(new BigDecimal("100"), editedOrder.getArea());
        assertEquals(new BigDecimal("3.50"), editedOrder.getCostPerSquareFoot());
        assertEquals(new BigDecimal("4.00"), editedOrder.getLaborCostPerSquareFoot());
        assertEquals(new BigDecimal("350.00"), editedOrder.getMaterialCost());
        assertEquals(new BigDecimal("400.00"), editedOrder.getLaborCost());
        assertEquals(new BigDecimal("46.88"), editedOrder.getTax());
        assertEquals(new BigDecimal("796.88"), editedOrder.getTotal());
    }

    @Test
    public void testEditOrderBadDate() {
        try {
            OrdersDao ordersDao = new OrdersInMemoDao();
            StateTaxDao taxDao = new StateTaxInMemoDao();
            ProductsDao productsDao = new ProductsInMemoDao();
            FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);

            LocalDate orderDate = LocalDate.of(2010, 01, 28);
            int orderNumber = 1;
            Order orderToEdit = service.getOrderById(orderDate, orderNumber);

            Order editedOrder = new Order();
            editedOrder.setOrderDate(LocalDate.of(2019, 01, 28));
            editedOrder.setCustomerName("Whitehouse");
            editedOrder.setState("WI");
            editedOrder.setProductType("Vinyl");
            editedOrder.setArea(new BigDecimal("200.00"));

            service.editOrder(orderDate, editedOrder, orderNumber);
            fail();

        } catch (OrderDoesNotExistException | ServiceLayerException | InvalidProductTypeException | InvalidStateException ex) {
            fail();
        } catch (InvalidDateException ex) {

        } catch (InvalidAreaSizeException ex) {
            fail();
        }
    }

    public void testEditOrderBadId() {
        try {
            OrdersDao ordersDao = new OrdersInMemoDao();
            StateTaxDao taxDao = new StateTaxInMemoDao();
            ProductsDao productsDao = new ProductsInMemoDao();
            FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);

            LocalDate orderDate = LocalDate.of(2019, 01, 28);
            int orderNumber = 100;
            Order orderToEdit = service.getOrderById(orderDate, orderNumber);

            Order editedOrder = new Order();
            editedOrder.setOrderDate(LocalDate.of(2019, 01, 28));
            editedOrder.setCustomerName("Whitehouse");
            editedOrder.setState("WI");
            editedOrder.setProductType("Vinyl");
            editedOrder.setArea(new BigDecimal("200.00"));

            service.editOrder(orderDate, editedOrder, orderNumber);
            fail();
        } catch (OrderDoesNotExistException ex) {

        } catch (ServiceLayerException | InvalidDateException | InvalidProductTypeException | InvalidStateException ex) {
            fail();
        } catch (InvalidAreaSizeException ex) {
            fail();
        }
    }

    @Test
    public void testEditOrderBadState() {
        try {
            OrdersDao ordersDao = new OrdersInMemoDao();
            StateTaxDao taxDao = new StateTaxInMemoDao();
            ProductsDao productsDao = new ProductsInMemoDao();
            FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);

            LocalDate orderDate = LocalDate.of(2019, 01, 28);
            int orderNumber = 1;
            Order orderToEdit = service.getOrderById(orderDate, orderNumber);

            //Order editedOrder = new Order();
            //editedOrder.setOrderDate(LocalDate.of(2019, 01, 28));
            orderToEdit.setCustomerName("Whitehouse");
            orderToEdit.setState("NY");
            orderToEdit.setProductType("Wood");
            orderToEdit.setArea(new BigDecimal("200.00"));

            service.editOrder(orderDate, orderToEdit, orderNumber);
            fail();

        } catch (OrderDoesNotExistException | ServiceLayerException | InvalidDateException | InvalidProductTypeException ex) {
            fail();
        } catch (InvalidStateException ex) {
        } catch (InvalidAreaSizeException ex) {
            fail();
        }
    }

    @Test
    public void testEditOrderBadProductType() {
        try {
            OrdersDao ordersDao = new OrdersInMemoDao();
            StateTaxDao taxDao = new StateTaxInMemoDao();
            ProductsDao productsDao = new ProductsInMemoDao();
            FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);

            LocalDate orderDate = LocalDate.of(2019, 01, 28);
            int orderNumber = 1;
            Order orderToEdit = service.getOrderById(orderDate, orderNumber);

            //Order editedOrder = new Order();
            orderToEdit.setOrderDate(LocalDate.of(2019, 01, 28));
            orderToEdit.setCustomerName("Whitehouse");
            orderToEdit.setState("MN");
            orderToEdit.setProductType("Plastic");
            orderToEdit.setArea(new BigDecimal("200.00"));

            service.editOrder(orderDate, orderToEdit, orderNumber);
            fail();
        } catch (OrderDoesNotExistException | ServiceLayerException | InvalidDateException | InvalidStateException ex) {
            fail();
        } catch (InvalidProductTypeException ex) {

        } catch (InvalidAreaSizeException ex) {
            fail();
        }
    }

    /**
     * Test of removeOrder method, of class FlooringServiceImpl.
     */
    @Test
    public void testRemoveOrderGoldenPath() throws Exception {
        OrdersDao ordersDao = new OrdersInMemoDao();
        StateTaxDao taxDao = new StateTaxInMemoDao();
        ProductsDao productsDao = new ProductsInMemoDao();
        FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);
        LocalDate orderDate = LocalDate.of(2019, 01, 28);

        service.removeOrder(orderDate, 1);

        assertEquals(1, service.getOrdersByDate(orderDate).size());

    }

    @Test
    public void testRemoveOrderBadDao() {
        try {
            OrdersDao failDao = new OrdersAlwaysFailDao();
            StateTaxDao taxDao = new StateTaxInMemoDao();
            ProductsDao productsDao = new ProductsInMemoDao();
            FlooringService service = new FlooringServiceImpl(failDao, productsDao, taxDao);
            LocalDate orderDate = LocalDate.of(2019, 01, 28);

            service.removeOrder(orderDate, 1);
            fail();

        } catch (ServiceLayerException ex) {

        } catch (InvalidDateException | OrderDoesNotExistException ex) {
            fail();
        }

    }

    @Test
    public void testRemoveOrderBadId() {
        try {
            OrdersDao ordersDao = new OrdersInMemoDao();
            StateTaxDao taxDao = new StateTaxInMemoDao();
            ProductsDao productsDao = new ProductsInMemoDao();
            FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);

            service.removeOrder(LocalDate.of(2019, 01, 28), 100);
            fail();
        } catch (OrderDoesNotExistException ex) {

        } catch (InvalidDateException | ServiceLayerException ex) {
            fail();
        }

    }

    @Test
    public void testRemoveOrderDateWithNoOrders() {
        OrdersDao ordersDao = new OrdersInMemoDao();
        StateTaxDao taxDao = new StateTaxInMemoDao();
        ProductsDao productsDao = new ProductsInMemoDao();
        FlooringService service = new FlooringServiceImpl(ordersDao, productsDao, taxDao);
        LocalDate orderDate = LocalDate.of(2050, 01, 28);

        try {

            service.removeOrder(orderDate, 1);
            fail();
        } catch (ServiceLayerException | OrderDoesNotExistException ex) {
            fail();
        } catch (InvalidDateException ex) {
            try {
                assertEquals(0, service.getOrdersByDate(orderDate).size());
            } catch (ServiceLayerException ex1) {
                fail();
            }

        }

    }

}
