package org.example.week2;

public abstract class BaseProperty {

    // Shared fields
    protected String address;
    protected double monthlyRent;

    // Constructor
    public BaseProperty(String address, double monthlyRent) {
        this.address = address;
        this.monthlyRent = monthlyRent;
    }

    // Concrete method - shared by all subclasses
    public double annualRent() {
        return monthlyRent * 12;
    }

    // Abstract method - subclasses MUST implement this
    public abstract String getPropertyType();

    // Getters
    public String getAddress() { return address; }
    public double getMonthlyRent() { return monthlyRent; }
}