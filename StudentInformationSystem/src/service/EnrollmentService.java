package service;

import dao.impl.CourseDAOImpl;
import dao.impl.EnrollmentDAOImpl;
import dao.impl.StudentDAOImpl;
import model.Course;
import model.Enrollment;
import model.Grade;
import model.Student;
import util.ValidNumber;

import java.sql.SQLException;
import java.util.Scanner;

public class EnrollmentService {
    Scanner input = new Scanner(System.in);
    ValidNumber validNumber = new ValidNumber();
    StudentDAOImpl studentDAOImpl = new StudentDAOImpl();
    CourseDAOImpl courseDAOImpl = new CourseDAOImpl();
    EnrollmentDAOImpl enrollmentDAOImpl = new EnrollmentDAOImpl();

    // ENROLLMENTS & GRADES
    public void enrollStudent() {
        int student_id;
        int course_id;

        while (true) {
            System.out.print("\nEnter Student ID: ");
            String userInput = input.nextLine().trim();
            if (userInput.isEmpty()) {
                System.out.println("❌ Student ID cannot be empty!");
                continue;
            }

            if (!validNumber.isValid(userInput)) continue;
            student_id = Integer.parseInt(userInput);
            break;
        }

        while (true) {
            System.out.print("Enter Course ID: ");
            String userInput = input.nextLine().trim();
            if (userInput.isEmpty()) {
                System.out.println("❌ Course ID cannot be empty!");
                continue;
            }

            if (!validNumber.isValid(userInput)) continue;
            course_id = Integer.parseInt(userInput);
            break;
        }

        try {
            Student student = studentDAOImpl.getStudentById(student_id);
            if (student == null) {
                System.out.println("❌ Student not found!");
                return;
            }
            Course course = courseDAOImpl.getCourseById(course_id);
            if (course == null) {
                System.out.println("❌ Course not found!");
                return;
            }

            Enrollment enrollment = new Enrollment();
            enrollment.setStudent_id(student.getStudent_id());
            enrollment.setCourse_id(course.getCourse_id());
            enrollmentDAOImpl.enrollStudent(enrollment);
            System.out.printf("✅ Student '%s %s' enrolled in the course '%s' successfully!%n",
                    student.getFirst_name(),
                    student.getLast_name(),
                    course.getCourse_name());
        } catch (SQLException e) {
            if ("23505".equals(e.getSQLState())) {
                System.out.println("❌ This student is already enrolled in this course!");
            } else {
                System.err.println("❌ " + e.getMessage());
            }
        }
    }

    // View Enrollments
    public void viewEnrollment() {

        try {
            enrollmentDAOImpl.viewEnrollments();
        } catch (SQLException e) {
            System.err.println("❌ " + e.getMessage());
        }
    }

    // Insert Grade
    public void insertGrade() {
        int enrollment_id;
        Double grade_value;
        Enrollment enrollment;

        while (true) {
            System.out.print("\nEnter Enrollment ID: ");
            String userInput = input.nextLine().trim();
            if (userInput.isEmpty()) {
                System.out.println("❌ Enrollment ID cannot be empty!");
                continue;
            }

            if (!validNumber.isValid(userInput)) continue;
            enrollment_id = Integer.parseInt(userInput);

            try {
                enrollment = enrollmentDAOImpl.getEnrollmentById(enrollment_id);
                if (enrollment == null) {
                    System.out.println("❌ Enrollment not found.");
                    return;
                }
            } catch (SQLException e) {
                System.err.println("❌ " + e.getMessage());
                return;
            }
            break;
        }

        while (true) {
            System.out.print("Enter Grade (0 - 20): ");
            String userInput = input.nextLine().trim();
            if (userInput.isEmpty()) {
                System.out.println("❌ Grade cannot be empty!");
                continue;
            }

            if (!validNumber.isValid(userInput)) continue;
            grade_value = Double.parseDouble(userInput);
            if (grade_value < 0 || grade_value > 20) {
                System.out.println("❌ Enter a number between (0 - 20)!");
                continue;
            }
            break;
        }

        try {
            enrollmentDAOImpl.insertGrade(enrollment_id, grade_value);
            System.out.println("✅ Grade recorded successfully!");
        } catch (SQLException e) {
            System.err.println("❌ " + e.getMessage());
        }
    }

}
