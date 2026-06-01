package org.example.Week3;

import java.time.LocalDate;

public class RentalUtils {

    // Exchange rate GBP to SEK
    private static final double GBP_to_SEK = 13.5;

    // Convert GBP to SEK
    public static double convertGBPtoSEK(double amountGBP) {
        return amountGBP * GBP_to_SEK;
    }

    // Formata double as a currency string
    public static String formatAsCurrency(double amount) {
        return String.format("£%.2f", amount);
    }

    // Get the current UK tax year as a string
    public static String getCurrentTaxYear() {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();

        // UK tax year runs April to April
        if (month >= 4) {
            return year + "/" + (year + 1);
        } else {
            return (year - 1) + "/" + year;
        }
    }

    // Check if a date is within the current tax year
    public static boolean isTheCurrentTaxYear(LocalDate date) {
        LocalDate today = LocalDate.now();
        int year = today.getYear();
        int month = today.getMonthValue();

        LocalDate taxYearStart;
        if (month >= 4) {
            taxYearStart = LocalDate.of(year, 4, 6);
        } else {
            taxYearStart = LocalDate.of(year - 1, 4, 6);
        }
        LocalDate taxYearEnd = taxYearStart.plusYears(1).minusDays(1);

        return !date.isBefore(taxYearStart) && !date.isAfter(taxYearEnd);
    }

    // Generic method - works with any type
    public static <T> void printItem(String label, T item) {
        System.out.printf("%-20s %s%n", label, item.toString());
    }
}
