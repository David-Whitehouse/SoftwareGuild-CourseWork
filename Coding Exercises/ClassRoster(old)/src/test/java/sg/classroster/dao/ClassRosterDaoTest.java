/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.classroster.dao;

import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import sg.classroster.dto.Student;

/**
 *
 * @author ddubs
 */
public class ClassRosterDaoTest {
    
    private ClassRosterDao dao = new ClassRosterDaoFileImpl();
    
    public ClassRosterDaoTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() throws Exception{
        List<Student> studentList = dao.getAllStudents();
        for(Student student : studentList){
        dao.removeStudent(student.getStudentId());
        }
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of addStudent method, of class ClassRosterDao.
     */
    @Test
    public void testAddGetStudent() throws Exception {
        Student student = new Student("0001");
        student.setFirstName("Joe");
        student.setLastName("Smith");
        student.setCohort("Java-May-2000");
        
        dao.addStudent(student.getStudentId(), student);
        
        Student fromDao = dao.getStudent(student.getStudentId());
        
        assertEquals(student, fromDao);
    }

    /**
     * Test of getAllStudents method, of class ClassRosterDao.
     */
    @Test
    public void testGetAllStudents()throws Exception {
        Student student1 = new Student("0001");
        student1.setCohort("Joe");
        student1.setLastName("Smith");
        student1.setCohort("Java-May-2000");
        
        dao.addStudent(student1.getStudentId(), student1);
        
        Student student2 = new Student("0002");
        student2.setCohort("John");
        student2.setLastName("Doe");
        student2.setCohort(".NET-May-2000");
        
        dao.addStudent(student2.getStudentId(), student2);
        
        assertEquals(2, dao.getAllStudents().size());
    }

    /**
     * Test of removeStudent method, of class ClassRosterDao.
     */
    @Test
    public void testRemoveStudent() {
        Student student1 = new Student("0001");
        student1.setCohort("Joe");
        student1.setLastName("Smith");
        student1.setCohort("Java-May-2000");
        
        dao.addStudent(student1.getStudentId(), student1);
        
        Student student2 = new Student("0002");
        student2.setCohort("John");
        student2.setLastName("Doe");
        student2.setCohort(".NET-May-2000");
        
        dao.addStudent(student2.getStudentId(), student2);
        
        dao.removeStudent(student1.getStudentId());
        assertEquals(1, dao.getAllStudents().size());
        assertNull(dao.getStudent(student1.getStudentId()));
        
        dao.removeStudent(student2.getStudentId());
        assertEquals(0,dao.getAllStudents().size());
        assertNull(dao.getStudent(student2.getStudentId()));
       
    }

}
  
