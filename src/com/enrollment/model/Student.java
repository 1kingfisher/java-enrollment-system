package com.enrollment.model;

import java.io.Serializable;

/**
 * Represents a Student.
 * Implements Serializable to allow for data persistence (P2).
 * Fulfills P4 (Encapsulation) by keeping fields private.
 */
public class Student implements Serializable {
    // P4: Encapsulation - all fields are private
    private static final long serialVersionUID = 1L;
    private String studentId;
    private String name;
    private double gpa;

    public Student(String studentId, String name, double gpa) {
        this.studentId = studentId;
        this.name = name;
        this.gpa = gpa;
    }

    // Getters and Setters for encapsulated fields
    public String getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public double getGpa() {
        return gpa;
    }

    @Override
    public String toString() {
        return "Student [ID=" + studentId + ", Name=" + name + ", GPA=" + gpa + "]";
    }
}