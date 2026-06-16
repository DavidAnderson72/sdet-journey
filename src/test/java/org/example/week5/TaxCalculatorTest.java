package org.example.week5;

import org.testng.Assert;
import org.testng.annotations.*;

public class TaxCalculatorTest {

    @BeforeSuite(alwaysRun = true)
    public void beforeSuite() {
        System.out.println("Starting Tax Calculator test suite - TestNG");
    }

    @AfterSuite(alwaysRun = true)
    public void afterSuite() {
        System.out.println("Tax Calculator test suite complete - TestNG");
    }

    @DataProvider(name = "basicRateTaxData")
    public Object[][] basicRateTaxData() {
        return new Object[][] {
                // income, expectedTax
                {20000.0, 1486.0},   // just above personal allowance
                {30000.0, 3486.0},   // mid basic rate band
                {50000.0, 7486.0},   // top of basic rate band
        };
    }

    @DataProvider(name = "higherRateTaxData")
    public Object[][] higherRateTaxData() {
        return new Object[][] {
                // income, expectedTax
                {60000.0, 11432.0},  // into higher rate band
                {80000.0, 19432.0},  // mid higher rate band
                {100000.0, 27432.0}, // approaching personal allowance taper
        };
    }

    @Test(groups = {"smoke", "tax"}, dataProvider = "basicRateTaxData")
    public void calculateTax_basicRateIncome_returnsCorrectTax(double income, double expectedTax) {
        double result = calculateTax(income);
        Assert.assertEquals(result, expectedTax,
                "Tax calculation incorrect for income: " + income);
    }

    @Test(groups = {"regression", "tax"}, dataProvider = "higherRateTaxData")
    public void calculateTax_higherRateIncome_returnsCorrectTax(double income, double expectedTax) {
        double result = calculateTax(income);
        Assert.assertEquals(result, expectedTax,
                "Tax calculation incorrect for income: " + income);
    }

    // Simple UK tax calculation
    private double calculateTax(double income) {
        double personalAllowance = 12570.0;
        double basicRateLimit = 50270.0;
        double tax;

        if (income <= personalAllowance) {
            return 0.0;
        }

        if (income <= basicRateLimit) {
            tax = (income - personalAllowance) * 0.20;
        } else {
            tax = (basicRateLimit - personalAllowance) * 0.20;
            tax += (income - basicRateLimit) * 0.40;
        }

        return Math.round(tax * 100.0) / 100.0;
    }
}
