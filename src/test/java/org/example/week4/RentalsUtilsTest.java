package org.example.week4;

import org.example.Week3.InvalidRentException;
import org.example.Week3.RentalUtils;
import org.example.Week3.Week3_Friday;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
    void convertGBPtoSEKReturnCorrectValue() {
        double result = RentalUtils.convertGBPtoSEK(100.0);
        assertEquals(1350.0, result);
    }

    @Test
    void convertGBPtoSEKWithZeroReturnsZero() {
        double result = RentalUtils.convertGBPtoSEK(0.0);
        assertEquals(0.0, result);
    }

    @Test
    void formatAsCurrencyFormatsCorrectly() {
        String result = RentalUtils.formatAsCurrency(1200.0);
    }

    @Test
    void formatAsCurrencyIsNotNull() {
        String result = RentalUtils.formatAsCurrency(500.0);
        assertNotNull(result);
    }

    @Test
    void getCurrentTaxYearReturnsCorrectFormat() {
        String result = RentalUtils.getCurrentTaxYear();
        assertEquals("2026/2027", result);
    }

    @Test
    void getCurrentTaxYearIsNotEmpty() {
        String result = RentalUtils.getCurrentTaxYear();
        assertFalse(result.isEmpty());
    }

    @Test
    void validateRentThrowsExceptionForNegativeRent() {
        assertThrows(InvalidRentException.class, () -> {
            Week3_Friday.validateRent(-100.0);
        });
    }

    @Test
    void validateRentThrowsExceptionForZeroRent() {
        assertThrows(InvalidRentException.class, () -> {
            Week3_Friday.validateRent(0.0);
        });
    }

    @Test
    void assertAllExample() {
        String currency = RentalUtils.formatAsCurrency(750.0);
        assertAll("currency formatting checks",
            () -> assertNotNull(currency),
            () -> assertTrue(currency.startsWith("£")),
            () -> assertEquals("£750.00", currency)
        );
    }

}
