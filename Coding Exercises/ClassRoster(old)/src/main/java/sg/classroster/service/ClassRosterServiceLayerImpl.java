/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.classroster.service;

import java.util.List;
import sg.classroster.dao.ClassRosterAuditDao;
import sg.classroster.dao.ClassRosterDao;
import sg.classroster.dao.ClassRosterPersistenceException;
import sg.classroster.dto.Student;

/**
 *
 * @author ddubs
 */
public class ClassRosterServiceLayerImpl implements ClassRosterServiceLayer {
    ClassRosterDao dao;
    
    private ClassRosterAuditDao auditDao;
    
    public ClassRosterServiceLayerImpl(ClassRosterDao dao, ClassRosterAuditDao auditDao){
        this.dao = dao;
        this.auditDao = auditDao;
    }
    
    
    @Override
    public void createStudent(Student student) throws ClassRosterDuplicateIdException, ClassRosterDataValidationException, ClassRosterPersistenceException {
        
        if (dao.getStudent(student.getStudentId()) != null){
            throw new ClassRosterDuplicateIdException(
                    "ERROR: Could not create student. Student Id " + student.getStudentId() + " already exists"); 
        }
        
        
    // Now validate all the fields on the given Student object.  
    // This method will throw an
    // exception if any of the validation rules are violated.
        validateStudentData(student);
        
    // We passed all our business rules checks so go ahead 
    // and persist the Student object
        dao.addStudent(student.getStudentId(), student);
        
    //The student was successfully created, now write to the audit log
    auditDao.writeAuditEntry("Student " + student.getStudentId() + " CREATED");
    }

    @Override
    public List<Student> getAllStudents() throws ClassRosterPersistenceException {
        return dao.getAllStudents();
    }

    @Override
    public Student getStudent(String studentId) throws ClassRosterPersistenceException {
       return dao.getStudent(studentId);
    }

    @Override
    public Student removeStudent(String studentId) throws ClassRosterPersistenceException {
        Student removedStudent = dao.removeStudent(studentId);
        
// Write to audit log
    auditDao.writeAuditEntry("Student " + studentId + " REMOVED.");
    return removedStudent;
}
    
    private void validateStudentData(Student student) throws
            ClassRosterDataValidationException{
        
        if (student.getFirstName() == null
            || student.getFirstName().trim().length() == 0
            || student.getLastName() == null
            || student.getLastName().trim().length() == 0
            || student.getCohort() == null
            || student.getCohort().trim().length() == 0){
            
            throw new ClassRosterDataValidationException(
                "ERROR: All Fields [First Name, Last Name, Cohort] are required");
        }    
    }
}
