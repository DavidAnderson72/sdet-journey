package org.example.week1;

import org.example.common.Taxable;

public class RentalCalculator implements Taxable {

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
        System.out.printf("Tax band:           %s%n", taxBand());
    }

    // Determines tax band and tax owed based on annual profit
    public double taxOwed() {
        double annual = annualGrossProfit();
        if (annual <= 12570) {
            return 0;
        } else if (annual <= 50270) {
            return (annual - 12570) * 0.20;
        } else if (annual <= 125140) {
            return (annual - 50270) * 0.40;
        } else {
            return (annual - 125140) * 0.45;
        }
    }

    // Loop to project income over 12 months
    public void printMonthlyProjection() {
        System.out.println("\n=== 12 Month Projection ===");
        double runningTotal = 0;
        for (int month = 1; month <= 12; month++) {
            runningTotal += grossProfit();
            System.out.printf("Month %d: Profit £%.2f | Running total £%.2f%n",
                    month, grossProfit(), runningTotal);
        }
        System.out.printf("Annual total: £%.2f%n", runningTotal);
    }

    // Taxable interface implementation
    @Override
    public double calculateTax() {
        double annual = annualGrossProfit();
        if (annual <= 12570) return 0;
        else if (annual <= 50270) return (annual - 12570) * 0.20;
        else if (annual <= 125140) return (annual - 50270) * 0.40;
        else return (annual - 125140) * 0.45;
    }

    @Override
    public String getTaxBand() {
        double annual = annualGrossProfit();
        if (annual <= 12570) return "Personal allowance";
        else if (annual <= 50270) return "Basic rate 20%";
        else if (annual <= 125140) return "Higher rate 40%";
        else return "Additional rate 45%";
    }

    @Override
    public boolean requiresSelfAssessment() {
        return annualGrossProfit() > 1000;
    }
}
