package org.example.week4;

import org.example.week1.RentalCalculator;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

@DisplayName("Tax Calculator Tests")
class TaxCalculatorTest {

    @BeforeAll
    static void beforeAll() {
        System.out.println("Starting Tax Calculator test suite");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Tax Calculator test suite complete");
    }

    // -------------------------------------------------------
    // @Nested - Personal Allowance Band (0% tax)
    // -------------------------------------------------------
    @Nested
    @DisplayName("Personal Allowance Band - 0% tax")
    class PersonalAllowanceBandTests {

        @Test
        @DisplayName("calculateTax returns zero for income under personal allowance")
        void calculateTax_incomePersonalAllowance_returnsZero() {
            // Arrange
            RentalCalculator calc = new RentalCalculator(833.33, 0, 0);

            // Act
            double result = calc.calculateTax();

            // Assert
            assertEquals(0.0, result, 0.01,
                    "Monthly rent of 833.33 produces annual income under 12570 so tax should be zero");
        }

        @Test
        @DisplayName("calculateTax returns zero for income exactly at personal allowance")
        void calculateTax_incomeAtPersonalAllowance_returnsZero() {
            // Arrange
            RentalCalculator calc = new RentalCalculator(1047.50, 0, 0);

            // Act
            double result = calc.calculateTax();

            // Assert
            assertEquals(0.0, result, 0.01,
                    "Income at exactly 12570 should produce no tax");
        }
    }

    // -------------------------------------------------------------------
    // @Nested - Basic Rate Band (20%)
    // -------------------------------------------------------------------
    @Nested
    @DisplayName("Basic Rate Band - 20% tax")
    class BasicRateBandTests {
        @ParameterizedTest(name = "Monthly rent £{0} should produce tax of £{1}")
        @DisplayName("calculateTax returns correct 20% tax for various rents in basic rate band")
        @CsvSource({
                "1500.0, 1086.0",
                "2000.0, 2286.0",
                "2500.0, 3486.0",
                "3000.0, 4686.0",
                "3500.0, 5886.0"
        })
        void calculateTax_rentInBasicRateBand_returnsCorrectTax(double monthlyRent, double expectedTax) {
            // Arrange
            RentalCalculator calc = new RentalCalculator(monthlyRent, 0, 0);

            // Act
            double result = calc.calculateTax();

            // Assert
            assertEquals(expectedTax, result, 1.0,
                    "Tax should be 20% of income above personal allowance");
        }
    }

    // --------------------------------------------------------------------
    // @Nested - Higher Rate Band (40% tax)
    // --------------------------------------------------------------------
    @Nested
    @DisplayName("Higher Rate Band - 40% tax")
    class HigherRateBandTests {

        @ParameterizedTest(name = "Monthly rent £{0} should produce tax over 3000")
        @DisplayName("calculateTax returns tax over 3000 for rents in higher rate band")
        @ValueSource(doubles = {5000.0, 6000.0, 7000.0, 8000.0, 9000.0})
        void calculateTax_rentInHigherRateBand_returnsTaxOver3000(double monthlyRent) {
            // Arrange
            RentalCalculator calc = new RentalCalculator(monthlyRent, 0, 0);

            // Act
            double result = calc.calculateTax();

            // Assert
        assertTrue(result >3000,
                "Annual income over 50270 should produce tax over 3000");
        }
    }

    // --------------------------------------------------------------------
    // @MethodSource - supplying complex test data
    // --------------------------------------------------------------------
    @ParameterizedTest(name = "Rent £{0} with expenses £{1} should produce tax £{2}")
    @DisplayName("calculateTax correctly reduces tax when expenses are factored in")
    @MethodSource("rentAndExpensesProvider")
    void calculateTax_rentWithExpenses_returnsReducedTax(double monthlyRent, double monthlyExpenses, double expectedTax) {
        // Arrange
        RentalCalculator calc = new RentalCalculator(monthlyRent, monthlyExpenses, 0);

        // Act
        double result = calc.calculateTax();

        // Assert
        assertEquals(expectedTax, result, 1.0,
                "Expenses should reduce taxable income and therefore reduce tax");
    }

    static Stream<org.junit.jupiter.params.provider.Arguments> rentAndExpensesProvider() {
        return Stream.of(
                org.junit.jupiter.params.provider.Arguments.of(2000.0, 200.0, 1806.0),
                org.junit.jupiter.params.provider.Arguments.of(2000.0, 500.0, 1086.0),
                org.junit.jupiter.params.provider.Arguments.of(2000.0, 1000.0, 0.0)
        );
    }

    // -------------------------------------------------------------------
    // @RepeatedTest
    // -------------------------------------------------------------------
    @RepeatedTest(3)
    @DisplayName("calculateTax produces consistent results across repeated runs")
    void calculateTax_sameInputRepeated_returnsConsistentResult() {

        // Arrange
        RentalCalculator calc = new RentalCalculator(2000.0, 0, 0);

        // Act
        double result = calc.calculateTax();

        // Assert
        assertEquals(2286.0, result, 1.0,
                "Same inputs should always produce same tax output");
    }

    // -------------------------------------------------------------------
    // assumeTrue / assumeFalse
    // -------------------------------------------------------------------
    @Test
    @DisplayName("calculateTax is skipped when income produces no tax")
    void calculateTax_incomeBelowPersonalAllowance_testSkipped() {
        // Arrange
        RentalCalculator calc = new RentalCalculator(500.0, 0, 0);

        // Act
        double result = calc.calculateTax();

        // Assume & Assert
        assumeTrue(result > 0,
        "Skipping test - income is below personal allowance, no tax due");
        assertTrue(result > 0,
                "Tax should be positive if income exceeds personal allowance");
    }

    // ------------------------------------------------------------------
    // @Disabled
    // ------------------------------------------------------------------
    @Test
    @Disabled("Additional rate band not yet implemented in RentalCalculator - revisit week 6")
    @DisplayName("calculateTax returns correct tax for income in additional rate band")
    void calculateTax_incomeInAdditionalRateBand_returnsCorrectTax() {
        // Arrange
        RentalCalculator calc = new RentalCalculator(15000.0, 0, 0);

        // Act
        double result = calc.calculateTax();

        // Assert
        assertTrue(result > 40000,
                "Income in additional rate band should produce tax over 40000");
    }

}
