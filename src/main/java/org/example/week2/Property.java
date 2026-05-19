package org.example.week2;

public class Property {

    // Fields
    private String address;
    private double monthlyRent;
    private int bedrooms;

    // Constructor
    public Property(String address, double monthlyRent, int bedrooms) {
        this.address = address;
        this.monthlyRent = monthlyRent;
        this.bedrooms = bedrooms;
    }

    // Getters
    public String getAddress() { return address; }
    public double getMonthlyRent() { return monthlyRent; }
    public int getBedrooms() { return bedrooms; }

    // Print details
    public void printDetails() {
        System.out.println("=== Property Details ===");
        System.out.printf("Address:  %s%n", address);
        System.out.printf("Rent:     £%.2f%n", monthlyRent);
        System.out.printf("Bedrooms: %d%n", bedrooms);
    }
}