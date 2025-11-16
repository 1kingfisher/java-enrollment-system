package com.enrollment.model;

import java.io.Serializable;

/**
 * Abstract base class for all Courses (P5: Inheritance).
 * Implements Serializable for data persistence (P2).
 * Fulfills P4 (Encapsulation).
 */
public abstract class Course implements Serializable {
    // P4: Encapsulation
    private static final long serialVersionUID = 1L;
    private String courseId;
    private String title;
    private int maxSeats;
    private int enrolledCount;

    public Course(String courseId, String title, int maxSeats) {
        this.courseId = courseId;
        this.title = title;
        this.maxSeats = maxSeats; // P4: Encapsulation for course capacity
        this.enrolledCount = 0;
    }

    // Getters and Setters
    public String getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public int getMaxSeats() {
        return maxSeats;
    }

    public int getEnrolledCount() {
        return enrolledCount;
    }

    public boolean isFull() {
        return enrolledCount >= maxSeats;
    }

    public void incrementEnrolledCount() {
        if (!isFull()) {
            this.enrolledCount++;
        }
    }

    /**
     * Abstract method for P6 (Polymorphism).
     * Subclasses MUST override this to display their specific details.
     */
    public abstract String displayDetails();

    @Override
    public String toString() {
        return "Course [ID=" + courseId + ", Title=" + title + ", Enrolled=" + enrolledCount + "/" + maxSeats + "]";
    }
}