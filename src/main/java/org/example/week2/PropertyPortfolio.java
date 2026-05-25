package org.example.week2;

import org.example.common.Taxable;

import java.util.ArrayList;
import java.util.List;

public class PropertyPortfolio {

    // Holds all properties in the portfolio
    private List<BaseProperty> properties;
    private String portfolioName;

    // Constructor
    public PropertyPortfolio(String portfolioName) {
        this.portfolioName = portfolioName;
        this.properties = new ArrayList<>();
    }

    // Add a property to the portfolio
    public void addProperty(BaseProperty property) {
        properties.add(property);
        System.out.printf("Added: %s%n", property.getAddress());
    }

    // Remove a property from the portfolio
    public void removeProperty(BaseProperty property) {
        if (properties.remove(property)) {
            System.out.printf("Removed: %s%n", property.getAddress());
        }
    }

    // Get total monthly income across all properties
    public double getTotalMonthlyIncome() {
        double total = 0;
        for (BaseProperty property : properties) {
            total += property.getMonthlyRent();
        }
        return total;
    }

    // Get total annual income
    public double getTotalAnnualIncome() {
        return getTotalMonthlyIncome() * 12;
    }

    // Get number of properties
    public int getPropertyCount() {
        return properties.size();
    }

    // Print full portfolio summary
    public void printSummary() {
        System.out.println("=== " + portfolioName + " ===");
        System.out.printf("Properties:  %d%n", getPropertyCount());
        System.out.println();

        // Loop through all properties
        for (BaseProperty property : properties) {
            System.out.printf("%-30s £%.2f/month  %s%n",
                    property.getAddress(),
                    property.getMonthlyRent(),
                    property.getPropertyType());
        }

        System.out.println();
        System.out.printf("Total monthly income:  £%.2f%n", getTotalMonthlyIncome());
        System.out.printf("Total annual income:  £%.2f%n", getTotalAnnualIncome());

        // Tax summary for residential properties
        System.out.println("\n=== Tax Position ===");
        for (BaseProperty property : properties) {
            if (property instanceof Taxable) {
                Taxable taxable = (Taxable) property;
                System.out.printf("%-30s %s | Tax: £%.2f%n",
                        property.getAddress(),
                        taxable.getTaxBand(),
                        taxable.calculateTax());
            }
        }
    }
}
