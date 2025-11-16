package com.enrollment.model;

/**
 * Represents a Lecture Course, inheriting from Course (P5: Inheritance).
 */
public class LectureCourse extends Course {
    private static final long serialVersionUID = 1L;
    private String roomNumber;
    private String lecturerName;

    public LectureCourse(String courseId, String title, int maxSeats, String roomNumber, String lecturerName) {
        super(courseId, title, maxSeats);
        this.roomNumber = roomNumber;
        this.lecturerName = lecturerName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getLecturerName() {
        return lecturerName;
    }

    /**
     * P6: Polymorphism
     * Overrides the abstract method to display details specific to a LectureCourse.
     */
    @Override
    public String displayDetails() {
        // Calls super.toString() for basic info, then adds its own.
        return super.toString() + "\n  Type: Lecture" + "\n  Lecturer: " + lecturerName + "\n  Room: " + roomNumber;
    }
}