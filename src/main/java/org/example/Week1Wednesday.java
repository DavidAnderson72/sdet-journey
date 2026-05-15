package org.example;

public class Week1Wednesday {
    public static void main(String[] args) {

        // Your rental income details
        double annualProfit = 9600.00;
        String propertyType = "residential";

        // if/else - Determine UK tax band
        if (annualProfit <= 12570) {
            System.out.println("Within personal allowance - no tax owed");
        } else if (annualProfit <= 50270) {
            double taxOwed = (annualProfit - 12570) * 0.20;
            System.out.println("Basic rate taxpayer - 20%");
            System.out.println("Tax owed: £" + taxOwed);
        } else if (annualProfit <= 125140) {
            double taxOwed = (annualProfit - 50270) * 0.40;
            System.out.println("Higher rate taxpayer - 40%");
            System.out.println("Tax owed: £" + taxOwed);
        } else {
            double taxOwed = (annualProfit - 125140) * 0.45;
            System.out.println("Additional rate taxpayer - 45%");
            System.out.println("Tax owed: £" + taxOwed);
        }

        // Switch statement - property type
        switch (propertyType) {
            case "residential":
                System.out.println("Property type: Residential buy-to-let");
                break;
            case "commercial":
                System.out.println("Property type: Commercial property");
                break;
            case "hmo":
                System.out.println("Property type: House of multiple occupancy");
                break;
            default:
                System.out.println("Property type: Unknown");
        }

        // Comparison operators recap
        boolean needsSelfAssessment = annualProfit > 1000;
        boolean isHighEarner = annualProfit > 50270;

        System.out.println("Needs self assessment: " + needsSelfAssessment);
        System.out.println("Is higher rate taxpayer: " + isHighEarner);
    }
}
