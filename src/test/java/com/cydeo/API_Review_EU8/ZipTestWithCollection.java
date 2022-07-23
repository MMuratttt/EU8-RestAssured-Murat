package com.cydeo.API_Review_EU8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ZipTestWithCollection extends ZipBase {

    @Test
    public void collectionTest(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("zip",22031)
                .when().
                get("/{zip}");

        response.prettyPrint();

      // System.out.println(response.asString());

        Map<String,Object> postCode = response.as(Map.class);
        System.out.println("postCode.get(\"post code\") = " + postCode.get("post code"));

        assertEquals("United States",postCode.get("country"));

        List<Map<String,Object>> places = (List<Map<String, Object>>) postCode.get("places");

        assertEquals("Virginia", places.get(0).get("state"));
        assertEquals("Fairfax", places.get(0).get("place name"));

    }}
