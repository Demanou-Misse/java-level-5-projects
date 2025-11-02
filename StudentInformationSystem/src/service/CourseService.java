package service;

import dao.impl.CourseDAOImpl;
import model.Course;
import util.ValidNumber;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class CourseService {
    Scanner input = new Scanner(System.in);
    ValidNumber validNumber = new ValidNumber();
    CourseDAOImpl courseDAOImpl = new CourseDAOImpl();

    // Add Course
    public void addCourse() {
        String course_name;
        int credits;

        while (true) {
            System.out.print("\nEnter Course Name: ");
            course_name = input.nextLine().trim();
            if (course_name.isEmpty()) {
                System.out.println("❌ Course Name cannot be empty!");
                continue;
            }
            break;
        }

        while (true) {
            System.out.print("Enter Credits: ");
            String userInput = input.nextLine().trim();
            if (userInput.isEmpty()) {
                System.out.println("❌ Credits cannot be empty!");
                continue;
            }

            if (!validNumber.isValid(userInput)) continue;
            credits = Integer.parseInt(userInput);
            break;
        }

        Course course = new Course(course_name, credits);
        try {
            courseDAOImpl.addCourse(course);
            System.out.println("✅ Course added successfully!");
            System.out.println("👉 Course ID: " + course.getCourse_id());
        } catch (SQLException e) {
            System.err.println("❌ Error: " + e.getMessage());
        }

    }

    // Update Course
    public void updateCourse() {
        int course_id;
        String newCourseName;
        int newCredits;
        Course course;

        while (true) {
            System.out.print("\nEnter Course ID to update: ");
            String userInput = input.nextLine().trim();
            if (userInput.isEmpty()) {
                System.out.println("❌ Course ID cannot be empty!");
                continue;
            }

            if (!validNumber.isValid(userInput)) continue;
            course_id = Integer.parseInt(userInput);

            try {
                course = courseDAOImpl.getCourseById(course_id);
                if (course == null) {
                    System.out.println("❌ Course not found!");
                    return;
                }
            } catch (SQLException e) {
                System.out.println("❌ " + e.getMessage());
                return;
            }
            break;
        }

        System.out.println("\n📗 Current Information:");
        System.out.println("    Course Name: " + course.getCourse_name());
        System.out.println("    Credits: " + course.getCredits());
        System.out.println("\n👉 Press ENTER to keep current value.");

        System.out.print("New Course Name: ");
        newCourseName = input.nextLine().trim();
        if (!newCourseName.isEmpty()) course.setCourse_name(newCourseName);

        while (true) {
            System.out.print("New Credits: ");
            String userInput = input.nextLine().trim();
            if (userInput.isEmpty()) break;

            if (!validNumber.isValid(userInput)) continue;
            newCredits = Integer.parseInt(userInput);
            course.setCredits(newCredits);
            break;
        }

        try {
            courseDAOImpl.updateCourse(course);
            System.out.println("✅ Student updated successfully!");
        } catch (SQLException e) {
            System.err.println("❌ " + e.getMessage());
        }

    }

    // Delete Course
    public void deleteCourse() {
        int course_id;
        Course course;

        while (true) {
            System.out.print("\nEnter Course ID to delete: ");
            String userInput = input.nextLine().trim();
            if (userInput.isEmpty()) {
                System.out.println("❌ Course ID cannot be empty!");
                continue;
            }

            if (!validNumber.isValid(userInput)) continue;
            course_id = Integer.parseInt(userInput);

            try {
                course = courseDAOImpl.getCourseById(course_id);
                if (course == null) {
                    System.out.println("❌ Course not found!");
                    return;
                }

                while (true) {
                    System.out.print("👉 Are you sure? (Y/N): ");
                    String choice = input.nextLine().trim().toUpperCase();
                    if (choice.isEmpty()) {
                        System.out.println("❌ Your choice cannot be empty!");
                        continue;
                    }

                    if (!choice.equals("Y") && !choice.equals("N")) {
                        System.out.println("❌ Enter 'Y' for yes or 'N' for no!");
                        continue;
                    }

                    switch (choice) {
                        case "N":
                            System.out.println("Get it! Course will not be deleted!");
                            break;
                        case "Y":
                            courseDAOImpl.deleteCourse(course);
                            System.out.println("✅ Course deleted successfully!");
                            break;
                    }
                    break;
                }
                break;
            } catch (SQLException e) {
                System.err.println("❌ " + e.getMessage());
                return;
            }
        }
    }

    // List All Course
    public void listAllCourse() {
        try {
            List<Course> courses = courseDAOImpl.allCourse();

            System.out.println("\n📘 COURSE LIST\n");
            System.out.println("*".repeat(60));
            for (Course course : courses) {
                 System.out.printf("    🔹 ID: %d    | Name: %s     | Credits: %d%n",
                         course.getCourse_id(),
                         course.getCourse_name(),
                         course.getCredits());
            }
            System.out.println("*".repeat(60));
        } catch (SQLException e) {
            System.err.println("❌ " + e.getMessage());
        }

    }

}
