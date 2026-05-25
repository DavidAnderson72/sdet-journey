package org.example.week1;

public class Week1_Saturday {
    public static void main(String[] args) {

        // Create a RentalCalculator object with your property details
        RentalCalculator calculator = new RentalCalculator(1150.00, 350.00, 400.00);

        // Print the full summary
        calculator.printSummary();

        // Call individual methods
        System.out.println("\n=== Individual Calculations ===");
        System.out.printf("Gross profit:  £%.2f%n", calculator.grossProfit());
        System.out.printf("Annual profit: £%.2f%n", calculator.annualGrossProfit());
        System.out.printf("Tax credit:    £%.2f%n", calculator.taxCredit());
        System.out.printf("Net income:    £%.2f%n", calculator.netIncome());
        System.out.println("Tax band:      " + calculator.taxBand());
        System.out.printf("Tax owed:      £%.2f%n", calculator.taxOwed());
        calculator.printMonthlyProjection();

        // Test Taxable interface on RentalCalculator
        System.out.println("\n=== RentalCalculator as Taxable ===");
        calculator.printTaxSummary();
        System.out.printf("Requires self assessment: %s%n",
                calculator.requiresSelfAssessment() ? "Yes" : "No");
    }


}
