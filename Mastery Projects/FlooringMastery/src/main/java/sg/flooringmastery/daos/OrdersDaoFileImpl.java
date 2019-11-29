/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.flooringmastery.daos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import sg.flooringmastery.dtos.Order;

/**
 *
 * @author ddubs
 */
public class OrdersDaoFileImpl implements OrdersDao {

    String path;

    public OrdersDaoFileImpl(String path, String dir) {
        this.path = path;
    }

    @Override
    public List<Order> listOrdersByDate(LocalDate orderDate) throws OrdersDaoException {
        List<Order> orders = new ArrayList<>();

        Path datePath = Paths.get(path, buildFilePath(orderDate));

        FileReader reader = null;
        try {
            reader = new FileReader(datePath.toString());
            Scanner scn = new Scanner(reader);
            scn.nextLine();
            scn.nextLine();

            while (scn.hasNextLine()) {
                String line = scn.nextLine();
                if (line.length() > 0) {
                    String[] cells = line.split(",");
                    Order individualOrder = new Order();
                    individualOrder.setOrderDate(orderDate);
                    individualOrder.setOrderNumber(Integer.parseInt(cells[0]));
                    individualOrder.setCustomerName(cells[1]);
                    individualOrder.setState(cells[2]);
                    individualOrder.setTaxRate(new BigDecimal(cells[3]));
                    individualOrder.setProductType(cells[4]);
                    individualOrder.setArea(new BigDecimal(cells[5]));
                    individualOrder.setCostPerSquareFoot(new BigDecimal(cells[6]));
                    individualOrder.setLaborCostPerSquareFoot(new BigDecimal(cells[7]));

                    orders.add(individualOrder);
                }
            }
            return orders;
        } catch (FileNotFoundException ex) {
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException ex) {
                throw new OrdersDaoException("Could not close reader for " + path, ex);
            }
        }
        return orders;

    }

    private String buildFilePath(LocalDate orderDate) {
        String path = null;
        String date = orderDate.format(DateTimeFormatter.ofPattern("MMddyyyy"));
        return path = ("Orders_" + date + ".txt");
    }

    private void writeOrderToFile(List<Order> allOrders, LocalDate date) throws OrdersDaoException {
        FileWriter writer = null;

        try {
            Path datePath = Paths.get(path, buildFilePath(date));
            writer = new FileWriter(datePath.toString());
            PrintWriter pw = new PrintWriter(writer);
            pw.println("OrderNumber,CustomerName,State,TaxRate,ProductType,Area,CostPerSquareFoot,LaborCost");
            pw.println("PerSquareFoot,MaterialCost,LaborCost,Tax,Total");

            for (Order toWrite : allOrders) {
                String line = convertToLine(toWrite);
                pw.println(line);
            }

        } catch (IOException ex) {
            throw new OrdersDaoException("Error: could not write to " + path, ex);
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                throw new OrdersDaoException("Error: could not close writer for " + path, ex);
            }
        }

    }

    private String convertToLine(Order toWrite) {
        String line
                = toWrite.getOrderNumber() + ","
                + toWrite.getCustomerName() + ","
                + toWrite.getState() + ","
                + toWrite.getTaxRate() + ","
                + toWrite.getProductType() + ","
                + toWrite.getArea() + ","
                + toWrite.getCostPerSquareFoot() + ","
                + toWrite.getLaborCostPerSquareFoot() + ","
                + toWrite.getMaterialCost() + ","
                + toWrite.getLaborCost() + ","
                + toWrite.getTax() + ","
                + toWrite.getTotal();

        return line;
    }

    @Override
    public void addOrder(Order toAdd) throws OrdersDaoException {
        List<Order> allOrders = listOrdersByDate(toAdd.getOrderDate());

        allOrders.add(toAdd);

        writeOrderToFile(allOrders, toAdd.getOrderDate());

    }

    @Override
    public void removeOrder(LocalDate dateFromUser, int orderNumber) throws OrdersDaoException {
        List<Order> allOrders = listOrdersByDate(dateFromUser);

        int matchIndex = -1;

        for (int i = 0; i < allOrders.size(); i++) {
            Order toCheck = allOrders.get(i);

            if (toCheck.getOrderNumber() == orderNumber) {
                matchIndex = i;
                break;
            }
        }

        if (matchIndex == -1) {
            throw new OrdersDaoException("Could not remove order with orderNumber" + orderNumber);
        }

        allOrders.remove(matchIndex);

        writeOrderToFile(allOrders, dateFromUser);

    }

    @Override
    public void editOrder(Order originalOrder, Order editedOrder) throws OrdersDaoException {
        if (originalOrder.getOrderDate() == editedOrder.getOrderDate()) {
            removeOrder(originalOrder.getOrderDate(), originalOrder.getOrderNumber());

            addOrder(editedOrder);
        } else {
            throw new OrdersDaoException("Order does not exist.");
        }
    }

    @Override
    public Order getOrderById(LocalDate orderDate, int orderNumber) throws OrdersDaoException {
        Order orderToReturn = null;
        List<Order> orders = listOrdersByDate(orderDate);
        for (Order toView : orders) {
            if (toView.getOrderNumber() == orderNumber) {
                orderToReturn = toView;
                break;
            }
        }
        if(orderToReturn == null){
            throw new OrdersDaoException("Order does not Exist.");
        }
        return orderToReturn;
    }
}
