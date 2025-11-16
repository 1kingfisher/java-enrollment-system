package com.enrollment.app;

/**
 * P10: Structure (Packages)
 * This is the main entry point of the application, located in the
 * com.enrollment.app package.
 */
public class EnrollmentSystem {

    public static void main(String[] args) {
        // Create the main manager object and run the application
        EnrollmentManager manager = new EnrollmentManager();
        manager.run(); // This starts the P1 menu loop
    }
}