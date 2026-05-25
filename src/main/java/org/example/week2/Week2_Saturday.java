package org.example.week2;

public class Week2_Saturday {
    public static void main(String[] args) {

        // Create portfolio
        PropertyPortfolio portfolio = new PropertyPortfolio(
                "David's Property Portfolio");

        // Crate properties
        ResidentialProperty salford = new ResidentialProperty(
                "5 Salford Street", 1150.00, 3, true);
        salford.setTenantName("Mr Smith");

        ResidentialProperty oak = new ResidentialProperty(
                "22 Oak Avenue", 850.00, 2, false);
        oak.setTenantName("Miss Jones");

        CommercialProperty deansgate = new CommercialProperty(
                "10 Deansgate", 2500.00, 2, "Retail", 45000.00);

        // Add properties to portfolio
        System.out.println("=== Adding Properties ===");
        portfolio.addProperty(salford);
        portfolio.addProperty(oak);
        portfolio.addProperty(deansgate);

        // Print full summary
        System.out.println();
        portfolio.printSummary();

        // Remove a property and reprint
        System.out.println("\n=== After Removing Oak Avenue ===");
        portfolio.removeProperty(oak);
        System.out.println();
        portfolio.printSummary();
    }
}
