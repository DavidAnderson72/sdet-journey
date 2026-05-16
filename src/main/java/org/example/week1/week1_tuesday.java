package org.example.week1;

public class week1_tuesday {
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

            // String methods
            System.out.println("Address uppercase: " + propertyAddress.toUpperCase());
            System.out.println("Address length: " + propertyAddress.length());
            System.out.println("Contains Salford: " + propertyAddress.contains("Salford"));
            System.out.println("First 5 Characters: " + propertyAddress.substring(0, 5));

            // Comparison operators
            double threshold = 700.00;
            boolean isProfitable = monthlyProfit > threshold;
            boolean isHighRent = monthlyRent >= 1000.00;
            boolean isLowExpenses = monthlyExpenses < 200.00;

            System.out.println("Is profitable above £700: " + isProfitable);
            System.out.println("Is high rent: " + isHighRent);
            System.out.println("Is low expenses: " + isLowExpenses);

            // Logical operators
            boolean isGoodInvestment = isProfitable && isHighRent;
            boolean needsAttention = !isOccupied || isLowExpenses;

            System.out.println("Is good investment: " + isGoodInvestment);
            System.out.println("Needs attention: " + needsAttention);
        }

}
