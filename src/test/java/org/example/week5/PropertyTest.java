package org.example.week5;

import org.example.week2.Property;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
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

        @Test(groups = {"smoke", "property"}, dependsOnMethods = {"getAddress_validProperty_returnsCorrectAddress"})
        public void getAddress_dependencyExample_skipsIfAddressFails() {
        String result = property.getAddress();
        Assert.assertEquals(result, "10 Manchester Road", "Dependent test - only runs if address test passed");
        }

        @Test(groups = {"regression", "property"})
        public void getAnnualRent_dependsOnSmoke_skipsIfSmokeFails() {
        double result = property.getAnnualRent();
        Assert.assertEquals(result, 14400.00, "Only runs if all smoke tests passed");
    }

        @Test(groups = {"smoke", "property"},
            threadPoolSize = 3,
            invocationCount = 6)
        public void getMonthlyRent_stressTest_alwaysReturnsCorrectRent() {
        double result = property.getMonthlyRent();
        Assert.assertEquals(result, 1200.00, "Monthly rent should be consistent across parallel invocations");
    }

    @Test(groups = {"smoke", "property"})
    @Parameters("expectedRent")
    public void getMonthlyRent_fromXmlParameter_returnsExpectedRent(double expectedRent) {
        double result = property.getMonthlyRent();
        Assert.assertEquals(result, expectedRent, "Rent should match the value passed from testng.xml");
    }

    @Test(groups = {"regression", "property"})
    public void getProperty_allFields_returnCorrectValues() {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(property.getAddress(),"10 Manchester Road", "Address should match");
        softAssert.assertEquals(property.getMonthlyRent(),1200.00, "Rent amount should match");
        softAssert.assertEquals(property.getBedrooms(),3, "Number of bedrooms should match");

        softAssert.assertAll();
    }
}
