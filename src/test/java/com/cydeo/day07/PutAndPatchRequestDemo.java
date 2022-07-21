package com.cydeo.day07;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

public class PutAndPatchRequestDemo extends SpartanTestBase {

    @Test
    public void PUTRequest() {

        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("name", "BruceWayne");
        putRequestMap.put("gender", "Male");
        putRequestMap.put("phone", 468725465872L);

        RestAssured.given()
                .and().contentType(ContentType.JSON)
                .body(putRequestMap)
                .and().pathParam("id", 120)
                .when().put("/api/spartans/{id}")
                .then()
                .statusCode(204);


    }

    @Test
    public void PATCHRequest() {

        Map<String, Object> putRequestMap = new LinkedHashMap<>();
        putRequestMap.put("phone", 468725465872L);

        RestAssured.given()
                .and().contentType(ContentType.JSON)
                .body(putRequestMap)
                .and().pathParam("id", 120)
                .when().patch("/api/spartans/{id}")
                .then()
                .statusCode(204);

    }

    @Test
    public void DELETESpartan() {
        int idToDelete = 120;

        RestAssured.given().pathParam("id",idToDelete)
                .when().delete("/api/spartans/{id}")
                .then().statusCode(204);



    }
}