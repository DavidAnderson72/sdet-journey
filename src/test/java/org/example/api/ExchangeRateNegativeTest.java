package org.example.api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsStringIgnoringCase;
import static org.hamcrest.Matchers.notNullValue;

/**
 * Negative tests: invalid input should produce a clear error, not a 200.
 */
class ExchangeRateNegativeTest extends ApiTestBase {

    @Test
    @DisplayName("Unknown currency code returns 422 with an error message")
    void getRate_unknownCurrency_returns422() {
        given()
            .spec(spec)
        .when()
            .get("/rate/eur/xyz")
        .then()
            .statusCode(422)
            .contentType(ContentType.JSON)
            .body("message", containsStringIgnoringCase("xyz"));
    }

    @Test
    @DisplayName("Malformed date returns 422 with an error message")
    void getRate_malformedDate_returns422() {
        given()
            .spec(spec)
            .queryParam("date", "not-a-date")
        .when()
            .get("/rate/eur/sek")
        .then()
            .statusCode(422)
            .contentType(ContentType.JSON)
            .body("message", notNullValue());
    }
}
