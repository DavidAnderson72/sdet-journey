package org.example.week2;

public class CommercialProperty extends Property {

    //Additional fields specific to commercial properties
    private String businessType;
    private double annualRateableValue;

    //Constructor
    public CommercialProperty(String address, double monthlyRent, int floors, String businessType,
                              double annualRateableValue) {
        // Call parent constructor
        super(address, monthlyRent, floors);
        this.businessType = businessType;
        this.annualRateableValue = annualRateableValue;
    }

    // Getters
    public String getBusinessType() { return businessType; }
    public double getAnnualRateableValue() { return annualRateableValue; }

    // Override printDetails
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.printf("Business type:   %s%n", businessType);
        System.out.printf("Rateable value:  £%.2f%n", annualRateableValue);
    }
}
