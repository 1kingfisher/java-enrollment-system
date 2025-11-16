package com.enrollment.app;

/**
 * P3: Error Handling (Exceptions)
 * A custom exception to handle business logic errors, like attempting
 * to enroll in a full course or not meeting prerequisites.
 */
public class EnrollmentException extends Exception {
    public EnrollmentException(String message) {
        super(message);
    }
}