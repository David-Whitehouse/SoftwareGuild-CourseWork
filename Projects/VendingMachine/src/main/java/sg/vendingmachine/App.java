/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.vendingmachine;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sg.vendingmachine.Controller.VendingMachineController;
import sg.vendingmachine.Dao.VendingMachineDao;
import sg.vendingmachine.Dao.VendingMachineDaoException;
import sg.vendingmachine.Dao.VendingMachineDaoFileImpl;
import sg.vendingmachine.Service.VendingMachineServiceLayer;
import sg.vendingmachine.Service.VendingMachineServiceLayerImpl;
import sg.vendingmachine.ui.UserIO;
import sg.vendingmachine.ui.UserIOConsoleImpl;
import sg.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author ddubs
 */
public class App {
    public static void main(String[] args){
//        String path = "VendingMachineInventory.txt";
//        UserIO myIo = new UserIOConsoleImpl();
//        VendingMachineView myView = new VendingMachineView(myIo);
//        VendingMachineDao myDao = new VendingMachineDaoFileImpl(path);
//        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao);
//        VendingMachineController controller = new VendingMachineController(myService, myView);

        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        VendingMachineController controller = ctx.getBean("controller", VendingMachineController.class);

        controller.run();
    }
}
