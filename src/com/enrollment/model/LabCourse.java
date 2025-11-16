package com.enrollment.model;

/**
 * Represents a Lab Course, inheriting from Course (P5: Inheritance).
 */
public class LabCourse extends Course {
    private static final long serialVersionUID = 1L;
    private String labLocation;
    private boolean safetyProtocolRequired;

    public LabCourse(String courseId, String title, int maxSeats, String labLocation, boolean safetyProtocolRequired) {
        super(courseId, title, maxSeats);
        this.labLocation = labLocation;
        this.safetyProtocolRequired = safetyProtocolRequired;
    }

    public String getLabLocation() {
        return labLocation;
    }

    public boolean isSafetyProtocolRequired() {
        return safetyProtocolRequired;
    }

    /**
     * P6: Polymorphism
     * Overrides the abstract method to display extra lab session info.
     */
    @Override
    public String displayDetails() {
        return super.toString() + "\n  Type: Lab" + "\n  Location: " + labLocation + "\n  Safety Protocol Required: "
                + safetyProtocolRequired;
    }
}