package com.cydeo.day05;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {

    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1(){

        given()
                .accept(ContentType.JSON)
                .and()
                .pathParam("id",15)
        .when()
                .get("http://44.206.228.3:8000/api/spartans/{id}")
        .then()
                .statusCode(200)
                .and()
                .contentType("application/json")
                .and()
                .body("id",equalTo(15),
                        "name",is("Meta"),
                        "gender",is("Female"),
                        "phone",is(1938695106));
    }

    @DisplayName("CBTraining Teacher request with chaining and matchers")
    @Test
    public void teacherData(){

        given()
                .accept(ContentType.JSON)
                .and().pathParam("id",21921)
        .when()
                .get("http://api.cybertektraining.com/teacher/{id}")
        .then()
                .statusCode(200)
                .and()
                .contentType("application/json;charset=UTF-8")
                .and()
                .header("Content-Length","282")
                .and()
                .header("date",notNullValue())
                .and()
                .body("teachers[0].firstName",is("Andrii"))
                .and()
                .body("teachers[0].gender",equalTo("Male"));

    }

    @DisplayName("GET request to teacher/all and chaining")
    @Test
    public void teachersTest(){


        given()
                .accept(ContentType.JSON).
        when()
                .get("http://api.cybertektraining.com/teacher/all").
        then()
                .statusCode(200)
                .body("teachers.firstName",hasItems("Leoneli","Andrii","Feddy"));


    }



}
