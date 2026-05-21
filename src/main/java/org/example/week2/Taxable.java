package org.example.week2;

public interface Taxable {

    // Interface methods - no implementation, just the contract
    double calculateTax();
    String getTaxBand();
    boolean requiresSelfAssessment();

    // Default method - interfaces can have these since Java 8
    default void printTaxSummary() {
        System.out.println("=== Tax Summary ===");
        System.out.printf("Tax band:           %s%n", getTaxBand());
        System.out.printf("Tax owed:           £%.2f%n", calculateTax());
        System.out.printf("Self assessment:    %s%n",
                requiresSelfAssessment() ? "Required" : "Not required");
    }
}
