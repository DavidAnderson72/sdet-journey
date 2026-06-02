package org.example.Week3;

import org.example.common.Taxable;
import org.example.week2.BaseProperty;
import org.example.week2.PropertyPortfolio;

import java.util.HashMap;

public class TaxYearSummary {

    private PropertyPortfolio portfolio;
    private String taxYear;

    // HashMap storing tax rates by band name
    private static final HashMap<String, Double> TAX_RATES = new HashMap<>();

    static {
        TAX_RATES.put("Personal allowance", 0.00);
        TAX_RATES.put("Basic rate 20%", 20.00);
        TAX_RATES.put("Higher rate 40%", 40.00);
        TAX_RATES.put("Additional rate 45%", 45.00);
    }

    // Constructor
    public TaxYearSummary(PropertyPortfolio portfolio, String taxYear) {
        this.portfolio = portfolio;
        this.taxYear = taxYear;
    }

    // Calculate total gross income
    public double getTotalGrossIncome() {
        return portfolio.getTotalAnnualIncome();
    }

    // Calculate total expenses
    public double getTotalExpenses(double monthlyExpenses) {
        return monthlyExpenses * 12;
    }

    // Calculate total profit
    public double getTotalProfit(double monthlyExpenses) {
        return getTotalGrossIncome() - getTotalExpenses(monthlyExpenses);
    }

    // Calculate all tax owed across all taxable properties
    public double getTaxOwed() {
        double total = 0;
        for (BaseProperty property : portfolio.getPropertiesAboveRent(0)) {
            if (property instanceof Taxable) {
                total += ((Taxable) property).calculateTax();
            }
        }
        return total;
    }

    // Print full tax year summary
    public void printSummary(double monthlyExpenses) {
        double grossIncome = getTotalGrossIncome();
        double expenses = getTotalExpenses(monthlyExpenses);
        double profit = getTotalProfit(monthlyExpenses);
        double taxOwed = (getTaxOwed());
        double netIncome = profit - taxOwed;

        System.out.println("=== Tax Year Summary " + taxYear + " ===");
        System.out.printf("Gross rental income:     £%.2f%n", grossIncome);
        System.out.printf("Total expenses:          £%.2f%n", expenses);
        System.out.printf("Net profit:              £%.2f%n", profit);
        System.out.printf("Tax owed:                £%.2f%n", taxOwed);
        System.out.printf("Net income after tax:    £%.2f%n", netIncome);
        System.out.println();

        // Property breakdown
        System.out.println("=== Property Breakdown ===");
        for (BaseProperty property : portfolio.getPropertiesAboveRent(0)) {
            System.out.printf("%-30s £%.2f/year",
                    property.getAddress(),
                    property.getMonthlyRent() * 12);
            if (property instanceof Taxable) {
                Taxable taxable = (Taxable) property;
                System.out.printf(" | %s | Tax: £%.2f",
                        taxable.getTaxBand(),
                        taxable.calculateTax());
            }
            System.out.println();
        }
    }
}
