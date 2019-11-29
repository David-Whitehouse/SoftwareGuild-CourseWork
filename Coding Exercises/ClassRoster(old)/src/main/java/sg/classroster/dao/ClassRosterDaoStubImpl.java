/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.classroster.dao;

import java.util.ArrayList;
import java.util.List;
import sg.classroster.dto.Student;

/**
 *
 * @author ddubs
 */
public class ClassRosterDaoStubImpl implements ClassRosterDao {
    
    Student onlyStudent;
    List<Student> studentList = new ArrayList<>();
    
    public ClassRosterDaoStubImpl(){
        onlyStudent = new Student("0001");
        onlyStudent.setFirstName("John");
        onlyStudent.setLastName("Doe");
        onlyStudent.setCohort("Java-Jan-2015");
        
        studentList.add(onlyStudent);
    }

    @Override
    public Student addStudent(String studentId, Student student) {
        if(studentId.equals(onlyStudent.getStudentId())){
            return onlyStudent;
        }else{
            return null;
        }
    }

    @Override
    public List<Student> getAllStudents() {
        return studentList;
    }

    @Override
    public Student getStudent(String studentId) {
            if(studentId.equals(onlyStudent.getStudentId())){
            return onlyStudent;
        }else{
            return null;
        }    
    }

    @Override
    public Student removeStudent(String studentId) {
                if(studentId.equals(onlyStudent.getStudentId())){
            return onlyStudent;
        }else{
            return null;
        }
    }
    
}
