package org.example.week1;

public class week1_thursday {
    public static void main(String[] args) {

        // Your property details
        double monthlyRent = 1150.00;
        double monthlyExpenses = 350.00;
        double monthlyProfit = monthlyRent - monthlyExpenses;
        double annualTotal = 0;

        //for loop - 12 month projection
        System.out.println("=== 12 Month Rental Projection ===");
        for (int month = 1; month <= 12; month++) {
            annualTotal += monthlyProfit;
            System.out.printf("Month %d: Income £%.2f | Expenses £%.2f | Profit £%.2f%n",
                    month, monthlyRent, monthlyExpenses, monthlyProfit);
        }
        System.out.println("Annual total profit: £" + annualTotal);

        // while loop - count vacant months until occupied
        System.out.println("\n=== Rent Lost if Vacant ===");
        int vacantMonths = 0;
        double lostIncome = 0;
        while (lostIncome < 5000) {
            vacantMonths++;
            lostIncome += monthlyRent;
            System.out.printf("Vacant month %d: - Lost income so far: £%.2f%n",
                    vacantMonths, lostIncome);

        }
        System.out.println("Months until £5000 lost: " + vacantMonths);

        // do-while loop - always runs at least once
        System.out.println("\n=== Rent Review ===");
        double currentRent = monthlyRent;
        int reviewCount = 0;
        do {
            reviewCount++;
            currentRent *= 1.05;
            long roundedRent = Math.round(currentRent);
            System.out.printf("Review %d: Rent after 5%% increase: £%d.00%n",
                    reviewCount, roundedRent);
                    //Math.round(currentRent * 100.0) / 100.0);
        } while     (currentRent < 1500);
        System.out.printf("Reviews needed to reach £1500: %d%n", reviewCount);

        //break and continue
        System.out.println("\n=== Months with full rent only ===");
        for (int month = 1; month <= 12; month++) {
            if (month == 3 || month == 7) {
                System.out.printf("Month %d: Partial rent - skipping%n", month);
                continue;
            }
            if (month == 10) {
                System.out.printf("Month %d: Void period - stopping%n", month);
                break;
            }
            System.out.printf("Month %d: Full rent £%.2f%n", month, monthlyRent);

            System.out.println("Property:\t5 Salford Street");
            System.out.println("Rent:\t\t£1150.00");
            System.out.println("Profit:\t\t£800.00");

        }
    }
}
