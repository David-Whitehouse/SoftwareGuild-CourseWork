/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.classroster.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sg.classroster.dto.Student;

/**
 *
 * @author ddubs
 */
public class ClassRosterDaoFileImpl implements ClassRosterDao {

   @Override
    public Student addStudent(String studentId, Student student) {
        Student newStudent = students.put(studentId, student);
        return newStudent;
    }

    @Override
    public List<Student> getAllStudents() {
        return new ArrayList<Student>(students.values());
    }

@Override
public Student getStudent(String studentId) {
    return students.get(studentId);
}

@Override
public Student removeStudent(String studentId) {
    Student removedStudent = students.remove(studentId);
    return removedStudent;
}
    
    private Map <String, Student> students = new HashMap<>();    
}
