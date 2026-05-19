package org.example.week2;

public class Week2_Tuesday {
    public static void main(String[] args) {

        // Create properties using constructor
        Property property1 = new Property("5 Salford Street", 1150.00, 3);
        Property property2 = new Property("12 Manchester Road", 950.00, 2);

        // Use getters to access private fields
        System.out.println("=== Property 1 ===");
        System.out.printf("Address:  %s%n", property1.getAddress());
        System.out.printf("Rent:     £%.2f%n", property1.getMonthlyRent());
        System.out.printf("Bedrooms: %d%n", property1.getBedrooms());

        // Use setters to update private fields
        System.out.println("\n=== Updating Property 1 Rent ===");
        System.out.printf("Old rent: £%.2f%n", property1.getMonthlyRent());
        property1.setMonthlyRent(1200.00);
        System.out.printf("New rent: £%.2f%n", property1.getMonthlyRent());

        // Try setting invalid rent
        System.out.println("\n=== Testing Validation ===");
        property1.setMonthlyRent(-500.00);
        System.out.printf("Rent after invalid update: £%.2f%n",
                property1.getMonthlyRent());

        // Default constructor - no parameters
        Property defaultProperty = new Property();
        System.out.println("\n=== Default Property ===");
        defaultProperty.printDetails();
    }
}
