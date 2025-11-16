package com.enrollment.app;

import com.enrollment.model.EnrollmentData;
import java.io.*;

/**
 * P2: Data Persistence (File I/O)
 * Handles saving and loading all application data to/from a file.
 * Uses Object Serialization to store the EnrollmentData object.
 */
public class DataManager {
    private static final String DATA_FILE = "enrollment.dat";

    /**
     * Saves the application data to a file.
     */
    public static void saveData(EnrollmentData data) {
        // P3: Error Handling (Exceptions) for File I/O
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))) {
            oos.writeObject(data);
            System.out.println("Data saved successfully to " + DATA_FILE);
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    /**
     * Loads the application data from a file.
     * If no file exists, returns new (empty) data.
     */
    public static EnrollmentData loadData() {
        EnrollmentData data = null;
        File file = new File(DATA_FILE);

        if (file.exists()) {
            // P3: Error Handling (Exceptions) for File I/O
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))) {
                data = (EnrollmentData) ois.readObject();
                System.out.println("Data loaded successfully from " + DATA_FILE);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading data: " + e.getMessage());
                System.err.println("Creating new data store.");
                data = new EnrollmentData();
            }
        } else {
            System.out.println("No existing data file found. Starting with a new session.");
            data = new EnrollmentData();
        }
        return data;
    }
}