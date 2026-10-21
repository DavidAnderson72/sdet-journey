package org.example.week4;

import org.example.week3.InvalidRentException;
import org.example.week3.RentalUtils;
import org.example.week3.Week3_Friday;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("RentalUtils Tests")
class RentalUtilsTest {

    @BeforeAll
    static void BeforeAll() {
        System.out.println("Starting RentalUtils test suite");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("RentalUtils test suite complete");
    }

    @Test
    @DisplayName("convertGBPtoSEK returns correct value for known input")
    void convertGBPtoSEK_validAmount_returnCorrectConversion() {
        // Arrange
        double amountGBP = 100.0;

        // Act
        double result = RentalUtils.convertGBPtoSEK(amountGBP);

        // Assert
        assertEquals(1350.0, result,
                "100 GBP should convert to 1350 SEK at 13.5 rate");
    }

    @Test
    @DisplayName("convertGBPtoSEK returns zero when input is zero")
    void convertGBPtoSEKWithZeroReturnsZero() {
        // Arrange
        double amountGBP = 0.0;
        // Act
        double result = RentalUtils.convertGBPtoSEK(amountGBP);

        // Assert
        assertEquals(0.0, result,
                "Converting zero GBP should return zero SEK");
    }

    @Test
    @DisplayName("formatAsCurrency returns correctly formatted string")
    void formatAsCurrency_validAmount_returnsFormattedString() {
        // Arrange
        double amount = 1200.0;

        // Act
        String result = RentalUtils.formatAsCurrency(amount);

        // Assery
        assertEquals("£1200.00", result,
                "Should format 1200.0 as £1200.00");
    }

    @Test
    @DisplayName("formatAsCurrency never returns null")
    void formatAsCurrency_validAmount_returnsNotNull() {
        // Arrange
        double amount = 500.0;

        // Act
        String result = RentalUtils.formatAsCurrency(amount);

        // Assert
        assertNotNull(result,
                "formatAsCurrency should never return null");
    }

    @Test
    @DisplayName("getCurrentTaxYear returns correct tax year string for current date")
    void getCurrentTaxYear_currentDate_returnsCorrectTaxYear() {
        // Arrange
        // uses Local.Date.now() internally

        // Act
        String result = RentalUtils.getCurrentTaxYear();

        // Assert
        assertEquals("2026/2027", result,
                "In June 2026 the tax year should be 2026/2067");
    }

    @Test
    @DisplayName("getCurrentTaxYear never returns an empty string")
    void getCurrentTaxYear_currentDate_returnsNotEmptyString() {
        // Arrange
        // uses Local.Date.now() internally

        // Act
        String result = RentalUtils.getCurrentTaxYear();

        // Assert
        assertFalse(result.isEmpty(),
                "Tax year string should never be empty");
    }

    @Test
    @DisplayName("validateRent InvalidRentException for negative rent")
    void validateRent_negativeRent_ThrowsInvalidRentException() {
        // Arrange
        double invalidRent = -100.0;
        
        // Act & Assert
        assertThrows(InvalidRentException.class,
                () -> Week3_Friday.validateRent(invalidRent),
                "Negative rent should throw InvalidRentException");
    }

    @Test
    @DisplayName("validateRent throws InvalidRentException for zero rent")
    void validateRent_zeroRent_throwsInvalidRentException() {
        // Arrange
        double invalidRent = 0.0;

        // Act & Assert
        assertThrows(InvalidRentException.class,
                () -> Week3_Friday.validateRent(invalidRent),
                "Zero rent should throw InvalidRentException");
    }

    @Test
    @DisplayName("formatAsCurrency passes all grouped assertions")
    void formatAsCurrency_validAmount_passesAllGroupedAssertions() {
        // Arrange
        double amount = 750.0;

        // Act
        String result = RentalUtils.formatAsCurrency(amount);

        // Assert
        assertAll("currency formatting checks",
            () -> assertNotNull(result,
                    "Result should not be null"),
            () -> assertTrue(result.startsWith("£"),
            "Result should start with pound sign"),
            () -> assertEquals("£750.00", result,
                    "Result should be formatted to 2 decimal places")
        );
    }

}
