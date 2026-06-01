package org.example.Week3;

import org.example.week2.BaseProperty;
import org.example.week2.CommercialProperty;
import org.example.week2.PropertyPortfolio;
import org.example.week2.ResidentialProperty;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;

public class Week3_Friday {
    public static void main(String[] args) {

        // Polymorphism - store different types in one list
        System.out.println("=== Building Portfolio ===");
        PropertyPortfolio portfolio =
                new PropertyPortfolio("David's Portfolio");

        ResidentialProperty salford = new ResidentialProperty(
                "5 Salford Street", 1150.00, 3, true);
        salford.setTenantName("Mr Smith");

        ResidentialProperty oak = new ResidentialProperty(
                "22 Oak Avenue", 850.00, 2, false);
        oak.setTenantName("Miss Jones");

        ResidentialProperty park = new ResidentialProperty(
                "8 Park Lane", 1400.00, 4, true);
        park.setTenantName("Dr Wilson");

        CommercialProperty deansgate = new CommercialProperty(
                "10 Deansgate", 2500.00, 2, "Retail", 45000.00);

        portfolio.addProperty(salford);
        portfolio.addProperty(oak);
        portfolio.addProperty(park);
        portfolio.addProperty(deansgate);

        // Portfolio summary
        System.out.println();
        portfolio.printSummary();

        // Collections - filter above threshold
        System.out.println("\n=== Properties Above £1000/month ===");
        List<BaseProperty> expensive =
                portfolio.getPropertiesAboveRent(1000.00);
        expensive.forEach(p -> System.out.printf("%-30s £%.2f%n",
                p.getAddress(), p.getMonthlyRent()));

        // Hashmap - expensive breakdown per property
        System.out.println("\n=== Expense Breakdown ===");
        HashMap<String, Double> expenses = new HashMap<>();
        expenses.put("5 Salford Street", 350.00);
        expenses.put("22 Oak Avenue", 280.00);
        expenses.put("8 Park Lane", 420.00);
        expenses.put("10 Deansgate", 800.00);

        double totalExpenses = 0;
        for (String address : expenses.keySet()) {
            double expense = expenses.get(address);
            double rent = getRentForAddress(address, salford, oak, park, deansgate);
            double profit = rent - expense;
            totalExpenses += expense;
            System.out.printf("%-30s Rent: £%.2f | " +
                    "Expenses: £%.2f | Profit: £%.2f%n",
                    address, rent, expense, profit);
        }
        System.out.printf("Total expenses: £%.2f%n", totalExpenses);

        // Exception handling - invalid rent
        System.out.println("\n=== Exception Handling ===");
        try {
            validateRent(-100.00);
        } catch (InvalidRentException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // LocalDate - tenancy dates
        System.out.println("\n=== Tenancy Dates ===");
        LocalDate tenancyStart = LocalDate.of(2024, 3, 1);
        LocalDate tenancyEnd = tenancyStart.plusMonths(12);
        LocalDate today = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Tenancy start:  " + tenancyStart.format(formatter));
        System.out.println("Tenancy end:    " + tenancyEnd.format(formatter));
        System.out.println("Expired:    " + today.isAfter(tenancyEnd));

        // RentalUtils
        System.out.println("\n=== Currency Conversion ===");
        double totalMonthly = portfolio.getTotalMonthlyIncome();
        System.out.printf("Total monthly (GBP): £%.2f%n", totalMonthly);
        System.out.printf("Total monthly (SEK): %s%n",
            RentalUtils.formatAsCurrency(
                    RentalUtils.convertGBPtoSEK(totalMonthly))
                    .replace("£", "kr "));
        System.out.println("Current tax year:   " + RentalUtils.getCurrentTaxYear());

        // Generics
        System.out.println("\n=== Portfolio Summary (Generics) ===");
        RentalUtils.printItem("Properties:", portfolio.getPropertyCount());
        RentalUtils.printItem("Monthly income:",
                RentalUtils.formatAsCurrency(totalMonthly));
        RentalUtils.printItem("Annual income:",
                RentalUtils.formatAsCurrency(totalMonthly * 12));
        RentalUtils.printItem("Tax year:",
                RentalUtils.getCurrentTaxYear());
    }

    // Helper method to get rent for a specific address
    static double getRentForAddress(String address,
                                    BaseProperty... properties) {
        for (BaseProperty p : properties) {
            if (p.getAddress().equals(address)) {
                return p.getMonthlyRent();
            }
        }
        return 0.00;
    }

    // Validate rent - throws custom exception
    static void validateRent(double rent) throws InvalidRentException {
        if (rent <= 0) {
            throw new InvalidRentException(
                    "Rent must be positive. Provided:   £" + rent);
        }
    }
}
