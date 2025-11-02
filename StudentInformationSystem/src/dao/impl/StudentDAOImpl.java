package dao.impl;

import dao.StudentDAO;
import model.Student;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    DatabaseConnection databaseConnection = new DatabaseConnection();

    @Override
    public Student getStudentById(int id) throws SQLException {
        String sql = "SELECT * FROM students WHERE student_id = ?";

        try (Connection conn = databaseConnection.getConnexion();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getInt("student_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getTimestamp("registration_date").toLocalDateTime()
                );
            }
        }
        return null;
    }

    @Override
    public void addStudent(Student student) throws SQLException{
        String sql = "INSERT INTO students (first_name, last_name, email) VALUES (?, ?, ?) RETURNING student_id";

        try (Connection conn = databaseConnection.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getFirst_name());
            stmt.setString(2, student.getLast_name());
            stmt.setString(3, student.getEmail());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) student.setStudent_id(rs.getInt("student_id"));
            }
        }
    }

    @Override
    public void updateStudent (Student student) throws SQLException {
        String sql = "UPDATE students SET first_name=?, last_name=?, email=? WHERE student_id=?";

        try (Connection conn = databaseConnection.getConnexion();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, student.getFirst_name());
            stmt.setString(2, student.getLast_name());
            stmt.setString(3, student.getEmail());
            stmt.setInt(4, student.getStudent_id());
            stmt.executeUpdate();
        }
    }

    @Override
    public void deleteStudent (Student student) throws SQLException {
        String sql = "DELETE FROM students WHERE student_id=?";

        try (Connection conn = databaseConnection.getConnexion();
        PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, student.getStudent_id());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Student> allStudents() throws SQLException {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM students";

        try (Connection conn = databaseConnection.getConnexion();
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("student_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"),
                        rs.getObject(5, LocalDateTime.class)
                );
                students.add(student);
            }
        }
        return students;
    }


}
