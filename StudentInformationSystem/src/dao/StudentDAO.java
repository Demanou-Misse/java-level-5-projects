package dao;

import model.Student;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO {
    void addStudent(Student student) throws SQLException;
    void updateStudent (Student student) throws SQLException;
    void deleteStudent (Student student) throws SQLException;
    List<Student> allStudents() throws SQLException;
    Student getStudentById(int id) throws SQLException;
}
