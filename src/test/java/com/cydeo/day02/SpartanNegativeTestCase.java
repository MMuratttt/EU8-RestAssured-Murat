package com.cydeo.day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanNegativeTestCase {

    @BeforeAll
    public static void init() {
        baseURI = "http://44.206.228.3:8000";
    }

    @Test
    public void test1(){

        Response response = given().
                                accept(ContentType.XML)
                            .when()
                                .get("/api/spartans/10");

        System.out.println("response.statusCode() = " + response.statusCode());

        assertEquals(406,response.statusCode());
        assertEquals("application/xml;charset=UTF-8",response.contentType());

    }

}
