/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.flooringmastery.Controller;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.flooringmastery.Service.FlooringService;
import sg.flooringmastery.Service.InvalidAreaSizeException;
import sg.flooringmastery.Service.InvalidDateException;
import sg.flooringmastery.Service.InvalidProductTypeException;
import sg.flooringmastery.Service.InvalidStateException;
import sg.flooringmastery.Service.OrderDoesNotExistException;
import sg.flooringmastery.Service.ServiceLayerException;
import sg.flooringmastery.dtos.Order;
import ui.FlooringView;

/**
 *
 * @author ddubs
 */
public class FlooringController {

    int menuSelection = 0;
    FlooringView view;
    FlooringService service;

    public FlooringController(FlooringView view, FlooringService service) {
        this.view = view;
        this.service = service;
    }

    public void run() {
        boolean keepGoing = true;

        while (keepGoing) {
            try {
                menuSelection = welcomeScreenAndGetSelection();
                switch (menuSelection) {
                    case 1:
                        displayOrders();
                        break;
                    case 2:
                        addOrder();
                        break;
                    case 3:
                        editOrder();
                        break;
                    case 4:
                        removeOrder();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            } catch (ServiceLayerException | InvalidStateException | InvalidProductTypeException
                    | OrderDoesNotExistException | InvalidAreaSizeException | InvalidDateException ex) {
                view.displayErrorMessage(ex.getMessage());
            }
        }

        view.goodByeMessage();
    }

    private int welcomeScreenAndGetSelection() {
        return view.printMenuAndGetSelection();
    }

    private void displayOrders() throws OrderDoesNotExistException, InvalidDateException {
        // ask user for date to look up orders
        try {
            LocalDate orderDate = view.getDateFromUser();
            List<Order> ordersByDate = service.getOrdersByDate(orderDate);
            // display orders for date
            view.displayOrdersByDate(ordersByDate, orderDate);

            // if the date does not return any orders display no orders for date message
            if (ordersByDate.isEmpty()) {
                view.displayNoOrdersMessage();
            } else {

                // if date does return orders show prompt for user to see more details
                Order orderToDisplay = service.getOrderById(orderDate,
                        view.requestMoreDetails());

                if (orderToDisplay != null) {
                    view.displayOrderDetails(orderToDisplay);
                }
            }
        } catch (ServiceLayerException ex) {
            view.displayErrorMessage(ex.getMessage());
        }
    }

    private void addOrder() throws ServiceLayerException, InvalidStateException, InvalidProductTypeException, InvalidAreaSizeException {
        //use view to request date for the order to be added
        //use view to request userinput for each field required by the Order object
        Order newOrder = view.getNewOrderInfo();
        //service layer should use OrdersDao to determine what the next order number is and construct missing pieces of Order object
//        service.constructOrder(newOrder);
        newOrder = service.addOrder(newOrder, view.getAddApprovalFromUser(newOrder));
        
        //use view to show all data to be added and let user decide to place order or to discard order
//        if (newOrder.getTotal() != null) {
        view.displayOrderDetails(newOrder);
//            if (view.getApprovalFromUser()) {
//                service.addOrder(newOrder);
//            }
//        }
    }

    private void editOrder() throws ServiceLayerException, InvalidProductTypeException, InvalidStateException, OrderDoesNotExistException, InvalidDateException, InvalidAreaSizeException {
        //use view to ask user for date of order and order # to edit
        //have service get the order and display order details to user
        //ask the user to update the fields for the order, if the user hits enter and doesn't type anything
        LocalDate orderDate = view.getDateFromUser();
        int orderNumber = view.getOrderNumber();
        Order orderToEdit = service.getOrderById(orderDate, orderNumber);
        Order editedOrder = view.editFields(orderToEdit);
        service.editOrder(orderDate, editedOrder, orderNumber);
        //make sure service layer does not modify that field

    }

    private void removeOrder() throws ServiceLayerException, OrderDoesNotExistException, InvalidDateException {
        Order orderToRemove = null;
        LocalDate orderDate = null;
        int orderNumber = -1;
        while (orderToRemove == null) {
            orderDate = view.getDateFromUser();
            orderNumber = view.getOrderNumber();
            orderToRemove = service.getOrderById(orderDate, orderNumber);

        }
        view.displayOrderDetails(orderToRemove);

        if (view.getApprovalFromUserForRemoval()) {
            service.removeOrder(orderDate, orderNumber);
            view.removeSuccess();
        }

    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }
}
