package org.example.week2;

public class ResidentialProperty extends BaseProperty implements Taxable {

    private int bedrooms;
    private boolean furnished;
    private String tenantName;

    public ResidentialProperty(String address, double monthlyRent,
                               int bedrooms, boolean furnished) {
        super(address, monthlyRent);
        this.bedrooms = bedrooms;
        this.furnished = furnished;
        this.tenantName = "Vacant";
    }

    // Implement abstract method from BaseProperty
    @Override
    public String getPropertyType() {
        return "Residential Buy-to-Let";
    }

    // Implement Taxable interface methods
    @Override
    public double calculateTax() {
        double annual = annualRent();
        if (annual <= 12570) return 0;
        else if (annual <= 50270) return (annual - 12570) * 0.20;
        else if (annual <= 125140) return (annual - 50270) * 0.40;
        else return (annual - 125140) * 0.45;
    }

    @Override
    public String getTaxBand() {
        double annual = annualRent();
        if (annual <= 12570) return "Personal allowance";
        else if (annual <= 50270) return "Basic rate 20%";
        else if (annual <= 125140) return "Higher rate 40%";
        else return "Additional rate 45%";
    }

    @Override
    public boolean requiresSelfAssessment() {
        return annualRent() > 1000;
    }

    // Getters and setters
    public int getBedrooms() { return bedrooms; }
    public boolean isFurnished() { return furnished; }
    public String getTenantName() { return tenantName; }
    public void setTenantName(String tenantName) { this.tenantName = tenantName; }

    public void printDetails() {
        System.out.println("=== Property Details ===");
        System.out.printf("Type:      %s%n", getPropertyType());
        System.out.printf("Address:   %s%n", address);
        System.out.printf("Rent:      £%.2f%n", monthlyRent);
        System.out.printf("Bedrooms:  %d%n", bedrooms);
        System.out.printf("Furnished: %s%n", furnished ? "Yes" : "No");
        System.out.printf("Tenant:    %s%n", tenantName);
    }
}