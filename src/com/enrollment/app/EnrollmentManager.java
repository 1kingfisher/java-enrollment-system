package com.enrollment.app;

import com.enrollment.model.*;
import java.util.List;
import java.util.Scanner;

/**
 * P10: Structure (Packages)
 * This class is in the com.enrollment.app package.
 *
 * Handles the main application logic, menu, and user interaction.
 * Fulfills P1 (Menu/Loops).
 */
public class EnrollmentManager {
    private EnrollmentData data;
    private Scanner scanner;

    public EnrollmentManager() {
        this.data = DataManager.loadData(); // P2: Load data on start
        this.scanner = new Scanner(System.in);
    }

    /**
     * P1: System Interface (Menu/Loops)
     * The main application loop.
     */
    public void run() {
        boolean running = true;
        while (running) {
            printMenu();
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        addCourse();
                        break;
                    case 3:
                        enrollStudentInCourse();
                        break;
                    case 4:
                        listAllStudents();
                        break;
                    case 5:
                        listAllCourses();
                        break;
                    case 6:
                        viewStudentEnrollments();
                        break;
                    case 7:
                        DataManager.saveData(data); // P2: Save data
                        break;
                    case 8:
                        running = false;
                        System.out.println("Saving data and exiting...");
                        DataManager.saveData(data); // P2: Save on exit
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (Exception e) {
                // P3: Catch other unexpected errors
                System.err.println("An unexpected error occurred: " + e.getMessage());
            }
        }
        scanner.close();
        System.out.println("Goodbye!");
    }

    private void printMenu() {
        System.out.println("\n--- Course Enrollment Management System ---");
        System.out.println("1. Add New Student");
        System.out.println("2. Add New Course");
        System.out.println("3. Enroll Student in Course");
        System.out.println("4. List All Students");
        System.out.println("5. List All Courses (with details)");
        System.out.println("6. View Enrollments for a Student");
        System.out.println("7. Save Data Manually");
        System.out.println("8. Save and Exit");
        System.out.print("Enter your choice: ");
    }

    private void addStudent() {
        try {
            System.out.print("Enter Student ID (e.g., S101): ");
            String id = scanner.nextLine();
            System.out.print("Enter Student Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Student GPA: ");
            double gpa = Double.parseDouble(scanner.nextLine());

            // Check for duplicate ID
            if (findStudentById(id) != null) {
                System.out.println("Error: Student with this ID already exists.");
                return;
            }

            Student student = new Student(id, name, gpa);
            data.getStudents().add(student); // P7: Adding to Array (List) of Objects
            System.out.println("Student added successfully: " + student);
        } catch (NumberFormatException e) {
            System.out.println("Invalid GPA. Please enter a number (e.g., 3.5).");
        }
    }

    private void addCourse() {
        try {
            System.out.print("Enter Course ID (e.g., C101): ");
            String id = scanner.nextLine();
            System.out.print("Enter Course Title: ");
            String title = scanner.nextLine();
            System.out.print("Enter Max Seats: ");
            int maxSeats = Integer.parseInt(scanner.nextLine());

            if (findCourseById(id) != null) {
                System.out.println("Error: Course with this ID already exists.");
                return;
            }

            System.out.print("Is this a (1) Lecture or (2) Lab course? ");
            int type = Integer.parseInt(scanner.nextLine());

            Course course;
            if (type == 1) {
                System.out.print("Enter Room Number: ");
                String room = scanner.nextLine();
                System.out.print("Enter Lecturer Name: ");
                String lecturer = scanner.nextLine();
                course = new LectureCourse(id, title, maxSeats, room, lecturer); // P5
            } else if (type == 2) {
                System.out.print("Enter Lab Location: ");
                String location = scanner.nextLine();
                System.out.print("Does this lab require safety protocol (true/false)? ");
                boolean safety = Boolean.parseBoolean(scanner.nextLine());
                course = new LabCourse(id, title, maxSeats, location, safety); // P5
            } else {
                System.out.println("Invalid course type.");
                return;
            }

            data.getCourses().add(course); // P7: Adding to Array (List) of Objects
            System.out.println("Course added successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number for seats or type. Please try again.");
        }
    }

    private void enrollStudentInCourse() {
        try {
            System.out.print("Enter Student ID to enroll: ");
            String studentId = scanner.nextLine();
            System.out.print("Enter Course ID to enroll in: ");
            String courseId = scanner.nextLine();

            Student student = findStudentById(studentId);
            Course course = findCourseById(courseId);

            if (student == null) {
                System.out.println("Error: Student not found.");
                return;
            }
            if (course == null) {
                System.out.println("Error: Course not found.");
                return;
            }

            // P8: Using Student and Course objects as arguments for enrollment
            enroll(student, course);
            System.out.println("Enrollment successful!");

        } catch (EnrollmentException e) {
            // P3: Catching business logic exceptions
            System.out.println("Enrollment failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    /**
     * P8: Objects as Arguments
     * This method takes Student and Course objects to process enrollment.
     */
    private void enroll(Student student, Course course) throws EnrollmentException {
        // P9: Logic Control (Conditionals)
        
        // 1. Check for prerequisites (using GPA as a simple example)
        final double MIN_GPA = 2.0;
        if (student.getGpa() < MIN_GPA) {
            throw new EnrollmentException("Student GPA (" + student.getGpa()
                    + ") is below the minimum required GPA of " + MIN_GPA);
        }

        // 2. Check course capacity
        if (course.isFull()) {
            throw new EnrollmentException("Course is full. (Capacity: " + course.getMaxSeats() + ")");
        }

        // 3. Check for duplicate enrollment
        for (Enrollment e : data.getEnrollments()) {
            if (e.getStudentId().equals(student.getStudentId()) && e.getCourseId().equals(course.getCourseId())) {
                throw new EnrollmentException("Student is already enrolled in this course.");
            }
        }

        // All checks passed. Create enrollment.
        Enrollment enrollment = new Enrollment(student.getStudentId(), course.getCourseId());
        data.getEnrollments().add(enrollment);
        course.incrementEnrolledCount(); // Update the course's enrolled count
    }

    private void listAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = data.getStudents();
        if (students.isEmpty()) {
            System.out.println("No students registered.");
        } else {
            for (Student s : students) {
                System.out.println(s);
            }
        }
    }

    private void listAllCourses() {
        System.out.println("\n--- All Courses ---");
        List<Course> courses = data.getCourses();
        if (courses.isEmpty()) {
            System.out.println("No courses available.");
        } else {
            // P6: Polymorphism in action.
            // Calling c.displayDetails() will call the
            // overridden method for either LectureCourse or LabCourse.
            for (Course c : courses) {
                System.out.println(c.displayDetails());
                System.out.println("--------------------");
            }
        }
    }

    private void viewStudentEnrollments() {
        System.out.print("Enter Student ID to view enrollments: ");
        String studentId = scanner.nextLine();
        
        Student student = findStudentById(studentId);
        if (student == null) {
            System.out.println("Error: Student not found.");
            return;
        }

        System.out.println("\n--- Enrollments for " + student.getName() + " (ID: " + student.getStudentId() + ") ---");
        boolean found = false;
        for (Enrollment e : data.getEnrollments()) {
            if (e.getStudentId().equals(studentId)) {
                Course c = findCourseById(e.getCourseId());
                System.out.println(c != null ? c.toString() : "Enrolled in unknown course ID: " + e.getCourseId());
                found = true;
            }
        }

        if (!found) {
            System.out.println(student.getName() + " is not enrolled in any courses.");
        }
    }

    // Helper methods
    private Student findStudentById(String id) {
        for (Student s : data.getStudents()) {
            if (s.getStudentId().equalsIgnoreCase(id)) {
                return s;
            }
        }
        return null;
    }

    private Course findCourseById(String id) {
        for (Course c : data.getCourses()) {
            if (c.getCourseId().equalsIgnoreCase(id)) {
                return c;
            }
        }
        return null;
    }
}