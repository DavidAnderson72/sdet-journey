package org.example.week2;

public class Week2_Monday {
    public static void main(String[] args) {

        // Create Property objects
        Property property1 = new Property("5 Salford Street", 1150.00, 3);
        Property property2 = new Property("12 Manchester Road", 950.00, 2);

        // Print property details
        property1.printDetails();
        property2.printDetails();

        // Access fields directly
        System.out.println("\nComparing properties:");
        System.out.println("Higher rent: " +
                (property1.getMonthlyRent() > property2.getMonthlyRent()
                        ? property1.getAddress()
                        : property2.getAddress()));
    }
}