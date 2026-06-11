package org.example.week3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Week3_Thursday {
    public static void main(String[] args) {

        // String methods
        System.out.println("=== String Methods ===");
        String address = "5 Salford Street, Manchester";

        System.out.println("Original:   " + address);
        System.out.println("Upper:      " + address.toUpperCase());
        System.out.println("Lower:      " + address.toLowerCase());
        System.out.println("Length:     " + address.length());
        System.out.println("Contains Manchester:    " + address.contains("Manchester"));
        System.out.println("Starts with 5:  " + address.startsWith("5"));
        System.out.println("Ends with rd:   " + address.endsWith("rd"));
        System.out.println("Replace:    " + address.replace("Manchester", "Salford"));
        System.out.println("Trim:       " +" 5 Salford Street ".trim());
        System.out.println("Substring:  " + address.substring(0, 10));
        System.out.println("Index of M: " + address.indexOf("M"));

        // Split
        String[] parts = address.split(", ");
        System.out.println("Street:     " + parts[0]);
        System.out.println("City:       " + parts[1]);

        // Math methods
        System.out.println("\n=== Math Methods ===");
        double rent = 1150.75;
        double expenses = 349.25;

        System.out.printf("Round rent:      £%d%n", Math.round(rent * 1.0));
        System.out.printf("Floor rent:      £%.0f%n", Math.floor(rent));
        System.out.printf("Ceiling rent:    £%.0f%n", Math.ceil(rent));
        System.out.printf("Max:             £%.2f%n", Math.max(rent, expenses));
        System.out.printf("Min:             £%.2f%n", Math.min(rent, expenses));
        System.out.printf("Absolute value:  £%.2f%n", Math.abs(-500.00));
        System.out.printf("Power (rent^2):  %.2f%n", Math.pow(rent, 2));
        System.out.printf("Square root:     %.2f%n", Math.sqrt(rent));

        // Integer and Double parsing
        System.out.println("\n=== Parsing Strings to Numbers ===");
        String rentString = "1150";
        String rateString = "20.5";

        int rentInt = Integer.parseInt(rentString);
        double rateDouble = Double.parseDouble(rateString);

        System.out.println("Parsed rent:    £" + rentInt);
        System.out.printf("Parsed rate:     %.1f%%%n", rateDouble);
        System.out.println("Max int:        " + Integer.MAX_VALUE);
        System.out.println("Min int:        " + Integer.MIN_VALUE);

        // LocalDate
        System.out.println("\n=== LocalDate ===");
        LocalDate today = LocalDate.now();
        LocalDate tenancyStart = LocalDate.of(2024, 3, 1);
        LocalDate tenancyEnd = tenancyStart.plusMonths(12);
        LocalDate nextReview = today.plusMonths(6);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.println("Today:          " + today.format(formatter));
        System.out.println("Tenancy start:  " + tenancyStart.format(formatter));
        System.out.println("Tenancy end:    " + tenancyEnd.format(formatter));
        System.out.println("Next review:    " + nextReview.format(formatter));
        System.out.println("Is after start: " + today.isAfter(tenancyStart));
        System.out.println("Is before end:  " + today.isBefore(tenancyEnd));

        // RentalUtils utility class methods
        System.out.println("\n=== RentalUtils ===");
        System.out.printf("GBP to SEK:      kr %.2f%n",
                RentalUtils.convertGBPtoSEK(1150.00));
        System.out.printf("Formatted:       %s%n",
                RentalUtils.formatAsCurrency(800.00));
        System.out.println("Tax year:       " +
                RentalUtils.getCurrentTaxYear());

        // Generics demonstration
        System.out.println("\n=== Generics ===");
        RentalUtils.printItem("Address:", "5 Salford Street");
        RentalUtils.printItem("Rent:", 1150.00);
        RentalUtils.printItem("Bedrooms:", 3);
        RentalUtils.printItem("Occupied:", true);

    }
}


