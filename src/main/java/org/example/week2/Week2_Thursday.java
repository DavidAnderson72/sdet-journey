package org.example.week2;

import org.example.common.Taxable;

public class Week2_Thursday {
    public static void main(String[] args) {

        // Create residential property
        ResidentialProperty residential = new ResidentialProperty(
                "5 Salford Street", 1150.00, 3, true);
        residential.setTenantName("Mr Smith");

        // Print property details
        residential.printDetails();

        // Use Taxable interface methods
        System.out.println();
        residential.printTaxSummary();

        // Annual rent from BaseProperty
        System.out.printf("%nAnnual rent: £%.2f%n", residential.annualRent());

        // Abstract method implemented
        System.out.printf("Property type: %s%n", residential.getPropertyType());

        // Demonstrate interface reference
        System.out.println("\n=== Using Interface Reference ===");
        Taxable taxable = residential;
        System.out.printf("Tax via interface: £%.2f%n", taxable.calculateTax());
        taxable.printTaxSummary();
    }
}