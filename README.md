# sdet-journey

[![API Tests](https://github.com/DavidAnderson72/sdet-journey/actions/workflows/api-tests.yml/badge.svg)](https://github.com/DavidAnderson72/sdet-journey/actions/workflows/api-tests.yml)

A self-directed learning project documenting my move from manual QA into test automation (SDET), built in Java with Maven.

## API test suite

An automated regression suite for the [Frankfurter](https://frankfurter.dev) foreign exchange rates API, written with **REST Assured** and **JUnit 5** and run on every push by **GitHub Actions**.

### What it covers

| Area | Tests |
|---|---|
| Happy path | Status code, content type and response fields for EUR → SEK |
| Data-driven | One parameterized test checking EUR against SEK, GBP, USD and NOK |
| Historical data | A request for a past date returns that date's rate |
| Performance | Responses must arrive within 5 seconds |
| Contract | Responses are validated against a JSON schema (required fields, currency format, positive rate) |
| Negative | Unknown currency codes and malformed dates return 422 with an error message |

### Design choices

- **Shared request setup.** A base class holds the base URL and common settings, so each test only contains what is specific to it.
- **No exact rate values.** Rates change daily and the API blends several central bank sources, so tests assert on structure and valid ranges rather than exact numbers.
- **Logging only on failure.** The full request and response are printed only when a test fails, keeping passing runs readable.
- **Separate Maven profiles.** The project also contains TestNG exercises. Each test framework has its own Surefire profile with an explicitly configured provider, so neither framework's tests are silently skipped.

### Running the tests

```
mvn clean test -Papi
```

Requires Java 17 and Maven. The default profile (`mvn clean test`) runs the TestNG suite instead.

### Known limitation

The suite tests a live public API, so a CI run can fail because the API is unavailable rather than because of a defect. A planned improvement is to add mocked responses (e.g. WireMock) for deterministic runs alongside the live checks.

## Project structure

| Package | Contents |
|---|---|
| `org.example.week1`–`week3` | Java fundamentals: OOP, collections, exceptions |
| `org.example.week4` | JUnit 5 unit tests: parameterized, nested and grouped tests |
| `org.example.week5` | TestNG: suites, groups and XML configuration |
| `org.example.api` | REST Assured API test suite (above) |

## Tech stack

Java 17 · Maven · JUnit 5 · TestNG · REST Assured · JSON Schema Validator · GitHub Actions · Git
