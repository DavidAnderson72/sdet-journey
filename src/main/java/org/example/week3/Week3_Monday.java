package org.example.week3;

import org.example.week2.BaseProperty;
import org.example.week2.ResidentialProperty;
import org.example.week2.CommercialProperty;
import org.example.week2.PropertyPortfolio;
import java.util.ArrayList;
import java.util.List;

public class Week3_Monday {
    public static void main(String[] args) {

        // Create an ArrayList of properties
        ArrayList<BaseProperty> properties = new ArrayList<>();

        // Add properties
        properties.add(new ResidentialProperty("5 Salford Street", 1150.00, 3, true));
        properties.add(new ResidentialProperty("22 Oak Avenue", 850.00, 2, false));
        properties.add(new ResidentialProperty("8 Park Lane", 1400.00, 4, true));
        properties.add(new CommercialProperty("10 Deansgate", 2500.00, 2, "Retail",
                45000.00));


        // size() - how many properties
        System.out.println("=== ArrayList Basics ===");
        System.out.printf("Number of properties: %d%n", properties.size());

        // get() - access by index
        System.out.printf("First property: %s%n", properties.get(0).getAddress());
        System.out.printf("Last property: %s%n",
                properties.get(properties.size() - 1).getAddress());

        // contains() - check if property exists
        BaseProperty salford = properties.get(0);
        System.out.printf("Contains Salford Street: %s%n", properties.contains(salford));

        //Iterate using enhanced for loop
        System.out.printf("\n=== All Properties === %n");
        for (BaseProperty property : properties) {
            System.out.printf("%-30s £%.2f/month%n",
                    property.getAddress(),
                    property.getMonthlyRent());
        }

            // Filter - properties above a rent threshold
            System.out.println("\n=== Properties Above £1000/month ===");
            double threshold = 1000.00;
            for (BaseProperty prop : properties) {
                if (prop.getMonthlyRent() > threshold) {
                    System.out.printf("%-30s £%.2f%n",
                            prop.getAddress(),
                            prop.getMonthlyRent());
                }
            }

            //remove() - remove a property
            System.out.println("\n=== Removing Oak Avenue ===");
            properties.remove(1);
            System.out.printf("Properties remaining: %d%n", properties.size());

            // forEach with lambda
            System.out.println("\n=== Address Only (forEach) ===");
            properties.forEach(p -> System.out.println(p.getAddress()));

            // Test PropertyPortfolio methods
            System.out.println("\n=== Properties Above £1000 via Portfolio Method ===");
            PropertyPortfolio portfolio = new PropertyPortfolio("Test Portfolio");
            portfolio.addProperty(properties.get(0));
            portfolio.addProperty(properties.get(1));
            portfolio.addProperty(properties.get(2));

            List<BaseProperty> expensive = portfolio.getPropertiesAboveRent(1000.00);
            expensive.forEach(p -> System.out.printf("%-30s £%.2f%n",
                p.getAddress(), p.getMonthlyRent()));

            System.out.println("\n=== Sorted by Rent ===");
            portfolio.sortByRent();
            portfolio.printSummary();

            // clear() - remove all
            System.out.println("\n=== Clearing Portfolio ===");
            properties.clear();
            System.out.printf("Properties after clear: %d%n", properties.size());

    }

}
