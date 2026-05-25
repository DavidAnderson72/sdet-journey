package org.example.week2;

import org.example.common.Taxable;

import java.util.ArrayList;
import java.util.List;

public class Week2_Friday {
    public static void main(String[] args) {

        // Polymorphism - different objects, same type reference
        List<BaseProperty> portfolio = new ArrayList<>();

        // Add different property types to the same list
        ResidentialProperty residential1 = new ResidentialProperty(
                "5 Salford Street", 1150.00, 3, true);
        residential1.setTenantName("Mr Smith");

        ResidentialProperty residential2 = new ResidentialProperty(
                "22 Oak Avenue", 850.00, 2, false);
        residential2.setTenantName("Miss Jones");

        CommercialProperty commercial = new CommercialProperty(
                "10 Deansgate", 2500.00, 2, "Retail", 45000.00);

        // All stored in the same list as BaseProperty
        portfolio.add(residential1);
        portfolio.add(residential2);
        portfolio.add(commercial);

        // Polymorphism in action - loop through all properties
        System.out.println("=== Portfolio Overview ===");
        double totalMonthlyIncome = 0;
        for (BaseProperty property : portfolio) {
            System.out.printf("Address: %-25s | Rent: £%.2f | Type: %s%n",
                    property.getAddress(),
                    property.getMonthlyRent(),
                    property.getPropertyType());
            totalMonthlyIncome += property.getMonthlyRent();
        }
        System.out.printf("%nTotal monthly income: £%.2f%n", totalMonthlyIncome);
        System.out.printf("Total annual income:  £%.2f%n", totalMonthlyIncome * 12);

        // instanceof - check what type each object actually is
        System.out.println("\n=== Property Type Check ===");
        for (BaseProperty property : portfolio) {
            if (property instanceof ResidentialProperty) {
                ResidentialProperty rp = (ResidentialProperty) property;
                System.out.printf("%s - Residential | Tenant: %s%n",
                        rp.getAddress(), rp.getTenantName());
            } else if (property instanceof CommercialProperty) {
                CommercialProperty cp = (CommercialProperty) property;
                System.out.printf("%s - Commercial | Business: %s%n",
                        cp.getAddress(), cp.getBusinessType());
            }
        }

        // Taxable interface - only residential properties implement it
        System.out.println("\n=== Tax Position ===");
        for (BaseProperty property : portfolio) {
            if (property instanceof Taxable) {
                Taxable taxable = (Taxable) property;
                System.out.printf("%s: Tax band - %s | Tax owed - £%.2f%n",
                        property.getAddress(),
                        taxable.getTaxBand(),
                        taxable.calculateTax());
            }
        }
    }
}
