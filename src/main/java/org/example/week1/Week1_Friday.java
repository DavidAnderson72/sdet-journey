package org.example.week1;

public class Week1_Friday {
    public static void main(String[] args) {

        // Calling our methods
        double rent = 1150;
        double expenses = 350;
        double mortgageInterest = 400;

        double profit = calculateProfit(rent, expenses);
        double annualProfit = calculateAnnualProfit(profit);
        double taxCredit = calculateMortgageTaxCredit(mortgageInterest);

        printPropertySummary(rent, expenses, profit, annualProfit, taxCredit);

        // Calculate profit with S.24 tax credit applied
        double profitWithCredit = calculateProfit(rent, expenses, mortgageInterest);
        System.out.printf("Profit with S.24 credit: £%.2f%n", profitWithCredit);

        // Calling a method that calls other methods
        double netAnnual = calculateNetAnnualIncome(rent, expenses, mortgageInterest);
        System.out.printf("Net annual income: £%.2f%n", netAnnual);
    }

    // Method 1 calculate monthly profit
    static double calculateProfit(double rent, double expenses) {
        return rent - expenses;
    }

    // Method 1 (overloaded) - calculate monthly profit inc Section 24 tax credit
    static double calculateProfit(double rent, double expenses, double mortgageInterest) {
        double basicProfit = rent - expenses;
        double taxCredit = mortgageInterest * 0.20;
        return basicProfit + taxCredit;
    }

    // Method 2 - calculate annual profit
    static double calculateAnnualProfit(double monthlyProfit) {
        return monthlyProfit * 12;
    }

    //Method 3 - calculate Section 24 mortgage tax credit
    static double calculateMortgageTaxCredit(double mortgageInterest) {
        return mortgageInterest * 0.20;
    }

    // Method 4 - print full summary
    static void printPropertySummary(double rent, double expenses, double profit,
                                     double annualProfit, double taxCredit) {
        System.out.println("=== Property Summary ===");
        System.out.printf("Monthly rent:       £%.2f%n", rent);
        System.out.printf("Monthly expenses:   £%.2f%n", expenses);
        System.out.printf("Monthly profit:     £%.2f%n", profit);
        System.out.printf("Annual profit       £%.2f%n", annualProfit);
        System.out.printf("Tax credit (S.24):  £%.2f%n", taxCredit);
        System.out.printf("Net tax position:   £%.2f%n", annualProfit - taxCredit);
    }

    // Method 5 - calls other methods internally
    static double calculateNetAnnualIncome(double rent, double expenses,
                                           double mortgageInterest) {
        double monthly = calculateProfit(rent, expenses);
        double annual = calculateAnnualProfit(monthly);
        double credit = calculateMortgageTaxCredit(mortgageInterest);
        return annual - credit;
    }


}