package com.cydeo.day11;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class CsvSourceParameterizedTest {

    // Test first number + second number = third number
//            1, 3 , 4
//            2, 3 , 5
//            8, 7 , 15
//            10, 9 , 19


    @ParameterizedTest
    @CsvSource({"1, 3 , 4",
                 "2, 3 , 5",
                 "8, 7 , 15",
                 "10, 9 , 19"})
    public void testAddition(int num1, int num2, int sum){

        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("sum = " + sum);

        MatcherAssert.assertThat((num1+num2), Matchers.equalTo(sum));

    }

    // Write a parameterized test for this request
    // GET https://api.zippopotam.us/us/{state}/{city}
    /*
        "NY, New York",
        "CO, Denver",
        "VA, Fairfax",
        "VA, Arlington",
        "MA, Boston",
        "NY, New York",
        "MD, Annapolis"
     */
    //verify place name contains your city name
    //print number of places for each request

    @ParameterizedTest
    @CsvSource({"NY, New York",
            "CO, Denver",
            "VA, Fairfax",
            "VA, Arlington",
            "MA, Boston",
            "NY, New York",
            "MD, Annapolis"})
    @Test
    public void test3(String state, String city){

        int placeNumber = RestAssured.given().baseUri("https://api.zippopotam.us")
                .pathParam("state", state)
                .pathParam("city", city)
                .when()
                .get("/us/{state}/{city}").
                then().statusCode(200)
                .body("places.'place name'", everyItem(containsStringIgnoringCase(city)))
               // .log().body()
                .extract().jsonPath().getList("places").size();


    }

}
