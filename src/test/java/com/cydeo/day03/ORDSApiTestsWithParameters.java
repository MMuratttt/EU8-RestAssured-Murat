package com.cydeo.day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestsWithParameters {

    @BeforeAll
    public static void init() {
        baseURI = "http://44.206.228.3:1000/ords/hr";
    }

     /*
        Given accept type is Json
        And parameters: q = {"region_id":2}
        When users sends a GET request to "/countries"
        Then status code is 200
        And Content type is application/json
        And Payload should contain "United States of America"
     */

    @DisplayName("GET request to /countries with Query Param")
    @Test
    public void test1(){

        Response response = given()
                .log().all()
                                .accept(ContentType.JSON)
                            .and()
                                .queryParam("q","{\"region_id\":2}")
                            .when()
                                .get("/countries");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.header("Content-Type"));
        assertTrue(response.body().asString().contains("United States of America"));

        response.prettyPrint();

    }

     /*
        Send a GET request to employees and get only employees who works as a IT_PROG
     */

    @DisplayName("GET request to /employees with Query Param")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
        .queryParam("q","{\"job_id\": \"IT_PROG\"}")
                .get("/employees");

        response.prettyPrint();

    }


}
