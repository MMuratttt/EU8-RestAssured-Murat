package com.cydeo.API_Review_EU8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class ZipTestWithHamcrest extends ZipBase{

    @Test
    public void hamcrestPathTest() {

        /*
    Exercise with JsonPath Method
Given Accept application/json
And "city" path is ma/belmont
When I send a GET request to /us endpoint
Then status code must be 200
And content type must be application/json
And Server header is cloudflare
And Report-To header exists
And body should contains following information
   post codes are existing : "02178","02478"
   country  is United States
   state abbreviation is MA
   place name is Belmont
   state is Massachusetts
   latitudes are 42.4464,42.4128

    */

        RestAssured.given().log().all().accept(ContentType.JSON)
                .pathParam("zip", 22031)
                .when().
                get("/{zip}")
                .then().assertThat().statusCode(200)
                .and().contentType("application/json")
                .header("Server", Matchers.equalTo("cloudflare"))
                .header("Report-To",Matchers.notNullValue())
                .body("country",Matchers.is("United States"))
                .body("\'post code\'",Matchers.is("22031"),
                        "places[0].state",Matchers.is("Virginia")
                , "'country abbreviation'", Matchers.equalTo("US")).log().all();







}}
