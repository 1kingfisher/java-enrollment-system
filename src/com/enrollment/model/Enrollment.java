package com.enrollment.model;

import java.io.Serializable;

/**
 * Represents the link between a Student and a Course.
 * Implements Serializable for data persistence (P2).
 * Used for P8 (Objects as Arguments) in the EnrollmentManager.
 */
public class Enrollment implements Serializable {
    private static final long serialVersionUID = 1L;
    private String studentId;
    private String courseId;
    private java.util.Date enrollmentDate;

    public Enrollment(String studentId, String courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = new java.util.Date();
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    @Override
    public String toString() {
        return "Enrollment [Student ID=" + studentId + ", Course ID=" + courseId + ", Date=" + enrollmentDate + "]";
    }
}