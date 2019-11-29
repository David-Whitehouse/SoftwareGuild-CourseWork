/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.flooringmastery.daos;

import java.time.LocalDate;
import java.util.List;
import sg.flooringmastery.dtos.Order;

/**
 *
 * @author ddubs
 */
public interface OrdersDao {

    public List<Order> listOrdersByDate(LocalDate orderDate) throws OrdersDaoException;

    //public void writeOrderToFile(List<Order> allOrders, LocalDate datePath) throws OrdersDaoException;

    public void addOrder(Order toAdd) throws OrdersDaoException;

    public void removeOrder(LocalDate dateFromUser, int orderNumber) throws OrdersDaoException;
    
    public void editOrder(Order originalOrder, Order editedOrder) throws OrdersDaoException;
    
    public Order getOrderById(LocalDate orderDate, int orderNumber) throws OrdersDaoException;

}
