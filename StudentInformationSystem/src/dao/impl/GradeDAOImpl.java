package dao.impl;

import dao.GradeDAO;
import model.Course;
import model.Student;
import util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GradeDAOImpl implements GradeDAO {
    DatabaseConnection databaseConnection = new DatabaseConnection();

    @Override
    public void allStudentsGPA () throws SQLException {
        String sql = "SELECT * FROM student_gpa";
        String averageGPA = "SELECT ROUND(AVG(gpa), 2) AS average FROM student_gpa";

        try (Connection conn = databaseConnection.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             PreparedStatement stmt1 = conn.prepareStatement(averageGPA);
             ResultSet rs = stmt.executeQuery();
             ResultSet rs1 = stmt1.executeQuery()) {

            System.out.println("\n🎯 GPA RESULTS\n");
            System.out.println("*".repeat(60));
            int count = 1;
            while (rs.next()) {
                System.out.printf("   🔹 %d. %s %s (ID: %d)      ->  GPA: %f%n",
                        count,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getInt("student_id"),
                        rs.getDouble("gpa"));
                count++;
            }
            System.out.println("*".repeat(60));

            if (rs1.next()) System.out.println("\n📗Average GPA (All Students): " + rs1.getDouble("average"));

        }
    }

    @Override
    public void topPerformers() throws SQLException {
        String sql = "SELECT * FROM student_gpa" +
                " ORDER BY gpa DESC LIMIT 3";

        try (Connection conn = databaseConnection.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\n🏆 TOP STUDENTS\n");
            System.out.println("*".repeat(60));
            int count = 1;
            while (rs.next()) {
                System.out.printf("   🔹 %d. %s %s (GPA: %f)%n",
                        count,
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getDouble("gpa"));
                count++;
            }
            System.out.println("*".repeat(60));
        }
    }

    @Override
    public void fullStudentReport() throws SQLException {
        StudentDAOImpl studentDAOImpl = new StudentDAOImpl();
        CourseDAOImpl courseDAOImpl = new CourseDAOImpl();
        Map<Integer, List<String>> map = new HashMap<>();
        String sql ="SELECT * FROM reports";
        String sql1 = "SELECT gpa From student_gpa WHERE student_id = ?";

        try (Connection conn = databaseConnection.getConnexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int student_id = rs.getInt("student_id");
                int course_id = rs.getInt("course_id");
                double grade_value = rs.getDouble("grade_value");
                Course course = courseDAOImpl.getCourseById(course_id);
                String string = "   🔹 " + course.getCourse_name() + " (Grade: " + grade_value + ")";

                map.putIfAbsent(student_id, new ArrayList<>());
                map.get(student_id).add(string);
            }

            System.out.println("\n📚 FULL STUDENT REPORTS\n");
            for (Map.Entry<Integer, List<String>> entry : map.entrySet()) {
                int student_id = entry.getKey();
                List<String> stringList = entry.getValue();
                Student student = studentDAOImpl.getStudentById(student_id);

                System.out.println("🙍‍♂️ STUDENT: " + student.getFirst_name() + " " + student.getLast_name());
                for (String string : stringList) {
                    System.out.println(string);
                }

                try (Connection connection = databaseConnection.getConnexion();
                     PreparedStatement stmt1 = connection.prepareStatement(sql1)) {
                    stmt1.setInt(1, student_id);
                    try (ResultSet rs1 = stmt1.executeQuery()) {
                        if (rs1.next()) {
                            System.out.println("🎯 AVERAGE (" + rs1.getDouble("gpa") + ")");
                        }
                    }
                }
                System.out.println("*".repeat(60));
            }
        }
    }

}
