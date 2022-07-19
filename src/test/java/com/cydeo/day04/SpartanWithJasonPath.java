package com.cydeo.day04;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanWithJasonPath extends SpartanTestBase {

    @DisplayName("GET one spartan with JsonPath")
    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                        .and().pathParam("id",10)
                .when().get("/api/spartans/{id}");

        assertEquals("application/json",response.contentType());
        assertEquals(200,response.statusCode());

        //print name with path method
        System.out.println("response.path(\"name\") = " + response.path("name"));

        //assgning response to jsonpath
        JsonPath jsonPath = response.jsonPath();

        int id = jsonPath.getInt("id");
        String name = jsonPath.getString("name");
        String gender = jsonPath.getString("gender");
        long number = jsonPath.getLong("phone");

        System.out.println("number = " + number);
        System.out.println("gender = " + gender);
        System.out.println("name = " + name);
        System.out.println("id = " + id);

    }



}
