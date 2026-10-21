package org.example.week5;

import org.example.week3.InvalidRentException;
import org.example.week3.RentalUtils;
import org.example.week3.Week3_Friday;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RentalUtilsTest {

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        System.out.println("Starting RentalUtils test class - TestNG");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("RentalUtils test class complete - TestNG");
    }

    @Test(groups = {"smoke", "rental"},
            description = "convertGBPtoSEK returns correct value for known input")
    public void convertGBPtoSEK_validAmount_returnCorrectConversion() {
        // Arrange
        double amountGBP = 100.0;

        // Act
        double result = RentalUtils.convertGBPtoSEK(amountGBP);

        // Assert (TestNG: actual first, expected second)
        Assert.assertEquals(result, 1350.0,
                "100 GBP should convert to 1350 SEK at 13.5 rate");
    }

    @Test(groups = {"regression", "rental"},
            description = "convertGBPtoSEK returns zero when input is zero")
    public void convertGBPtoSEK_zeroAmount_returnsZero() {
        // Arrange
        double amountGBP = 0.0;

        // Act
        double result = RentalUtils.convertGBPtoSEK(amountGBP);

        // Assert
        Assert.assertEquals(result, 0.0,
                "Converting zero GBP should return zero SEK");
    }

    @Test(groups = {"smoke", "rental"},
            description = "formatAsCurrency returns correctly formatted string")
    public void formatAsCurrency_validAmount_returnsFormattedString() {
        // Arrange
        double amount = 1200.0;

        // Act
        String result = RentalUtils.formatAsCurrency(amount);

        // Assert
        Assert.assertEquals(result, "£1200.00",
                "Should format 1200.0 as £1200.00");
    }

    @Test(groups = {"regression", "rental"},
            description = "formatAsCurrency never returns null")
    public void formatAsCurrency_validAmount_returnsNotNull() {
        // Arrange
        double amount = 500.0;

        // Act
        String result = RentalUtils.formatAsCurrency(amount);

        // Assert
        Assert.assertNotNull(result,
                "formatAsCurrency should never return null");
    }

    @Test(groups = {"regression", "rental"},
            description = "getCurrentTaxYear returns correct tax year string for current date")
    public void getCurrentTaxYear_currentDate_returnsCorrectTaxYear() {
        // Act
        String result = RentalUtils.getCurrentTaxYear();

        // Assert
        Assert.assertEquals(result, "2026/2027",
                "Between 6 April 2026 and 5 April 2027 the tax year should be 2026/2027");
    }

    @Test(groups = {"regression", "rental"},
            description = "getCurrentTaxYear never returns an empty string")
    public void getCurrentTaxYear_currentDate_returnsNotEmptyString() {
        // Act
        String result = RentalUtils.getCurrentTaxYear();

        // Assert
        Assert.assertFalse(result.isEmpty(),
                "Tax year string should never be empty");
    }

    @Test(groups = {"smoke", "rental"},
            description = "validateRent throws InvalidRentException for negative rent")
    public void validateRent_negativeRent_throwsInvalidRentException() {
        // Arrange
        double invalidRent = -100.0;

        // Act & Assert
        Assert.assertThrows(InvalidRentException.class,
                () -> Week3_Friday.validateRent(invalidRent));
    }

    @Test(groups = {"regression", "rental"},
            description = "validateRent throws InvalidRentException for zero rent")
    public void validateRent_zeroRent_throwsInvalidRentException() {
        // Arrange
        double invalidRent = 0.0;

        // Act & Assert
        Assert.assertThrows(InvalidRentException.class,
                () -> Week3_Friday.validateRent(invalidRent));
    }

    @Test(groups = {"regression", "rental"},
            description = "formatAsCurrency passes all grouped checks")
    public void formatAsCurrency_validAmount_passesAllGroupedAssertions() {
        // Arrange
        double amount = 750.0;

        // Act
        String result = RentalUtils.formatAsCurrency(amount);

        // Assert - SoftAssert is TestNG's equivalent of JUnit's assertAll
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertNotNull(result, "Result should not be null");
        softAssert.assertTrue(result.startsWith("£"), "Result should start with pound sign");
        softAssert.assertEquals(result, "£750.00", "Result should be formatted to 2 decimal places");
        softAssert.assertAll(); // reports every failure at once; without this call nothing fails
    }
}