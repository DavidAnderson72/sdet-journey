package org.example.week1;

public class RentalCalculator {

    // Instance variables
    private double monthlyRent;
    private double monthlyExpenses;
    private double mortgageInterest;

    // Constructor
    public RentalCalculator(double monthlyRent, double monthlyExpenses, double mortgageInterest) {
        this.monthlyRent = monthlyRent;
        this.monthlyExpenses = monthlyExpenses;
        this.mortgageInterest = mortgageInterest;
    }

    // Gross profit - rent minus expenses
    public double grossProfit() {
        return monthlyRent - monthlyExpenses;
    }

    // Annual gross profit
    public double annualGrossProfit() {
        return grossProfit() * 12;
    }

    // Section 24 tax credit  - 20% of mortgage interest
    public double taxCredit() {
        return mortgageInterest * 0.20;
    }

    // Net income - annual profit minus tax credit
    public double netIncome() {
        return annualGrossProfit() - taxCredit();
    }

    // Tax band based on annual profit
    public String taxBand() {
        double annual = annualGrossProfit();
        if (annual <= 12570) {
            return "Personal allowance - no tax";
        } else if (annual <= 50270) {
            return "Basic rate - 20%";
        } else if (annual <= 125140) {
            return "Higher rate - 40%";
        } else {
            return "Additional rate - 45%";
        }
    }

    // Print full summary
    public void printSummary() {
        System.out.println("=== Rental Calculator Summary ===");
        System.out.printf("Monthly rent:       £%.2f%n", monthlyRent);
        System.out.printf("Monthly expenses:   £%.2f%n", monthlyExpenses);
        System.out.printf("Monthly profit:     £%.2f%n", grossProfit());
        System.out.printf("Annual profit:      £%.2f%n", annualGrossProfit());
        System.out.printf("Tax credit (S.24):  £%.2f%n",taxCredit());
        System.out.printf("Net income:         £%.2f%n", netIncome());
        System.out.println("Tax band:        " + taxBand());
    }
}
