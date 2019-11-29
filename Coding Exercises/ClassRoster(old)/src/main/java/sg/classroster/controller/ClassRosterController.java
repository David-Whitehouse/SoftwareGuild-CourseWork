/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.classroster.controller;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import sg.classroster.dao.ClassRosterDao;
import sg.classroster.dao.ClassRosterDaoFileImpl;
import sg.classroster.dao.ClassRosterPersistenceException;
import sg.classroster.dto.Student;
import sg.classroster.service.ClassRosterDataValidationException;
import sg.classroster.service.ClassRosterDuplicateIdException;
import sg.classroster.service.ClassRosterServiceLayer;
import sg.classroster.ui.ClassRosterView;
import sg.classroster.ui.UserIO;
import sg.classroster.ui.UserIOConsoleImpl;

/**
 *
 * @author ddubs
 */
public class ClassRosterController {

    ClassRosterView view;
    ClassRosterServiceLayer service;
    private UserIO io = new UserIOConsoleImpl();

public ClassRosterController(ClassRosterServiceLayer service, ClassRosterView view) {
    this.service = service;
    this.view = view;
}    
    
    public void run() {
        boolean keepGoing = true;
        int menuSelection = 0;
        while (keepGoing) {
            
            try {
                menuSelection = getMenuSelection();
                
                switch (menuSelection) {
                    case 1:
                        listStudents();
                        break;
                    case 2:
                        createStudent();
                        break;
                    case 3:
                        viewStudent();
                        break;
                    case 4:
                        removeStudent();
                        break;
                    case 5:
                        keepGoing = false;
                        break;
                    default:
                        unknownCommand();
                }
            } catch (ClassRosterPersistenceException ex) {
                view.displayErrorMessage("Error: " + ex);
                        
            }

        }
        exitMessage();
    }
    
    private int getMenuSelection() {
        return view.printMenuAndGetSelection();
    }
    private void createStudent() throws ClassRosterPersistenceException {
    view.displayCreateStudentBanner();
    boolean hasErrors = false;
    do{
       Student currentStudent = view.getNewStudentInfo(); 
    
    try{
    service.createStudent(currentStudent);
    view.displayCreateSuccessBanner();
    hasErrors = false;
    }catch (ClassRosterDuplicateIdException | ClassRosterDataValidationException e){
        hasErrors = true;
        view.displayErrorMessage(e.getMessage());
    }
    }while (hasErrors);
    
}
    
    private void listStudents() throws ClassRosterPersistenceException {
    view.displayDisplayAllBanner();
    List<Student> studentList = service.getAllStudents();
    view.displayStudentList(studentList);
}
    private void viewStudent() throws ClassRosterPersistenceException {
    view.displayDisplayStudentBanner();
    String studentId = view.getStudentIdChoice();
    Student student = service.getStudent(studentId);
    view.displayStudent(student);
}
    private void removeStudent() throws ClassRosterPersistenceException {
    view.displayRemoveStudentBanner();
    String studentId = view.getStudentIdChoice();
    service.removeStudent(studentId);
    view.displayRemoveSuccessBanner();
}
    private void unknownCommand() {
    view.displayUnknownCommandBanner();
}

    private void exitMessage() {
    view.displayExitBanner();
}
}