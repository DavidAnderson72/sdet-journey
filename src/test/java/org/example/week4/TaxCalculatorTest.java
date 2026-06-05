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
        @DisplayName("Annual income of 10000 should produce zero tax")
        void zeroTaxForIncomeUnderPersonalAllowance() {
            // Monthly rent of 833.33 = -10000 annually, no expenses
            RentalCalculator calc = new RentalCalculator(833.33, 0, 0);
            assertEquals(0.0, calc.calculateTax(), 0.01,
                    "Income under personal allowance should produce no tax");
        }

        @Test
        @DisplayName("Incomeexactly at personal allowance should produce zero tax")
        void zeroTaxExactPersonalAllowance() {
            RentalCalculator calc = new RentalCalculator(1047.50, 0, 0);
            assertEquals(0.0, calc.calculateTax(), 0.01,
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
        @DisplayName("Multiple rent amounts in basic rate band")
        @CsvSource({
                "1500.0, 1086.0",
                "2000.0, 2286.0",
                "2500.0, 3486.0",
                "3000.0, 4686.0",
                "3500.0, 5886.0"
        })
        void basicRateTaxForVariousRents(double monthlyRent, double expectedTax) {
            RentalCalculator calc = new RentalCalculator(monthlyRent, 0, 0);
            assertEquals(expectedTax, calc.calculateTax(), 1.0,
                    "Tax should be 20% of income above personal allowance");
        }
    }

    // --------------------------------------------------------------------
    // @Nested - Higher Rate Band (40% tax)
    // --------------------------------------------------------------------
    @Nested
    @DisplayName("Higher Rate Band - 40% tax")
    class HigherRateBandTests {

        @ParameterizedTest(name = "Monthly rent £{0} should be in higher rate band")
        @ValueSource(doubles = {5000.0, 6000.0, 7000.0, 8000.0, 9000.0})
        @DisplayName("High rent amounts should fall in higher rate band and produce tax over 3000")
        void higherRateBandForLargeRents(double monthlyRent) {
            RentalCalculator calc = new RentalCalculator(monthlyRent, 0, 0);
        assertTrue(calc.calculateTax() >3000,
                "Annual income over 50270 should produce tax over 15000");
        }
    }

    // --------------------------------------------------------------------
    // @MethodSource - supplying complex test data
    // --------------------------------------------------------------------
    @ParameterizedTest(name = "Rent £{0} with expenses £{1} should produce tax £{2}")
    @DisplayName("Tax calculations with expenses factrored in")
    @MethodSource("rentAndExpensesProvider")
    void taxWithExpenses(double monthlyRent, double monthlyExpenses, double expectedTax) {
        RentalCalculator calc = new RentalCalculator(monthlyRent, monthlyExpenses, 0);
        assertEquals(expectedTax, calc.calculateTax(), 1.0,
                "Expenses should reduce taxable income");
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
    @DisplayName("Tax calculation should be consistent across repeated runs")
    void taxCalculationIsConsistent() {
        RentalCalculator calc = new RentalCalculator(2000.0, 0, 0);
        assertEquals(2286.0, calc.calculateTax(), 1.0,
                "Same inputs should always produce same tax output");
    }

    // -------------------------------------------------------------------
    // assumeTrue / assumeFalse
    // -------------------------------------------------------------------
    @Test
    @DisplayName("Skip test if income is below personal allowance")
    void onlyRunIfIncomeTaxable() {
        RentalCalculator calc = new RentalCalculator(500.0, 0, 0);
        assumeTrue(calc.calculateTax() > 0,
        "Skipping test - income is below personal allowance, no tax due");
        assertTrue(calc.calculateTax() > 0);
    }

    // ------------------------------------------------------------------
    // @Disabled
    // ------------------------------------------------------------------
    @Test@Disabled("Additional rate band not yet implemented in RentalCalculator - revisit week 6")
    @DisplayName("Additional rate band at 45 percent")
    void additionalRateBandTest() {
        RentalCalculator calc = new RentalCalculator(15000.0, 0, 0);
    }

}
