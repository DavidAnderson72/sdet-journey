package org.example.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;

/**
 * Shared setup for all API tests.
 * Every test class extends this, so the base URL and common settings
 * live in one place instead of being repeated in each test.
 */
public abstract class ApiTestBase {

    protected static RequestSpecification spec;

    @BeforeAll
    static void setUp() {
        // Print the full request and response only when a test fails,
        // so passing runs stay quiet but failures are easy to debug.
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        spec = new RequestSpecBuilder()
                .setBaseUri("https://api.frankfurter.dev")
                .setBasePath("/v2")
                .setAccept(ContentType.JSON)
                .build();
    }
}
