package com.cydeo.day04;

import com.cydeo.utilities.ConfigurationReader;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class CBTrainingApiWithJsonPath {

    @BeforeAll
    public static void init() {
        baseURI = "http://api.cybertektraining.com";
    }

    @DisplayName("GET request to individual student")
    @Test
    public void test1(){

        //send a get request to student id 23401 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /*
                firstName Vera
                batch 14
                section 12
                emailAddress aaa@gmail.com
                companyName Cybertek
                state IL
                zipCode 60606
                using JsonPath
             */

        Response response = given().accept(ContentType.JSON).
                and().pathParam("studentId",32401).
                when().get("/student/{studentId}");

        JsonPath jsonPath = response.jsonPath();

        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("application/json;charset=UTF-8",response.contentType());
        Assertions.assertEquals("gzip",response.getHeader("Content-Encoding"));
        Assertions.assertFalse(response.getHeader("Date").isEmpty());
/*
        String firstName = jsonPath.get("items.findAll {studentId=23401}.firstName");
        String batch = jsonPath.get("items.findAll {studentId=23401}.batch");


*/

        String firstName = jsonPath.getString("students.firstName");
  //    int batch = jsonPath.getInt("students.batch");
        String section = jsonPath.getString("students.section");
        String email = jsonPath.getString("students.contact.emailAddress");
        String companyName = jsonPath.getString("students.company.companyName");
        String state = jsonPath.getString("students.company.address.state");
 //       int zipCode = jsonPath.getInt("students.company.address.zipCode");

        System.out.println("firstName = " + firstName);
 //    System.out.println("batch = " + batch);
        System.out.println("email = " + email);
        System.out.println("section = " + section);
        System.out.println("companyName = " + companyName);
        System.out.println("state = " + state);
   //     System.out.println("zipCode = " + zipCode);








    }



}
