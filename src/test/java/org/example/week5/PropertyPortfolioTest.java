package org.example.week5;

import org.example.week2.BaseProperty;
import org.example.week2.PropertyPortfolio;
import org.example.week2.ResidentialProperty;
import org.example.week3.InvalidRentException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PropertyPortfolioTest {

    private PropertyPortfolio portfolio;
    private BaseProperty property1;
    private BaseProperty property2;
    private BaseProperty property3;

    @BeforeClass(alwaysRun = true)
    public void beforeClass() {
        System.out.println("Starting PropertyPortfolio test class - TestNG");
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        System.out.println("PropertyPortfolio test class complete - TestNG");
    }

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        portfolio = new PropertyPortfolio("Test Portfolio");
        property1 = new ResidentialProperty("10 Manchester Road", 1000.0, 2, false);
        property2 = new ResidentialProperty("20 Liverpool Street", 1500.0, 3, false);
        property3 = new ResidentialProperty("30 Leeds Lane", 2000.0, 2, false);
        portfolio.addProperty(property1);
        portfolio.addProperty(property2);
        portfolio.addProperty(property3);
    }

    // --------------------------------------------------------------
    // Positive tests (was @Nested PositiveTests in JUnit)
    // --------------------------------------------------------------

    @Test(groups = {"smoke", "portfolio", "positive"},
            description = "addProperty increases portfolio size by one")
    public void addProperty_validProperty_increasesPortfolioSize() {
        // Arrange
        ResidentialProperty newProperty = new ResidentialProperty("40 York Avenue", 1200.0, 2, false);
        int sizeBefore = portfolio.getPropertyCount();

        // Act
        portfolio.addProperty(newProperty);
        int sizeAfter = portfolio.getPropertyCount();

        // Assert
        Assert.assertEquals(sizeAfter, sizeBefore + 1,
                "Adding a property should increase the portfolio size by exactly one");
    }

    @Test(groups = {"regression", "portfolio", "positive"},
            description = "removeProperty decreases portfolio size by one")
    public void removeProperty_existingProperty_decreasePortfolioSize() {
        // Arrange
        int sizeBefore = portfolio.getPropertyCount();

        // Act
        portfolio.removeProperty(property1);
        int sizeAfter = portfolio.getPropertyCount();

        // Assert
        Assert.assertEquals(sizeAfter, sizeBefore - 1,
                "Removing a property should decrease the portfolio by exactly one");
    }

    @Test(groups = {"smoke", "portfolio", "positive"},
            description = "getTotalMonthlyIncome returns correct sum of all rents")
    public void getTotalMonthlyIncome_threeProperties_returnsCorrectTotal() {
        // Arrange
        double expectedTotal = 1000.0 + 1500.0 + 2000.0;

        // Act
        double result = portfolio.getTotalMonthlyIncome();

        // Assert (actual, expected, delta, message)
        Assert.assertEquals(result, expectedTotal, 0.01,
                "Total monthly income should be the sum of all property rents");
    }

    @Test(groups = {"regression", "portfolio", "positive"},
            description = "getTotalAnnualIncome returns twelve times monthly income")
    public void getTotalAnnualIncome_threeProperties_returnsTwelveTimesMonthly() {
        // Arrange
        double expectedAnnual = (1000.0 + 1500.0 + 2000.0) * 12;

        // Act
        double result = portfolio.getTotalAnnualIncome();

        // Assert
        Assert.assertEquals(result, expectedAnnual, 0.01,
                "Annual income should be twelve times the total monthly income");
    }

    @Test(groups = {"regression", "portfolio", "positive"},
            description = "getPropertiesAboveRent returns only properties above threshold")
    public void getPropertiesAboveRent_threshold1200_returnsTwoProperties() {
        // Arrange
        double threshold = 1200.0;

        // Act
        int result = portfolio.getPropertiesAboveRent(threshold).size();

        // Assert
        Assert.assertEquals(result, 2,
                "Only properties with rent above 1200 should be returned");
    }

    @Test(groups = {"regression", "portfolio", "positive"},
            description = "getPropertyCount returns correct number of properties")
    public void getPropertyCount_threePropertiesAdded_returnsThree() {
        // Arrange: portfolio is set up with 3 properties in setUp()

        // Act
        int result = portfolio.getPropertyCount();

        // Assert
        Assert.assertEquals(result, 3,
                "Portfolio should contain exactly 3 properties after setUp");
    }

    // --------------------------------------------------------------
    // Negative tests (was @Nested NegativesTests in JUnit)
    // --------------------------------------------------------------

    @Test(groups = {"smoke", "portfolio", "negative"},
            description = "addProperty with negative rent throws InvalidRentException")
    public void addProperty_negativeRent_throwsInvalidRentException() {
        // Act & Assert
        Assert.assertThrows(InvalidRentException.class,
                () -> portfolio.addProperty(new ResidentialProperty("Bad Property", -500.0, 1, false)));
    }

    @Test(groups = {"regression", "portfolio", "negative"},
            description = "getTotalMonthlyIncome returns zero for empty portfolio")
    public void getTotalMonthlyIncome_emptyPortfolio_returnsZero() {
        // Arrange
        PropertyPortfolio emptyPortfolio = new PropertyPortfolio("Empty Portfolio");

        // Act
        double result = emptyPortfolio.getTotalMonthlyIncome();

        // Assert
        Assert.assertEquals(result, 0.0, 0.01,
                "Empty portfolio should return zero monthly income");
    }

    @Test(groups = {"regression", "portfolio", "negative"},
            description = "getPropertiesAboveRent returns empty list when no properties qualify")
    public void getPropertiesAboveRent_thresholdAboveAllRents_returnsEmptyList() {
        // Arrange
        double threshold = 9999.0;

        // Act
        int result = portfolio.getPropertiesAboveRent(threshold).size();

        // Assert
        Assert.assertEquals(result, 0,
                "No properties should be returned when threshold exceeds all rents");
    }
}
