package com.cydeo.day05;

import com.cydeo.utilities.HRTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class ORDSHamcrestTest extends HRTestBase {

    @DisplayName("GET request to employees IT_PROG endpoint and chaining")
    @Test
    public void regionTest() {

        List<String> names = Arrays.asList("Alexander", "Bruce", "David", "Valli", "Diana");

        RestAssured.given()
                .accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .body("items.job_id", Matchers.everyItem(Matchers.equalTo("IT_PROG")))
                .and()
                .body("items.first_name", Matchers.containsInAnyOrder("Diana", "Alexander", "Bruce", "David", "Valli"))
                .and()
                .body("items.email", containsInRelativeOrder("AHUNOLD", "BERNST", "DAUSTIN", "VPATABAL", "DLORENTZ"))
                .and()
                .body("items.first_name", Matchers.equalToObject(names)); // also we can use equalTo()


    }

    @Test
    public void employeesTest2() {

        Response response = RestAssured.given()
                .accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().response();

        response.prettyPrint();

        JsonPath jsonPath = RestAssured.given()
                .accept(ContentType.JSON)
                .and().queryParam("q", "{\"job_id\": \"IT_PROG\"}")
                .when()
                .get("/employees")
                .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .body("items.job_id", everyItem(equalTo("IT_PROG")))
                .extract().jsonPath();

        assertThat(jsonPath.getList("items.first_name"), hasSize(5));



    }

}
