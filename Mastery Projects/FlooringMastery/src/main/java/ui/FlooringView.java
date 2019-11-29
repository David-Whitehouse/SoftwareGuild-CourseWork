/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import sg.flooringmastery.dtos.Order;

/**
 *
 * @author ddubs
 */
public class FlooringView {
    private UserIO io;
    
    public FlooringView(UserIO io){
    this.io = io;
    }
    
    public int printMenuAndGetSelection() {
        io.print("\n" + "****************************************** \n");
        io.print("              Flooring Program \n");
        io.print("             1. Display Orders \n");
        io.print("             2. Add an Order \n");
        io.print("             3. Edit an Order \n");
        io.print("             4. Remove an Order \n");
        io.print("             5. Exit \n");
        io.print("****************************************** \n");

        return io.readInt("Please select from the " + "above choices  ", 1, 5);
    }
    
    
    public void goodByeMessage() {
        io.print("Good-bye!");
    }

    public void displayUnknownCommandBanner() {
        io.print("Unknown Command...");
    }

    public void displayOrdersByDate(List<Order> orders, LocalDate orderDate) {
        io.print("\n" + "Orders for " + orderDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + "\n");
        io.print("______________________________________ \n");
        orders.stream().forEach(order -> io.print(order.getOrderNumber()
                + ". " + order.getCustomerName()
                + "  Total Cost: $ " + order.getTotal()
                + "\n"));
    }

    public LocalDate getDateFromUser() {
       return io.readDate("Please enter a date for looking up orders (MM/DD/YYYY):  ");
        
    }


    public void displayErrorMessage(String message) {
        io.print("Error: " + message);
    }

    public void displayNoOrdersMessage(){
        io.print("No Orders for date selected. \n");
        io.print("______________________________________ \n");
    }
    
        public void displayNoOrdersMessage(int id){
        io.print("No orders match order number " +id+ " for date selected. \n");
        io.print("______________________________________ \n");
    }

    public int requestMoreDetails() {
        return io.readIntOrEnterToContinue("Please provide an ID number to view all details for the order or hit enter to continue.  ");
    }

    public void displayOrderDetails(Order orderToDisplay) {
        io.print("Order Number: " + orderToDisplay.getOrderNumber() + "\n");
        io.print("Customer Name: " + orderToDisplay.getCustomerName() + "\n");
        io.print("State: " + orderToDisplay.getState() + "\n");
        io.print("Tax Rate: " + orderToDisplay.getTaxRate() + "\n");
        io.print("Product Type: " + orderToDisplay.getProductType() + "\n");
        io.print("Area: " + orderToDisplay.getArea() + "\n");
        io.print("Materials Cost Per Square Foot: $" + orderToDisplay.getCostPerSquareFoot() + "\n");
        io.print("Labor Cost Per Square Foot: $" + orderToDisplay.getLaborCostPerSquareFoot()+ "\n");
        io.print("Total Materials Cost: $" + orderToDisplay.getMaterialCost() + "\n");
        io.print("Total Labor Cost: $" + orderToDisplay.getLaborCost() + "\n");
        io.print("Total Tax: $" + orderToDisplay.getTax() + "\n");
        io.print("Total Cost: $" + orderToDisplay.getTotal() + "\n");
  
    }

    public Order getNewOrderInfo() {
        Order toAdd = new Order();
        io.print("Please enter the following order items - \n");
        toAdd.setOrderDate(io.readDate("Date for the order (MM/DD/YYYY): \n"));   //, LocalDate.now().minusDays(1), LocalDate.now().plusDays(365)));
        toAdd.setCustomerName(io.readStringNotBlank("Customer Last Name: \n"));
        toAdd.setState(io.readString("State Abbreviation (ex: MN): \n"));
        toAdd.setProductType(io.readString("Product Type: \n"));
//        BigDecimal min = new BigDecimal("10");
//        BigDecimal max = new BigDecimal("100000");
        toAdd.setArea(io.readBigDecimal("Floor Area: \n"));
        
        
        return toAdd;
    }

    public boolean getApprovalFromUser() {
       
        boolean approval = io.readYesOrNo("Would you like to place the order listed above? (enter 'y' for yes or 'n' for no): ");
        return approval;
    }
    
        public boolean getApprovalFromUserForRemoval() {
       
        boolean approval = io.readYesOrNo("Would you like to remove the order listed above? (enter 'y' for yes or 'n' for no): ");
        return approval;
    }

    public int getOrderNumber() {
        int orderToEdit =
        io.readInt("Please enter the order number to edit.");
        
        return orderToEdit;
    }

    public Order editFields(Order toEdit) {
        Order editedOrder = toEdit;
        io.print("Please edit the following fields (if you would like to not modify the field press enter): \n");
//        editedOrder.setOrderDate(io.readEditDate("Please enter order date(" 
//                + toEdit.getOrderDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")) + "):"));
        editedOrder.setCustomerName(io.readString("Please enter custome Name(" + toEdit.getCustomerName() + "):"));
        editedOrder.setState(io.readString("Please enter State(" + toEdit.getState() + "):"));
        editedOrder.setProductType(io.readString("Please enter Product Type(" + toEdit.getProductType() + "):"));
        editedOrder.setArea(io.readEditBigDecimal("Please enter custome Name(" + toEdit.getArea() + "):"));
        
        return editedOrder;
    }

    public void removeSuccess() {
    io.print("Order Successfully Removed");
    }

    public boolean getAddApprovalFromUser(Order orderToAdd) {
        io.print("Order Date: " + orderToAdd.getOrderDate()+ "\n");
        io.print("Customer Name: " + orderToAdd.getCustomerName() + "\n");
        io.print("State: " + orderToAdd.getState() + "\n");
        io.print("Product Type: " + orderToAdd.getProductType() + "\n");
        io.print("Area: " + orderToAdd.getArea() + "\n");
        boolean approval = io.readYesOrNo("Would you like to place the order listed above? (enter 'y' for yes or 'n' for no): ");
        return approval;
    }
    
}
