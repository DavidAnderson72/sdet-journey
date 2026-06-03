package org.example.week4;

import org.example.week2.Property;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PropertyTest {

    Property property;

    @BeforeEach
    void setUp() {
        property = new Property("123 Test Street", 1200.0, 3);
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test completed");
    }

    @Test
    void propertyIsCreatedWithCorrectAddress() {
        assertEquals("123 Test Street", property.getAddress());
    }

    @Test
    void propertyCreatedWithCorrectRent() {
        assertEquals(1200.0, property.getMonthlyRent());
    }

    @Test
    void setMonthlyRentUpdatesCorrectly() {
        property.setMonthlyRent(1500.0);
        assertEquals(1500.0, property.getMonthlyRent());
    }

    @Test
    void monthlyRentIsPositive() {
        assertTrue(property.getMonthlyRent() > 0);
    }

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("Starting Property test suite");
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("Property test suite completed");
    }
}  // end of class
