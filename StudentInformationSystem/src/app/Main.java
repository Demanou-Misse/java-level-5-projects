package app;

import dao.impl.GradeDAOImpl;
import service.CourseService;
import service.EnrollmentService;
import service.StudentService;
import util.DatabaseInitializer;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int mainChoice;
        int studentChoice;
        int courseChoice;
        int enrollments_and_grades_choice;
        int reports_and_rankings_choice;
        Scanner input = new Scanner(System.in);
        DatabaseInitializer databaseInitializer = new DatabaseInitializer();
        GradeDAOImpl gradeDAOImpl = new GradeDAOImpl();
        StudentService studentService = new StudentService();
        CourseService courseService = new CourseService();
        EnrollmentService enrollmentService = new EnrollmentService();

        // Initialize database
        databaseInitializer.initializeDatabase();

        // Start
        System.out.println("\n" + "=".repeat(40));
        System.out.println("   🎓 STUDENT INFORMATION SYSTEM ");
        System.out.println("=".repeat(40));

        do {

            System.out.println("\n" + "-".repeat(40));
            System.out.println("    📗MAIN MENU");
            System.out.println("-".repeat(40));

            System.out.println("1️⃣ Manage Students");
            System.out.println("2️⃣ Manage Courses");
            System.out.println("3️⃣ Enrollments & Grades");
            System.out.println("4️⃣ Reports & Rankings");
            System.out.println("5️⃣ Exit Program");

            while (true) {
                System.out.print("\n👉 Enter your choice: ");
                String userInput = input.nextLine().trim();
                if (userInput.isEmpty()) {
                    System.out.println("❌ Your choice cannot be empty!");
                    continue;
                }
                if (isValidNumber(userInput, 5)) {
                    mainChoice = Integer.parseInt(userInput);
                    break;
                }
            }

            switch (mainChoice) {

                // STUDENT MANAGEMENT
                case 1:

                    do {
                        System.out.println("\n" + "*".repeat(30));
                        System.out.println("STUDENT MANAGEMENT");
                        System.out.println("*".repeat(30));

                        System.out.println("    1. Add New Student");
                        System.out.println("    2. Update Student Information");
                        System.out.println("    3. Delete Student");
                        System.out.println("    4. List All Students");
                        System.out.println("    5. Back to Main Menu");

                        while (true) {
                            System.out.print("👉 Enter your choice: ");
                            String userInput = input.nextLine().trim();
                            if (userInput.isEmpty()) {
                                System.out.println("❌ Your choice cannot be empty!");
                                continue;
                            }
                            if (isValidNumber(userInput, 5)) {
                                studentChoice = Integer.parseInt(userInput);
                                break;
                            }
                        }

                        switch (studentChoice) {
                            case 1:
                                studentService.addStudent();
                                break;
                            case 2:
                                studentService.updateStudent();
                                break;
                            case 3:
                                studentService.deleteStudent();
                                break;
                            case 4:
                                studentService.listAllStudents();
                                break;
                            case 5:
                                System.out.println("🔄 Returning to Main Menu...");
                                break;
                        }

                    } while (studentChoice != 5);
                    break;

                // COURSE MANAGEMENT
                case 2:

                    do {
                        System.out.println("\n" + "*".repeat(30));
                        System.out.println("COURSE MANAGEMENT");
                        System.out.println("*".repeat(30));

                        System.out.println("    1. Add Course");
                        System.out.println("    2. Update Course");
                        System.out.println("    3. Delete Course");
                        System.out.println("    4. List All Courses");
                        System.out.println("    5. Back to Main Menu");

                        while (true) {
                            System.out.print("👉 Enter your choice: ");
                            String userInput = input.nextLine().trim();
                            if (userInput.isEmpty()) {
                                System.out.println("❌ Your choice cannot be empty!");
                                continue;
                            }
                            if (isValidNumber(userInput, 5)) {
                                courseChoice = Integer.parseInt(userInput);
                                break;
                            }
                        }

                        switch (courseChoice) {
                            case 1:
                                courseService.addCourse();
                                break;
                            case 2:
                                courseService.updateCourse();
                                break;
                            case 3:
                                courseService.deleteCourse();
                                break;
                            case 4:
                                courseService.listAllCourse();
                                break;
                            case 5:
                                System.out.println("🔄 Returning to Main Menu...");
                                break;
                        }

                    } while (courseChoice != 5);
                    break;

                //  ENROLLMENTS & GRADES
                case 3:

                    do {
                        System.out.println("\n" + "*".repeat(30));
                        System.out.println("ENROLLMENTS & GRADES");
                        System.out.println("*".repeat(30));

                        System.out.println("    1. Enroll Student in Course");
                        System.out.println("    2. View Enrollments");
                        System.out.println("    3. Insert/Update Grade");
                        System.out.println("    4. Back to Main Menu");

                        while (true) {
                            System.out.print("👉 Enter your choice: ");
                            String userInput = input.nextLine().trim();
                            if (userInput.isEmpty()) {
                                System.out.println("❌ Your choice cannot be empty!");
                                continue;
                            }
                            if (isValidNumber(userInput, 4)) {
                                enrollments_and_grades_choice = Integer.parseInt(userInput);
                                break;
                            }
                        }

                        switch (enrollments_and_grades_choice) {
                            case 1:
                                enrollmentService.enrollStudent();
                                break;
                            case 2:
                                enrollmentService.viewEnrollment();
                                break;
                            case 3:
                                enrollmentService.insertGrade();
                                break;
                            case 4:
                                System.out.println("🔄 Returning to Main Menu...");
                                break;
                        }

                    } while (enrollments_and_grades_choice != 4);
                    break;

                // REPORTS & RANKINGS
                case 4:

                    do {
                        System.out.println("\n" + "*".repeat(30));
                        System.out.println("REPORTS & RANKINGS");
                        System.out.println("*".repeat(30));

                        System.out.println("    1. Compute GPA for All Students");
                        System.out.println("    2. Show Top Performers");
                        System.out.println("    3. Display Full Student Report");
                        System.out.println("    4. Back to Main Menu");

                        while (true) {
                            System.out.print("👉 Enter your choice: ");
                            String userInput = input.nextLine().trim();
                            if (userInput.isEmpty()) {
                                System.out.println("❌ Your choice cannot be empty!");
                                continue;
                            }
                            if (isValidNumber(userInput, 4)) {
                                reports_and_rankings_choice = Integer.parseInt(userInput);
                                break;
                            }
                        }

                        switch (reports_and_rankings_choice) {
                            case 1:
                                try {
                                    gradeDAOImpl.allStudentsGPA();
                                } catch (SQLException e) {
                                    System.err.println("❌ Error: " + e.getMessage());
                                }
                                break;
                            case 2:
                                try {
                                    gradeDAOImpl.topPerformers();
                                } catch (SQLException e) {
                                    System.err.println("❌ Error: " + e.getMessage());
                                }
                                break;
                            case 3:
                                try {
                                    gradeDAOImpl.fullStudentReport();
                                } catch (SQLException e) {
                                    System.err.println("❌ Error: " + e.getMessage());
                                }
                                break;
                            case 4:
                                System.out.println("🔄 Returning to Main Menu...");
                                break;
                        }

                    } while (reports_and_rankings_choice != 4);
                    break;

                case 5:
                    System.out.println("\nExiting program...");
                    System.out.println("Saving data to database...");
                    System.out.println("✅ All changes saved successfully.");

                    System.out.println("\n" + "=".repeat(40));
                    System.out.println("    PROGRAM TERMINATED SAFELY");
                    System.out.println("=".repeat(40));
                    break;
            }

        } while (mainChoice != 5);

        input.close();

    }

    public static boolean isValidNumber (String userInput, int max) {
        try {
            int num = Integer.parseInt(userInput);
            if (num >= 1 && num <= max) return true;
            System.out.println("❌ Invalid Input! Enter a number between (1-" + max + ").");
            return false;
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid Input");
            return false;
        }
    }
}