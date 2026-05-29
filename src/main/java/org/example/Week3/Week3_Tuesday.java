package org.example.Week3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Week3_Tuesday {
    public static void main(String[] args) {

        // HashMap - key/value pairs
        System.out.println("=== UK Tax Bands (HashMap) ===");
        HashMap<String, Double> taxBands = new HashMap<>();

        // Put() - key/value pairs
        taxBands.put("Personal allowance", 0.00);
        taxBands.put("Basic rate", 20.00);
        taxBands.put("Higher rate", 40.00);
        taxBands.put("Additional rate", 45.00);

        // get() - retrieve by key
        System.out.printf("Basic rate tax: %.0f%%%n", taxBands.get("Basic rate"));
        System.out.printf("Higher rate tax: %.0f%%%n", taxBands.get("Higher rate"));

        // containsKey() - check if key exists
        System.out.printf("Has basic rate: %s%n", taxBands.containsKey("Basic rate"));
        System.out.printf("Has super tax: %s%n", taxBands.containsKey("super tax"));

        // keySet() - iterate through all keys
        System.out.println("\n=== All Tax Bands ===");
        for (String band : taxBands.keySet()) {
            System.out.printf("%-25s %.0f%%%n", band, taxBands.get(band));
        }

        // values() - iterate through all values
        System.out.println("\n=== All Tax Rates ===");
        for (double rate : taxBands.values()) {
            System.out.printf("%.0f%%%n", rate);
        }

        // entrySet() - iterate through key/value pairs together
        System.out.println("\n=== Tax Bands with Rates ===");
        for (Map.Entry<String, Double> entry : taxBands.entrySet()) {
            System.out.printf("%-25s %.0f%%%n",
                    entry.getKey(), entry.getValue());
        }

        // Property expenses HashMap
        System.out.println("\n=== Property Expenses (HashMap) ===");
        HashMap<String, Double> expenses = new HashMap<>();
        expenses.put("Letting agent fee", 115.00);
        expenses.put("Insurance", 45.00);
        expenses.put("Maintenance", 80.00);
        expenses.put("Mortgage interest", 400.00);
        expenses.put("Accountancy", 50.00);

        // Calculate total expenses
        double totalExpenses = 0;
        for (double amount : expenses.values()) {
            totalExpenses += amount;
        }
        System.out.printf("Total monthly expenses: £%.2f%n", totalExpenses);

        // HashSet - unique values only
        System.out.println("\n=== Property Types (HashSet) ===");
        HashSet<String> propertyTypes = new HashSet<>();
        propertyTypes.add("Residential");
        propertyTypes.add("Commercial");
        propertyTypes.add("Residential"); // duplicate - ignored
        propertyTypes.add("HMO");
        propertyTypes.add("Commercial"); // duplicate - ignored

        System.out.printf("Unique property types: %d%n", propertyTypes.size());
        propertyTypes.forEach(type -> System.out.println("- " + type));

        // When to use List vs Map vs Set
        System.out.println("\n=== Collection Summary ===");
        System.out.println("List    - ordered, allows duplicates, access by index");
        System.out.println("Map     - key/value pairs, fast lookup by key");
        System.out.println("Set     - unique values only, no duplicates");
    }
}
