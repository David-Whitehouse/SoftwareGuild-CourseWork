/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.classroster.service;

import java.util.List;
import sg.classroster.dao.ClassRosterPersistenceException;
import sg.classroster.dto.Student;

/**
 *
 * @author ddubs
 */
public interface ClassRosterServiceLayer {
    
    void createStudent(Student student) throws
            ClassRosterDuplicateIdException,
            ClassRosterDataValidationException,
            ClassRosterPersistenceException;


    List<Student> getAllStudents() throws
        ClassRosterPersistenceException;
    
    Student getStudent(String StudentId) throws
            ClassRosterPersistenceException;
    
    Student removeStudent(String StudentId) throws
            ClassRosterPersistenceException;

}