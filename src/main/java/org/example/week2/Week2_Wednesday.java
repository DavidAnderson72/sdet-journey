package org.example.week2;

public class Week2_Wednesday {
    public static void main(String[] args) {

        // Create a residential property
        ResidentialProperty residential = new ResidentialProperty(
                "5 Salford Street", 1150.00, 3, true);
        residential.setTenantName("Mr Smith");

        // Create a commercial property
        CommercialProperty commercial = new CommercialProperty(
                "10 Deansgate", 2500.00, 2, "Retail", 45000.00);

        // Print details of both
        System.out.println("=== Residential Property ===");
        residential.printDetails();

        // Print details of both
        System.out.println("\n=== Commercial Property ===");
        commercial.printDetails();

        // Inherited methods work on both
        System.out.println("\n=== Comparing Properties ===");
        System.out.printf("Residential monthly rent:  £%.2f%n",
                residential.getMonthlyRent());
        System.out.printf("Commercial monthly rent:   £%.2f%n",
                commercial.getMonthlyRent());
        System.out.printf("Higher rent: %s%n",
                residential.getMonthlyRent() > commercial.getMonthlyRent()
                ? residential.getAddress()
                        : commercial.getAddress());
    }
}
