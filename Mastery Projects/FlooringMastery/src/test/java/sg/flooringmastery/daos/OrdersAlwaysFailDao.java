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
public class OrdersAlwaysFailDao implements OrdersDao{

    @Override
    public List<Order> listOrdersByDate(LocalDate orderDate) throws OrdersDaoException {
        throw new OrdersDaoException("Always Fail Dao."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void addOrder(Order toAdd) throws OrdersDaoException {
        throw new OrdersDaoException("Always Fail Dao."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void removeOrder(LocalDate dateFromUser, int orderNumber) throws OrdersDaoException {
        throw new OrdersDaoException("Always Fail Dao."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void editOrder(Order originalOrder, Order editedOrder) throws OrdersDaoException {
        throw new OrdersDaoException("Always Fail Dao."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order getOrderById(LocalDate orderDate, int orderNumber) throws OrdersDaoException {
        throw new OrdersDaoException("Always Fail Dao."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
