package com.cydeo.day04;

import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class ORDSApiWithJsonPath extends HRTestBase {

    @DisplayName("GET request to countries")
    @Test
    public void test1() {

        Response response = get("/countries");

        //get the second country name with JsonPath

        JsonPath jsonPath = response.jsonPath();

        System.out.println(jsonPath.get("items[1].country_name").toString());

        //get all country ids
        List<String> allCountryIds = jsonPath.get("items.country_id");
        System.out.println(allCountryIds);

        //get all country names where region id = 2
        List<String> countriesIn2Region = jsonPath.get("items.findAll {it.region_id==2}.country_name");
        System.out.println(countriesIn2Region);

    }

    @DisplayName("GET request /employees with query param")
    @Test
    public void test2() {

        Response response = given().accept(ContentType.JSON).and().queryParam("limit", 107)
                .when().get("/employees");


        //get me all email of employees who is working as IT_PROG
        JsonPath jsonPath = response.jsonPath();

        List<String> itProgs_mail = jsonPath.get("items.findAll {it.job_id==\"IT_PROG\"}.email");
        System.out.println(itProgs_mail);

        List<String> richEmployees = jsonPath.getList("items.findAll {it.salary>10000}.first_name");
        System.out.println(richEmployees);

        String kingName = jsonPath.get("items.max {it.salary}.first_name");
        System.out.println("kingName = " + kingName);

    }
}

