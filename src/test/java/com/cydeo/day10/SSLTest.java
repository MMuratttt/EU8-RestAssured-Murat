package com.cydeo.day10;

import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

public class SSLTest {

    @Test
    public void test1(){

        RestAssured.given().relaxedHTTPSValidation() //even if it doesnt have valid certificate
        .when()
                .get("http://ergast.com/api/f1/drivers/alonso")
                .prettyPrint();

    }

    @Test
    public void keyStore(){

        RestAssured.given()
                .keyStore("pathtofile","password")
                .when().get("apiurl");

    }

}
