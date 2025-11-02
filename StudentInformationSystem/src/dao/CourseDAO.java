package dao;

import model.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDAO {
    Course getCourseById(int id) throws SQLException;
    void addCourse (Course course) throws SQLException;
    void updateCourse (Course course) throws SQLException;
    void deleteCourse (Course course) throws SQLException;
    List<Course> allCourse() throws SQLException;
}
