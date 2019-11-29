/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.flooringmastery.daos;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import sg.flooringmastery.dtos.Order;

/**
 *
 * @author ddubs
 */
public class OrdersInMemoDao implements OrdersDao {
    
    private List<Order> ordersList = new ArrayList<>();
    
        public OrdersInMemoDao() {
        Order order1 = new Order();
           order1.setOrderDate(LocalDate.of(2019, 1, 28));
           order1.setOrderNumber(1);
           order1.setCustomerName("Smith");
           order1.setState("MN");
           order1.setTaxRate(new BigDecimal("6.25"));
           order1.setProductType("Wood");
           order1.setCostPerSquareFoot(new BigDecimal("3.50"));
           order1.setLaborCostPerSquareFoot(new BigDecimal("4.00"));
           order1.setArea(new BigDecimal("100"));
           
        Order order2 = new Order();
           order2.setOrderDate(LocalDate.of(2019, 1, 28));
           order2.setOrderNumber(2);
           order2.setCustomerName("Doe");
           order2.setState("WI");
           order2.setTaxRate(new BigDecimal("5.5"));
           order2.setProductType("Vinyl");
           order2.setCostPerSquareFoot(new BigDecimal("2.50"));
           order2.setLaborCostPerSquareFoot(new BigDecimal("3.50"));
           order2.setArea(new BigDecimal("150"));
           
        Order order3 = new Order();
           order3.setOrderDate(LocalDate.of(2020, 1, 28));
           order3.setOrderNumber(1);
           order3.setCustomerName("Smith");
           order3.setState("MN");
           order3.setTaxRate(new BigDecimal("6.25"));
           order3.setProductType("Wood");
           order3.setCostPerSquareFoot(new BigDecimal("3.50"));
           order3.setLaborCostPerSquareFoot(new BigDecimal("4.00"));
           order3.setArea(new BigDecimal("100"));
           
            ordersList.add(order1);
            ordersList.add(order2);
            ordersList.add(order3);
    }
    
    @Override
    public List<Order> listOrdersByDate(LocalDate orderDate) throws OrdersDaoException {
        List<Order> returnedOrders = new ArrayList<>();
        for(Order toCheck : ordersList){
        if(toCheck.getOrderDate().equals(orderDate)){
        returnedOrders.add(toCheck);
        }
        }
        return returnedOrders;
    }


    @Override
    public void addOrder(Order toAdd) throws OrdersDaoException {
        List<Order> allOrders = listOrdersByDate(toAdd.getOrderDate());
        
        ordersList.add(toAdd);
   
    }

    @Override
    public void removeOrder(LocalDate dateFromUser, int orderNumber) throws OrdersDaoException {
       List<Order> getOrders = listOrdersByDate(dateFromUser);
        
       int matchIndex = -1;

        for (int i = 0; i < getOrders.size(); i++) {
            Order toCheck = getOrders.get(i);

            if (toCheck.getOrderNumber() == orderNumber) {
                matchIndex = i;
                break;
            }
        }

        if (matchIndex == -1) {
            throw new OrdersDaoException("Could not remove order with orderNumber" + orderNumber);
        }

        ordersList.remove(matchIndex);
        
    }


    @Override
    public void editOrder(Order originalOrder, Order editedOrder) throws OrdersDaoException {
        removeOrder(originalOrder.getOrderDate(), originalOrder.getOrderNumber());
        
        addOrder(editedOrder);
    }

    @Override
    public Order getOrderById(LocalDate orderDate, int orderNumber) throws OrdersDaoException {
        Order orderToReturn = null;
        List<Order> orders = listOrdersByDate(orderDate);
        for (Order toView : orders) {
            if (toView.getOrderNumber() == orderNumber && toView.getOrderDate() == orderDate) {
                orderToReturn = toView;
                break;
            } 
        }
       return orderToReturn;
    }
}
