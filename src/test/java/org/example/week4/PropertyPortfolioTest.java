package org.example.week4;

import org.example.week2.BaseProperty;
import org.example.week2.PropertyPortfolio;
import org.example.week2.ResidentialProperty;
import org.example.week3.InvalidRentException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("PropertyPortfolio Tests")
class PropertyPortfolioTest {

    PropertyPortfolio portfolio;
    BaseProperty property1;
    BaseProperty property2;
    BaseProperty property3;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Starting PropertyPortfolio test suite");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("PropertyPortfolio test suite complete");
    }

    @BeforeEach
    void setUp() {
        portfolio = new PropertyPortfolio("Test Portfolio");
        property1 = new ResidentialProperty("10 Manchester Road", 1000.0, 2, false);
        property2 = new ResidentialProperty("20 Liverpool Street", 1500.0, 3, false);
        property3 = new ResidentialProperty("30 Leeds Lane", 2000.0, 2, false);
        portfolio.addProperty(property1);
        portfolio.addProperty(property2);
        portfolio.addProperty(property3);
    }

    // --------------------------------------------------------------
    // @Nested - Positive Tests
    // --------------------------------------------------------------
    @Nested
    @DisplayName("Positive Tests - expected behaviour")
    class PositiveTests {

        @Test
        @DisplayName("addProperty increases portfolio size by one")
        void addProperty_validProperty_increasesPortfolioSize() {
            // Arrange
            ResidentialProperty newProperty = new ResidentialProperty("40 York Avenue", 1200.0, 2, false);
            int sizeBefore = portfolio.getPropertyCount();

            // Act
            portfolio.addProperty(newProperty);
            int sizeAfter = portfolio.getPropertyCount();

            // Assert
            assertEquals(sizeBefore + 1, sizeAfter,
                    "Adding a property should increase the portfolio size by exactly one");
        }

        @Test
        @DisplayName("removeProperty decreases portfolio size by one")
        void removeProperty_existingProperty_decreasePortfolioSize() {
            // Arrange
            int sizeBefore = portfolio.getPropertyCount();

            // Act
            portfolio.removeProperty(property1);
            int sizeAfter = portfolio.getPropertyCount();

            // Assert
            assertEquals(sizeBefore - 1, sizeAfter,
                    "Removing a property should decrease the portfolio by exactly one");
        }

        @Test
        @DisplayName("getTotalMonthlyIncome returns correct sum of all rents")
        void getTotalMonthlyIncome_threeProperties_returnsCorrectTotal() {
            // Arrange
            double expectedTotal = 1000.0 + 1500.0 + 2000.0;

            // Act
            double result = portfolio.getTotalMonthlyIncome();

            // Assert
            assertEquals(expectedTotal, result, 0.01,
                    "Total monthly income should be the sum of all property rents");
        }

        @Test
        @DisplayName("getTotalAnnualIncome returns twelve times monthly income")
        void getTotalAnnualIncome_threeProperties_returnsTwelveTimesMonthly() {
            // Arrange
            double expectedAnnual = (1000.0 + 1500.0 + 2000.0) * 12;

            // Act
            double result = portfolio.getTotalAnnualIncome();

            // Assert
            assertEquals(expectedAnnual, result, 0.01,
                    "Annual income should be twelve times the total monthly income");
        }

        @Test
        @DisplayName("getPropertiesAboveRent returns only properties above threshold")
        void getPropertiesAboveRent_threshold1200_returnsTwoProperties() {
            // Arrange
            double threshold = 1200.0;

            // Act
            int result = portfolio.getPropertiesAboveRent(threshold).size();

            // Assert
            assertEquals(2, result,
                    "Only properties with rent above 1200 should be returned");
        }

        @Test
        @DisplayName("getPropertyCount returns correct number of properties")
        void getPropertyCount_threePropertiesAdded_returnsThree() {
            // Arrange
            // portfolio set up with 3 properties in @BeforeEach

            // Act
            int result = portfolio.getPropertyCount();

            // Assert
            assertEquals(3, result,
                    "Portfolio should contain exactly 3 properties after setUp");
        }

    }

    //----------------------------------------------------------------
    // @Nested - Negative Tests
    //----------------------------------------------------------------
    @Nested
    @DisplayName("Negative Tests - exception and edge case behaviour")
    class NegativesTests {

        @Test
        @DisplayName("addProperty with negative rent throws InvalidRentException")
        void addProperty_negativeTest_throwsInvalidRentException() {
            // Arrange
            // Act & Assert
            assertThrows(InvalidRentException.class,
                    () -> portfolio.addProperty(new ResidentialProperty("Bad Property", -500.0, 1, false)),
                    "Adding a property with negative rent should throw InvalidRentException");
        }

        @Test
        @DisplayName("getTotalMonthlyIncome returns zero for empty portfolio")
        void getTotalMonthlyIncome_emptyPortfolio_returnsZero() {
            // Arrange
            PropertyPortfolio emptyPortfolio = new PropertyPortfolio("Empty Portfolio");

            // Act
            double result = emptyPortfolio.getTotalMonthlyIncome();

            //Assert
            assertEquals(0.0, result, 0.01,
                    "Empty portfolio should return zero monthly income");
        }

        @Test
        @DisplayName("getPropertiesAboveRent returns empty list when no properties qualify")
        void getPropertiesAboveRent_thresholdAboveAllRents_returnsEmptyList() {
            // Arrange
            double threshold = 9999.0;

            // Act
            int result = portfolio.getPropertiesAboveRent(threshold).size();

            // Assert
            assertEquals(0, result,
                    "No properties should be returned when threshold exceeds all rents");
        }

    }


}
