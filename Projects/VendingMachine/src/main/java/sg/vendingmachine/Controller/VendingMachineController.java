/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.vendingmachine.Controller;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.vendingmachine.Dao.VendingMachineDao;
import sg.vendingmachine.Dao.VendingMachineDaoException;
import sg.vendingmachine.Service.InsufficientFundsException;
import sg.vendingmachine.Service.InvalidIdException;
import sg.vendingmachine.Service.OutOfStockException;
import sg.vendingmachine.Service.VendingMachineServiceLayer;
import sg.vendingmachine.Service.VendingMachineServiceLayerException;
import sg.vendingmachine.dtos.Item;
import sg.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author ddubs
 */
public class VendingMachineController {

    int menuSelection = 0;
    VendingMachineServiceLayer service;
    VendingMachineView view;

    public VendingMachineController(VendingMachineServiceLayer service, VendingMachineView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean keepGoing = true;
        welcomeScreenAndDisplayItems();
            
            while (keepGoing) {
                menuSelection = getMenuSelection();
                switch (menuSelection) {
                    case 1:
                        insertMoney();
                        break;
                    case 2:
                        vendItem();
                        break;
                    case 3:
                        returnChange();
                        break;
                    case 4:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            }

        view.goodByeMessage();

    }

    private int getMenuSelection() {
        return view.printMenuAndGetSelection();

    }

    private void unknownCommand() {
        view.displayUnknownCommandBanner();
    }

    private void welcomeScreenAndDisplayItems(){
        view.displayWelcome();
        
        try {
            view.displayItemList(service.grabAllItems());
        } catch (VendingMachineServiceLayerException ex) {
          view.displayErrorMessage(ex.getMessage());
        }
    }

    private void vendItem(){
        try {
            Item itemToVend = service.getItemById(view.getVendingSelection());

            boolean hasErrors = false;
            if (!hasErrors) {
                try {
                    service.vendItem(itemToVend.getId());
                } catch (OutOfStockException | InsufficientFundsException | VendingMachineServiceLayerException | InvalidIdException ex) {
                    hasErrors = true;
                    view.displayErrorMessage(ex.getMessage());

                }

                if (!hasErrors) {
                    view.purchaseMessage(itemToVend);
                    returnChange();
                }
            }

        } catch (InvalidIdException | VendingMachineServiceLayerException ex) {
            view.displayErrorMessage(ex.getMessage());
        }
    }

    private void returnChange() {
        view.returnChangeMessage(service.returnChange());

    }

    private void insertMoney() {
        try {
            service.insertMoney(view.insertMoneyMessage());
        } catch (VendingMachineServiceLayerException ex) {
            view.displayErrorMessage(ex.getMessage());
        }
    }

}
