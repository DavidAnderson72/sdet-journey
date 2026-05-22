package org.example.week2;

public class CommercialProperty extends BaseProperty {

    //Additional fields specific to commercial properties
    private String businessType;
    private double annualRateableValue;
    private int floors;
    private int getFloors() {return floors; }

    //Constructor
    public CommercialProperty(String address, double monthlyRent, int floors, String businessType,
                              double annualRateableValue) {
        // Call parent constructor
        super(address, monthlyRent);
        this.floors = floors;
        this.businessType = businessType;
        this.annualRateableValue = annualRateableValue;
    }

    // Getters
    public String getBusinessType() { return businessType; }
    public double getAnnualRateableValue() { return annualRateableValue; }

    // Implement abstract method from BaseProperty
    @Override
    public String getPropertyType() {
        return "Commercial";
    }

    // Print commercial property details
    public void printDetails() {
        System.out.println("=== Property Details ===");
        System.out.printf("Type:            %s%n", getPropertyType());
        System.out.printf("Address:         %s%n", address);
        System.out.printf("Rent:            £%.2f%n", monthlyRent);
        System.out.printf("Floors:          %d%n", floors);
        System.out.printf("Business type:   %s%n", businessType);
        System.out.printf("Rateable value:  £%.2f%n", annualRateableValue);
    }
}
