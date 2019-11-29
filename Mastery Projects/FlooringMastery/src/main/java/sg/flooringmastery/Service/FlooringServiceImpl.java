/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.flooringmastery.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.flooringmastery.daos.OrdersDao;
import sg.flooringmastery.daos.OrdersDaoException;
import sg.flooringmastery.daos.ProductsDao;
import sg.flooringmastery.daos.ProductsDaoException;
import sg.flooringmastery.daos.StateTaxDao;
import sg.flooringmastery.daos.StateTaxDaoException;
import sg.flooringmastery.dtos.Order;
import sg.flooringmastery.dtos.Product;
import sg.flooringmastery.dtos.State;

/**
 *
 * @author ddubs
 */
public class FlooringServiceImpl implements FlooringService {

    OrdersDao orderDao;
    ProductsDao productsDao;
    StateTaxDao taxDao;

    public FlooringServiceImpl(OrdersDao orderDao, ProductsDao productsDao, StateTaxDao taxDao) {
        this.orderDao = orderDao;
        this.productsDao = productsDao;
        this.taxDao = taxDao;
    }

    @Override
    public List<Order> getOrdersByDate(LocalDate orderDate) throws ServiceLayerException {
        List<Order> ordersToReturn = null;

        try {
            ordersToReturn = orderDao.listOrdersByDate(orderDate);

        } catch (OrdersDaoException ex) {
            throw new ServiceLayerException("Could not find file", ex);
        }

        return ordersToReturn;
    }

    @Override
    public Order getOrderById(LocalDate orderDate, int orderNumber) throws OrderDoesNotExistException, ServiceLayerException, InvalidDateException {
        Order orderToReturn = null;
        List<Order> orders = getOrdersByDate(orderDate);

        if (orders.isEmpty()) {
            throw new InvalidDateException("No orders for date selected.");
        }

        for (Order toView : orders) {
            if (toView.getOrderNumber() == orderNumber) {
                orderToReturn = toView;
                break;
            }
        }

        if (orderToReturn == null) {
            throw new OrderDoesNotExistException("Order does not exist.");
        }

        return orderToReturn;
    }

    @Override
    public Order addOrder(Order toAdd, boolean commit) throws ServiceLayerException, InvalidStateException, InvalidProductTypeException, InvalidAreaSizeException {

        if (commit) {
            setNextOrderNumber(toAdd);
            constructOrder(toAdd);
            if (toAdd != null) {
                if (toAdd.getOrderDate().isBefore(LocalDate.now())) {
                    throw new ServiceLayerException("Cannot add order for date in the past.");
                }
                try {
                    orderDao.addOrder(toAdd);
                } catch (OrdersDaoException ex) {
                    throw new ServiceLayerException("Could not add order to list.");
                }
            }
        }

        return toAdd;

    }

    private Order constructOrder(Order toAdd) throws ServiceLayerException, InvalidStateException, InvalidProductTypeException, InvalidAreaSizeException {

        setTaxRate(toAdd);
        if (toAdd.getTaxRate() != null) {
            setMaterialCostAndLaborCost(toAdd);
            if (toAdd.getProductType() != null) {
                BigDecimal min = new BigDecimal("10");
                BigDecimal max = new BigDecimal("100000");
                if (toAdd.getArea().compareTo(min) > 0 && toAdd.getArea().compareTo(max) < 0) {
                    return toAdd;
                } else {
                    throw new InvalidAreaSizeException("Area must be between 10-100000 sq ft.");
                }
            }
            toAdd = null;
        }
        return toAdd;
    }

    private void setNextOrderNumber(Order toAdd) throws ServiceLayerException {
        List<Order> allOrders = getOrdersByDate(toAdd.getOrderDate());

        int newId = 0;

        for (Order toCheck : allOrders) {
            if (toCheck.getOrderNumber() > newId) {
                newId = toCheck.getOrderNumber();
            }
        }

        newId++;
        toAdd.setOrderNumber(newId);
    }

    private void setTaxRate(Order toAdd) throws ServiceLayerException, InvalidStateException {
        List<State> states;
        try {
            states = taxDao.getAllStates();
        } catch (StateTaxDaoException ex) {
            throw new ServiceLayerException("could not find States List.");
        }

        State matched = null;

        for (State toCheck : states) {
            if (toCheck.getStateName().toUpperCase().equals(toAdd.getState().toUpperCase())) {
                matched = toCheck;
                break;
            }

        }
        if (matched == null) {
            throw new InvalidStateException("Invalid State.");
        }

        toAdd.setTaxRate(matched.getTaxRate());

    }

    private void setMaterialCostAndLaborCost(Order toAdd) throws ServiceLayerException, InvalidProductTypeException {
        List<Product> products;
        try {
            products = productsDao.getAllProducts();
        } catch (ProductsDaoException ex) {
            throw new ServiceLayerException("Could not find Products List.");
        }

        Product matched = null;
        for (Product toCheck : products) {

            if (toCheck.getProductType().toUpperCase().equals(toAdd.getProductType().toUpperCase())) {
                matched = toCheck;
                break;

            }

        }

        if (matched == null) {

            throw new InvalidProductTypeException("Product Type is Invalid");
        }

        toAdd.setCostPerSquareFoot(matched.getCostPerSquareFoot());
        toAdd.setLaborCostPerSquareFoot(matched.getLaborCostPerSquareFoot());
    }

    @Override
    public void editOrder(LocalDate dateFromUser, Order editedOrder, int orderNumber) throws ServiceLayerException,
            InvalidProductTypeException, InvalidStateException, OrderDoesNotExistException, InvalidDateException,
            InvalidAreaSizeException {
        Order orderToEdit = getOrderById(dateFromUser, orderNumber);

//        if (editedOrder.getOrderDate() == null || editedOrder.getOrderDate().equals(orderToEdit.getOrderDate())) {
//            editedOrder.setOrderDate(orderToEdit.getOrderDate());
//            editedOrder.setOrderNumber(orderNumber);
//        } else {
//            setNextOrderNumber(editedOrder);
//        }
        if (editedOrder.getCustomerName().equals("")) {
            editedOrder.setCustomerName(orderToEdit.getCustomerName());
        }

        if (editedOrder.getState().equals("")) {
            editedOrder.setState(orderToEdit.getState());
        }

        if (editedOrder.getProductType().equals("")) {
            editedOrder.setProductType(orderToEdit.getProductType());
        }

        if (editedOrder.getArea() == null) {
            editedOrder.setArea(orderToEdit.getArea());
        }
        constructOrder(editedOrder);
//        setTaxRate(editedOrder);
//        if (editedOrder.getTaxRate() != null) {
//            setMaterialCostAndLaborCost(editedOrder);
//            if (editedOrder.getProductType() != null) {

        try {
            orderDao.editOrder(orderToEdit, editedOrder);

//                removeOrder(orderToEdit.getOrderDate(), orderToEdit.getOrderNumber());
//                addOrder(editedOrder);
        } catch (OrdersDaoException ex) {
            throw new ServiceLayerException("Could not edit order.");
        }
        //}
        //}

    }

    @Override
    public void removeOrder(LocalDate dateFromUser, int orderNumber) throws ServiceLayerException, InvalidDateException, OrderDoesNotExistException {

        try {
            getOrderById(dateFromUser, orderNumber);

            orderDao.removeOrder(dateFromUser, orderNumber);
        } catch (OrdersDaoException ex) {
            throw new ServiceLayerException("Could not remove order.");
        }

    }
}
