package dao;

import model.Enrollment;
import model.Grade;

import java.sql.SQLException;

public interface EnrollmentDAO {
    Enrollment getEnrollmentById (int id) throws SQLException;

    Grade getGradeByEnrollment(int enrollment_id) throws SQLException;

    void enrollStudent (Enrollment enrollment) throws SQLException;
    void viewEnrollments() throws SQLException;
    void insertGrade (int enrollment_id, double grade_value) throws SQLException;
}
