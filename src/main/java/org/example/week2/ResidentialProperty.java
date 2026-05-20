package org.example.week2;

public class ResidentialProperty extends Property {

    // Additional field specific to residential properties
    private boolean furnished;
    private String tenantName;

    // Constructor
    public  ResidentialProperty(String address, double monthlyRent, int bedrooms, boolean furnished) {
        // Call parent constructor using super
        super(address, monthlyRent, bedrooms);
        this.furnished = furnished;
        this.tenantName = "Vacant";
    }

    // Getters and setters
    public boolean isFurnished() { return furnished; }
    public void setFurnished(boolean furnished) { this.furnished = furnished; }
    public String getTenantName() { return tenantName; }
    public void setTenantName(String tenantName) { this.tenantName = tenantName; }

    // Override printDetails from Property
    @Override
    public void printDetails() {
        super.printDetails();
        System.out.printf("Furnished: %s%n", furnished ? "Yes" : "No");
        System.out.printf("Tenant:   %s%n", tenantName);
    }
}
