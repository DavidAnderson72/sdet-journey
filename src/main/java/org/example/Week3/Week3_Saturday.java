package org.example.Week3;

import org.example.week2.CommercialProperty;
import org.example.week2.PropertyPortfolio;
import org.example.week2.ResidentialProperty;

public class Week3_Saturday {
    public static void main(String[] args) {

        // Build portfolio
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

        // Create tax year summary
        String taxYear = RentalUtils.getCurrentTaxYear();
        TaxYearSummary summary = new TaxYearSummary(portfolio, taxYear);

        // Print summary with estimated monthly expenses
        double monthlyExpenses = 1850.00;
        System.out.println();
        summary.printSummary(monthlyExpenses);

        // Exception handling - test invalid rent
        System.out.println("=== Exception Handling ===");
        try {
            validateAndAddProperty(portfolio, -100.00);
        } catch (InvalidRentException e) {
            System.out.println("Caught: " + e.getMessage());
        }

        // RentalUtils
        System.out.println("\n=== Currency Conversion ===");
        double monthlyIncome = portfolio.getTotalMonthlyIncome();
        System.out.printf("Monthly (GBP): £%.2f%n", monthlyIncome);
        System.out.printf("Monthly (SEK): kr %.2f%n",
                RentalUtils.convertGBPtoSEK(monthlyIncome));
        System.out.println("Tax year: " + RentalUtils.getCurrentTaxYear());
    }

    // Validate rent before adding property
    static void validateAndAddProperty(PropertyPortfolio portfolio,
                                       double rent) throws InvalidRentException {
        if (rent <= 0) {
            throw new InvalidRentException(
                    "Cannot add property with invalid rent: £" + rent);

        }

    }

}


