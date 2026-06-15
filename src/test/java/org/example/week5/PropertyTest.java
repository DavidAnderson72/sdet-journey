package org.example.week5;

import org.example.week2.Property;
import org.testng.Assert;
import org.testng.annotations.*;

public class PropertyTest {

    private Property property;

        @BeforeSuite(alwaysRun = true)
        public void beforeSuite() {
            System.out.println("Starting Property test suite - TestNG");
        }

        @AfterSuite(alwaysRun = true)
        public void afterSuite() {
            System.out.println("Property test suite completed - TestNG");
        }

        @BeforeClass(alwaysRun = true)
        public void beforeClass() {
            System.out.println("Initialising PropertyTest class");
        }

        @AfterClass(alwaysRun = true)
        public void afterClass() {
            System.out.println("PropertyTest class complete");
        }

        @BeforeMethod(alwaysRun = true)
        public void setUp() {
            property = new Property("10 Manchester Road", 1200.00, 3);
        }

        @AfterMethod(alwaysRun = true)
        public void tearDown() {
            System.out.println("Test completed");
            property = null;
        }

        @Test(groups = {"smoke", "property"})
        public void getMonthlyRent_validProperty_returnsCorrectRent() {
            double result = property.getMonthlyRent();
            Assert.assertEquals(result, 1200.00, "Monthly rent should match the value set in constructor");
        }

        @Test(groups = {"smoke", "property"})
        public void getAddress_validProperty_returnsCorrectAddress() {
            String result = property.getAddress();
            Assert.assertEquals(result, "10 Manchester Road", "Address should match the value set in the constructor");
        }

        @Test(groups = {"regression", "property"})
        public void getAnnualRent_validProperty_returnsCorrectAnnualRent() {
            double result = property.getAnnualRent();
            Assert.assertEquals(result, 14400.00, "Annual rent should bew monthly rent multiplied by 12");
        }

        @Test(groups = {"regression", "property"})
        public void getBedrooms_validProperty_returnsCorrectBedroomCount() {
            int result = property.getBedrooms();
            Assert.assertEquals(result, 3, "Bedroom count should match the value set in constructor");
        }
}
