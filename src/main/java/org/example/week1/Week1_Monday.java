package org.example.week1;

public class Week1_Monday {
    public static void main(String[] args) {

        // Variables and data types
        int bedrooms = 3;
        double monthlyRent = 1150.00;
        double monthlyExpenses = 350.00;
        String propertyAddress = "5 Salford Street";
        boolean isOccupied = true;

        //Calculate rental profit
        double monthlyProfit = monthlyRent - monthlyExpenses;

        // Print everything out
        System.out.println("Property: " + propertyAddress);
        System.out.println("Bedrooms: " + bedrooms);
        System.out.println("Occupied: " + isOccupied);
        System.out.println("Monthly rent: £" + monthlyRent);
        System.out.println("Monthly expenses: £" + monthlyExpenses);
        System.out.println("Monthly profit: £" +monthlyProfit);

        // Arithmetic operators
        double annualProfit = monthlyProfit * 12;
        double profitAfterTax = annualProfit * 0.80;

        System.out.println("Annual profit: £" + annualProfit);
        System.out.println("Profit after 20% tax: £" + profitAfterTax);
    }
}
