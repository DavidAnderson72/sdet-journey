package org.example.api;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

class ExchangeRateTest extends ApiTestBase {

    @Test
    @DisplayName("EUR to SEK returns 200 and a positive rate")
    void eurToSekReturnsPositiveRate() {
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
}
