package dao.impl;

import dao.EnrollmentDAO;
import model.Enrollment;
import model.Grade;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnrollmentDAOImpl implements EnrollmentDAO {
    DatabaseConnection databaseConnection = new DatabaseConnection();

    @Override
    public Enrollment getEnrollmentById(int id) throws SQLException{
        String sql = "SELECT * FROM enrollments WHERE enrollment_id = ?";
        Enrollment enrollment = null;

        try (Connection conn = databaseConnection.getConnexion();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    enrollment = new Enrollment(
                            rs.getInt("enrollment_id"),
                            rs.getInt("student_id"),
                            rs.getInt("course_id")
                    );
                }
            }
            return enrollment;
        }
    }

    @Override
    public Grade getGradeByEnrollment(int enrollment_id) throws SQLException{
        String sql = "SELECT * FROM grades WHERE enrollment_id = ?";
        Grade grade = null;

        try (Connection conn = databaseConnection.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, enrollment_id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    grade = new Grade(
                            rs.getInt("grade_id"),
                            rs.getInt("enrollment_id"),
                            rs.getDouble("grade_value") == 0 ? 0.0 : rs.getDouble("grade_value")
                    );
                }
            }
        }
        return grade;
    }

    @Override
    public void enrollStudent (Enrollment enrollment) throws SQLException {
        String sql = "INSERT INTO enrollments (student_id, course_id) VALUES (?, ?) RETURNING enrollment_id";

        try (Connection conn = databaseConnection.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, enrollment.getStudent_id());
            stmt.setInt(2, enrollment.getCourse_id());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) enrollment.setEnrollment_id(rs.getInt("enrollment_id"));
            }
        }
    }

    @Override
    public void viewEnrollments() throws SQLException {
        String sql = """
            SELECT e.enrollment_id, s.first_name, s.last_name, c.course_name, g.grade_value
                     FROM enrollments e
                     JOIN students s ON e.student_id = s.student_id
                     JOIN courses c ON e.course_id = c.course_id
                     LEFT JOIN LATERAL (
                         SELECT grade_value
                         FROM grades g
                         WHERE g.enrollment_id = e.enrollment_id
                         ORDER BY grade_date DESC
                         LIMIT 1
                     ) g ON TRUE
        """;

        try (Connection conn = databaseConnection.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n" + "-".repeat(30));
            System.out.println("📚 ENROLLMENT LIST");
            System.out.println("-".repeat(30) + "\n");

            while (rs.next()) {
                double gradeValue = rs.getDouble("grade_value");
                if (rs.wasNull()) gradeValue = 0.0; // Si pas encore de note

                System.out.printf("🔹 Enrollment ID: %d | Student: %s %s | Course: %s | Grade: %.2f%n",
                        rs.getInt("enrollment_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("course_name"),
                        gradeValue);
            }
            System.out.println("*".repeat(100));
        }

    }

    @Override
    public void insertGrade(int enrollment_id, double grade_value) throws SQLException {
        String sql = "INSERT INTO grades (enrollment_id, grade_value) VALUES (?, ?) RETURNING grade_id";

        try (Connection conn = databaseConnection.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, enrollment_id);
            stmt.setDouble(2, grade_value);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("✅ Grade inserted with ID: " + rs.getInt("grade_id"));
                }
            }
        }
    }


}
