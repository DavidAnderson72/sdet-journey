package org.example.api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;

/**
 * Positive tests for the /rate/{base}/{quote} endpoint.
 */
class ExchangeRateTest extends ApiTestBase {

    @Test
    @DisplayName("EUR to SEK returns 200 and a positive rate")
    void getRate_eurToSek_returnsPositiveRate() {
        given()
            .spec(spec)
        .when()
            .get("/rate/eur/sek")
        .then()
            .statusCode(200)
            .contentType(ContentType.JSON)
            .body("base", equalTo("EUR"))
            .body("quote", equalTo("SEK"))
            .body("rate", greaterThan(0f));
    }

    @Test
    @DisplayName("Rate request responds within 5 seconds")
    void getRate_eurToSek_respondsWithinFiveSeconds() {
        given()
            .spec(spec)
        .when()
            .get("/rate/eur/sek")
        .then()
            .statusCode(200)
            .time(lessThan(5L), TimeUnit.SECONDS);
    }

    @ParameterizedTest(name = "EUR to {0} returns a positive rate")
    @ValueSource(strings = {"SEK", "GBP", "USD", "NOK"})
    void getRate_eurToQuoteCurrency_returnsPositiveRate(String quoteCurrency) {
        given()
            .spec(spec)
            .pathParam("quote", quoteCurrency)
        .when()
            .get("/rate/eur/{quote}")
        .then()
            .statusCode(200)
            .body("quote", equalTo(quoteCurrency))
            .body("rate", greaterThan(0f));
    }

    @Test
    @DisplayName("Historical date returns the rate for that date")
    void getRate_withHistoricalDate_returnsRequestedDate() {
        given()
            .spec(spec)
            .queryParam("date", "2024-05-15")
        .when()
            .get("/rate/eur/sek")
        .then()
            .statusCode(200)
            .body("date", equalTo("2024-05-15"))
            .body("rate", greaterThan(0f));
    }

    @Test
    @DisplayName("Rate response matches the JSON schema contract")
    void getRate_eurToSek_matchesJsonSchema() {
        given()
            .spec(spec)
        .when()
            .get("/rate/eur/sek")
        .then()
            .statusCode(200)
            .body(matchesJsonSchemaInClasspath("schemas/rate-schema.json"));
    }
}
