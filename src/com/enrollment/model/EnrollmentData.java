package com.enrollment.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * A wrapper class to hold all application data (Students, Courses, Enrollments).
 * This single object is what gets saved/loaded for persistence (P2).
 * Uses ArrayList to manage the "Array of Objects" (P7).
 */
public class EnrollmentData implements Serializable {
    private static final long serialVersionUID = 1L;

    // P7: Use an Array (List) of Objects
    private List<Student> students;
    private List<Course> courses;
    private List<Enrollment> enrollments;

    public EnrollmentData() {
        this.students = new ArrayList<>();
        this.courses = new ArrayList<>();
        this.enrollments = new ArrayList<>();
    }

    // Getters
    public List<Student> getStudents() {
        return students;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }
}