/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.flooringmastery;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sg.flooringmastery.Controller.FlooringController;
import sg.flooringmastery.Service.FlooringService;
import sg.flooringmastery.Service.FlooringServiceImpl;
import sg.flooringmastery.daos.OrdersDao;
import sg.flooringmastery.daos.OrdersDaoFileImpl;
import sg.flooringmastery.daos.ProductsDao;
import sg.flooringmastery.daos.ProductsDaoFileImpl;
import sg.flooringmastery.daos.StateTaxDao;
import sg.flooringmastery.daos.StateTaxDaoFileImpl;
import ui.FlooringView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

/**
 *
 * @author ddubs
 */
public class App {
    public static void main(String[] args) {
        String path = null;
        
//        UserIO myIo = new UserIOConsoleImpl();
//        FlooringView myView = new FlooringView(myIo);
//        OrdersDao myOrdersDao = new OrdersDaoFileImpl("Orders", "Orders");
//        ProductsDao myProductsDao = new ProductsDaoFileImpl("Products.txt");
//        StateTaxDao myTaxDao = new StateTaxDaoFileImpl("Taxes.txt");
//        FlooringService myService = new FlooringServiceImpl(myOrdersDao, myProductsDao, myTaxDao);
//        FlooringController controller = new FlooringController(myView, myService);
//        controller.run();

        ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringController controller = 
           ctx.getBean("controller", FlooringController.class);
        controller.run();
    }
}
