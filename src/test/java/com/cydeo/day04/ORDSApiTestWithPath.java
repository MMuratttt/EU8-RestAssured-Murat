package com.cydeo.day04;

import com.cydeo.utilities.ConfigurationReader;
import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiTestWithPath extends HRTestBase {

    @DisplayName("GET request to countries with Path method")
    @Test
    public void test1(){

        Response response = given()
                .accept(ContentType.JSON)
                .and().queryParam("q","{\"region_id\":2}")
                .when().get("/countries");

        assertEquals(200,response.statusCode());

        //print limit result
        System.out.println("response.path(\"limit\") = " + response.path("limit"));

        //print hasMore
        System.out.println("response.path(\"hasMore\") = " + response.path("hasMore"));

        //first country_id
        System.out.println("response.path(\"items[0].country_id\") = " + response.path("items[0].country_id"));

        //print second country name
        System.out.println("response.path(\"items[1].country_name\") = " + response.path("items[1].country_name"));


        System.out.println("response.path(\"items[2].links.href\") = " + response.path("items[2].links.href"));

        System.out.println("response.path(\"items.country_name\") = " + response.path("items.country_name"));

        //assert that all regions ids are equal to 2
        List<Integer> path = response.path("items.region_id");
        for(Integer each : path){
            assertEquals(2,each);
        }

    }

    @DisplayName("GET each name of IT_PROGs")
    @Test
    public void test2(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("q","{\"job_id\": \"IT_PROG\"}")
                .and().get("/employees");


        System.out.println("response.path(\"items.job_id\") = " + response.path("items.job_id"));

        System.out.println("response.path(\"items.first_name\") = " + response.path("items.first_name"));

    }


}
