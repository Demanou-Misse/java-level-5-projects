package dao.impl;

import dao.CourseDAO;
import model.Course;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDAOImpl implements CourseDAO {
    DatabaseConnection databaseConnection = new DatabaseConnection();

    @Override
    public Course getCourseById(int id) throws SQLException {
        String sql = "SELECT * FROM courses WHERE course_id=?";

        try (Connection conn = databaseConnection.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Course(
                            rs.getInt("course_id"),
                            rs.getString("course_name"),
                            rs.getInt("credits")
                    );
                }
            }
        }
        return null;
    }

    @Override
    public void addCourse (Course course) throws SQLException {
        String sql = "INSERT INTO courses (course_name, credits) VALUES (?, ?) RETURNING course_id";

        try (Connection conn = databaseConnection.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, course.getCourse_name());
            stmt.setDouble(2, course.getCredits());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    course.setCourse_id(rs.getInt("course_id"));
                }
            }
        }
    }

    @Override
    public void updateCourse (Course course) throws SQLException {
        String sql = "UPDATE courses SET course_name=?, credits=? WHERE course_id=?";

        try (Connection conn = databaseConnection.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, course.getCourse_name());
            stmt.setInt(2, course.getCredits());
            stmt.setInt(3, course.getCourse_id());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteCourse (Course course) throws SQLException {
        String sql = "DELETE FROM courses WHERE course_id=?";

        try (Connection conn = databaseConnection.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, course.getCourse_id());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Course> allCourse() throws SQLException {
        List<Course> courses = new ArrayList<>();
        String sql = "SELECT * FROM courses";

        try (Connection conn = databaseConnection.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Course course = new Course(
                        rs.getInt("course_id"),
                        rs.getString("course_name"),
                        rs.getInt("credits")
                );
                courses.add(course);
            }
        }
        return courses;
    }

}
