/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.flooringmastery.Service;

import java.time.LocalDate;
import java.util.List;
import sg.flooringmastery.dtos.Order;

/**
 *
 * @author ddubs
 */
public interface FlooringService {
    
    List<Order> getOrdersByDate(LocalDate orderDate) throws ServiceLayerException;
    
    Order getOrderById(LocalDate orderDate, int orderNumber)throws OrderDoesNotExistException, 
            ServiceLayerException, InvalidDateException;
    
//    public Order constructOrder(Order toAdd) throws ServiceLayerException, InvalidStateException, InvalidProductTypeException;
    
    public Order addOrder(Order toAdd, boolean commit) throws ServiceLayerException, InvalidStateException, 
            InvalidProductTypeException, InvalidAreaSizeException;

    public void editOrder(LocalDate dateFromUser, Order editedOrder, int orderNumber)throws ServiceLayerException, 
                    InvalidProductTypeException, InvalidStateException, OrderDoesNotExistException, InvalidDateException,
                    InvalidAreaSizeException;

//    public Order getOrderToEdit(LocalDate dateFromUser, int orderNumber)throws ServiceLayerException, OrderDoesNotExistException;
    
    public void removeOrder(LocalDate dateFromUser, int orderNumber)throws ServiceLayerException, InvalidDateException, OrderDoesNotExistException;
}
