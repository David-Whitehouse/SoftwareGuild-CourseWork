/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.classroster;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import sg.classroster.controller.ClassRosterController;
import sg.classroster.dao.ClassRosterAuditDao;
import sg.classroster.dao.ClassRosterAuditDaoFileImpl;
import sg.classroster.dao.ClassRosterDao;
import sg.classroster.dao.ClassRosterDaoFileImpl;
import sg.classroster.service.ClassRosterServiceLayer;
import sg.classroster.service.ClassRosterServiceLayerImpl;
import sg.classroster.ui.ClassRosterView;
import sg.classroster.ui.UserIO;
import sg.classroster.ui.UserIOConsoleImpl;

/**
 *
 * @author ddubs
 */
public class App {
    public static void main(String[] args) {
//// Instantiate the UserIO implementation
//    UserIO myIo = new UserIOConsoleImpl();
//    // Instantiate the View and wire the UserIO implementation into it
//    ClassRosterView myView = new ClassRosterView(myIo);
//    // Instantiate the DAO
//    ClassRosterDao myDao = new ClassRosterDaoFileImpl();
//    // Instantiate the Audit DAO
//    ClassRosterAuditDao myAuditDao = new ClassRosterAuditDaoFileImpl();
//    // Instantiate the Service Layer and wire the DAO and Audit DAO into it
//    ClassRosterServiceLayer myService = new ClassRosterServiceLayerImpl(myDao, myAuditDao);
//    // Instantiate the Controller and wire the Service Layer into it
//    ClassRosterController controller = new ClassRosterController(myService, myView);
//    // Kick off the Controller
//    controller.run();

        ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
        ClassRosterController controller = 
           ctx.getBean("controller", ClassRosterController.class);
        controller.run();
    }
 
}
