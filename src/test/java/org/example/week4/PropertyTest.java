package org.example.week4;

import org.example.week2.Property;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;


@DisplayName("Property Tests")
class PropertyTest {

    Property property;

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("Starting property test suite");
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("Property test suite completed");
    }

    @BeforeEach
    void setUp() {
        property = new Property("123 Test Street", 1200.0, 3);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test completed");
    }

    @Test
    void getAddress_freshlyCreatedProperty_returnsConstructorAddress() {
        // Arrange
        // property created in @BeforeEach

        // Act
        String result = property.getAddress();

        // Assert
        assertEquals("123 Test Street", result,
                "getAddress should return the address passed into the constructor");
    }

    @Test
    @DisplayName("getMonthlyRent returns the rent set during construction")
    void getMonthlyRent_freshlyCreatedProperty_returns1200() {
        // Arrange
        // property created in @BeforeEach

        // Act
        double result = property.getMonthlyRent();

        // Assert
        assertEquals(1200.0, result,
                "getMonthlyRent should return rent passed into the constructor");
    }

    @Test
    @DisplayName("setMonthlyRent updates the rent to the new value")
    void setMonthly_validateNewRent_updateRentCorrectly() {
        // Arrange
        double newRent = 1500.0;

        // Act
        property.setMonthlyRent(newRent);
        double result = property.getMonthlyRent();

        // Assert
        assertEquals(1500.0, result,
                "getMonthlyRent should return the new rent after setMonthlyRent is called");
    }

    @Test
    @DisplayName("getMonthlyRent returns a positive value")
    void getMonthlyRent_freshlyCreatedProperty_returnsPositiveValue() {
        // Arrange
        // property created in @BeforeEach

        // Act
        double result = property.getMonthlyRent();

        // Assert
        assertTrue(result > 0,
                "Monthly rent should always be a positive value");
    }

}  // end of class
