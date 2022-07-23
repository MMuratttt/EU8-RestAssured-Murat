package com.cydeo.API_Review_EU8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZipJsonPathTest extends ZipBase {

    @Test
    public void JsonPathTest() {

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

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParams("state", "ma", "city", "belmont")
                .when().
                get("/{state}/{city}");

        response.prettyPrint();

        JsonPath jsonPath = response.jsonPath();

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());

        assertEquals("cloudflare",response.header("Server"));
        assertTrue(response.headers().hasHeaderWithName("Report-To"));

        assertEquals("United States",jsonPath.getString("country"));
        assertEquals("MA",jsonPath.getString("\'state abbreviation\'"));

        List<String> expectedZips = new ArrayList<>(Arrays.asList("02178","02478"));
        List<String> actualZips = jsonPath.getList("places.\'post code\'");
        assertEquals(expectedZips,actualZips);

        String expectedLatitude = "[42.4464]";
        String actualLatitude = jsonPath.getString("places.findAll {it.\'post code\'==\"02178\"}.latitude");

        assertEquals(expectedLatitude,actualLatitude);

    }
}