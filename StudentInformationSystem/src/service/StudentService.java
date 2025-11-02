package service;

import dao.impl.StudentDAOImpl;
import model.Student;
import util.ValidNumber;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentService {
    Scanner input = new Scanner(System.in);
    StudentDAOImpl studentDAOImpl = new StudentDAOImpl();
    ValidNumber validNumber = new ValidNumber();

    // Add Student
    public void addStudent() {
        String first_name;
        String last_name;
        String email;

        while (true) {
            System.out.print("\nEnter First Name: ");
            first_name = input.nextLine().trim();
            if (first_name.isEmpty()) {
                System.out.println("❌ Your first name cannot be empty.");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("Enter Last Name: ");
            last_name = input.nextLine().trim();
            if (last_name.isEmpty()) {
                System.out.println("❌ Your last name cannot be empty.");
                continue;
            }
            break;
        }
        while (true) {
            System.out.print("Enter Email: ");
            email = input.nextLine().trim();
            if (email.isEmpty()) {
                System.out.println("❌ Your email cannot be empty.");
                continue;
            }
            break;
        }

        Student student = new Student(first_name, last_name, email);
        try {
            studentDAOImpl.addStudent(student);
            System.out.println("\n✅ Student added successfully!");
            System.out.println("👉 Your ID: (" + student.getStudent_id() + ")  'Don't forget it!'");
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Update Student Information
    public void updateStudent() {
        int student_id;
        String newFirstName;
        String newLastName;
        String newEmail;
        Student student;

        while (true) {
            System.out.print("\nEnter Student ID to update: ");
            String userInput = input.nextLine().trim();
            if (userInput.isEmpty()) {
                System.out.println("❌ ID cannot be empty.");
                continue;
            }

            if (!validNumber.isValid(userInput)) continue;
            student_id = Integer.parseInt(userInput);

            try {
                student = studentDAOImpl.getStudentById(student_id);
                if (student == null) {
                    System.out.println("❌ Student not found!");
                    return;
                }
                break;
            } catch (SQLException e) {
                System.out.println("❌ Error: " + e.getMessage());
                return;
            }
        }

        System.out.println("\n📗 Current Information:");
        System.out.println("    First Name: " + student.getFirst_name());
        System.out.println("    Last Name: " + student.getLast_name());
        System.out.println("    Email: " + student.getEmail());
        System.out.println("\n👉 Press ENTER to Keep current value.");

        System.out.print("  New First Name: ");
        newFirstName = input.nextLine().trim();
        if (!newFirstName.isEmpty()) student.setFirst_name(newFirstName);

        System.out.print("  New Last Name: ");
        newLastName = input.nextLine().trim();
        if (!newLastName.isEmpty()) student.setLast_name(newLastName);

        System.out.print("  New Email: ");
        newEmail = input.nextLine().trim();
        if (!newEmail.isEmpty()) student.setEmail(newEmail);

        try {
            studentDAOImpl.updateStudent(student);
            System.out.println("✅ Student updated successfully!");
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

    // Delete Student
    public void deleteStudent() {
        int student_id;
        Student student = null;

        while (true) {
            System.out.print("\nEnter Student ID to delete: ");
            String userInput = input.nextLine().trim();
            if (userInput.isEmpty()) {
                System.out.println("❌ ID cannot be empty.");
                continue;
            }

            if (!validNumber.isValid(userInput)) continue;
            student_id = Integer.parseInt(userInput);
            break;
        }

        // Get Student
        try {
            student = studentDAOImpl.getStudentById(student_id);
            if (student == null) {
                System.out.println("❌ Student not found!");
                return;
            }
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }

        while (true) {
            System.out.print("Are you sure? (Y/N): ");
            String userInput = input.nextLine().trim().toUpperCase();
            if (!userInput.equals("Y") && !userInput.equals("N")) {
                System.out.println("❌ Enter 'Y' for yes or 'N' for no.");
                continue;
            }

            switch (userInput) {
                case "N":
                    System.out.println("Get it! Student will not be deleted.");
                    break;
                case "Y":
                    try {
                        studentDAOImpl.deleteStudent(student);
                        System.out.println("✅ Student deleted successfully!");
                        return;
                    } catch (SQLException e) {
                        System.err.println("❌ Error: " + e.getMessage());
                    }
            }
            return;
        }
    }

    public void listAllStudents() {
        List<Student> students = new ArrayList<>();

        try {
            students = studentDAOImpl.allStudents();

            System.out.println("\n🙍‍♂️ Student List\n");
            System.out.println("*".repeat(80));
            for (Student student : students) {
                System.out.printf("    🔹 ID: %d    | Name: %s %s    | Email: %s%n",
                        student.getStudent_id(),
                        student.getFirst_name(),
                        student.getLast_name(),
                        student.getEmail());
            }
            System.out.println("*".repeat(80));
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }
    }

}
