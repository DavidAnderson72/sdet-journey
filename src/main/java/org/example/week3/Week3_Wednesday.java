package org.example.week3;

import org.example.week2.ResidentialProperty;

public class Week3_Wednesday {
    public static void main(String[] args) {

        // Basic try/catch
        System.out.println("=== Basic Try/Catch ===");
        try {
            int result = 10 / 0;
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        // Multiple catch blocks
        System.out.println("\n=== Multiple Catch Blocks ===");
        String[] properties = {"5 Salford Street", "22 Oak Avenue", null};
        try {
            for (String property : properties) {
                System.out.println(property.toUpperCase());
            }
        } catch (NullPointerException e) {
            System.out.println("Null property found: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("General exception: " + e.getMessage());
        }

        // Finally block
        System.out.println("\n=== Finally Block ===");
        try {
            ResidentialProperty property = new ResidentialProperty(
                    "5 Salford Street", 1150.00, 3, true);
            System.out.println("Property created: " + property.getAddress());
            throw new RuntimeException("Simulated error");
        } catch (RuntimeException e) {
            System.out.println("Caught: " + e.getMessage());
        } finally {
            System.out.println("Finally always runs - cleanup code goes here");
        }

        // Custom exception
        System.out.println("\n=== Custom Exception ===");
        try {
            setMonthlyRent(-500.00);
        } catch (InvalidRentException e) {
            System.out.println("Invalid rent: " + e.getMessage());
        }

        try {
            setMonthlyRent(1150.00);
            System.out.println("Rent set successfully");
        } catch (InvalidRentException e) {
            System.out.println("Invalid rent: " + e.getMessage());
        }

        // Throws keyword
        System.out.println("\n=== Checked Exception ===");
        try {
            processProperty(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Caught: " + e.getMessage());
        }
    }

    // Method that throws custom exception
    static void setMonthlyRent(double rent) throws InvalidRentException {
        if (rent <= 0) {
            throw new InvalidRentException(
                    "Rent must be greater than zero. Provided: £" + rent);
        }
        System.out.printf("Rent set to: £%.2f%n", rent);
    }

    // Method throws built-in exception
    static void processProperty(String address) {
        if (address == null || address.isEmpty()) {
            throw new IllegalArgumentException(
                    "Property address cannot be null or empty");
        }
        System.out.println("Processing: " + address);
    }
}
