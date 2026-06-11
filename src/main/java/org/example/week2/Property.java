package org.example.week2;

public class Property {

    // Fields
    private String address;
    private double monthlyRent;
    private int bedrooms;

    // Default constructor
    public Property() {
        this.address = "Unknown";
        this.monthlyRent = 0.00;
        this.bedrooms = 0;
    }

    // Constructor (Parameterised constructor)
    public Property(String address, double monthlyRent, int bedrooms) {
        this.address = address;
        this.monthlyRent = monthlyRent;
        this.bedrooms = bedrooms;
    }

    // Getters
    public String getAddress() { return address; }
    public double getMonthlyRent() { return monthlyRent; }
    public int getBedrooms() { return bedrooms; }
    public double getAnnualRent() {return monthlyRent * 12; }

    // Print details
    public void printDetails() {
        System.out.println("=== Property Details ===");
        System.out.printf("Address:  %s%n", address);
        System.out.printf("Rent:     £%.2f%n", monthlyRent);
        System.out.printf("Bedrooms: %d%n", bedrooms);
    }

    // Setters with validation
    public void setMonthlyRent(double monthlyRent) {
        if (monthlyRent > 0) {
            this.monthlyRent = monthlyRent;
        } else {
            System.out.println("Invalid rent — must be greater than zero");
        }
    }

    public void setAddress(String address) {
        if (address != null && !address.isEmpty()) {
            this.address = address;
        } else {
            System.out.println("Invalid address — cannot be empty");
        }
    }

    public void setBedrooms(int bedrooms) {
        if (bedrooms > 0) {
            this.bedrooms = bedrooms;
        } else {
            System.out.println("Invalid bedrooms — must be greater than zero");
        }
    }
}