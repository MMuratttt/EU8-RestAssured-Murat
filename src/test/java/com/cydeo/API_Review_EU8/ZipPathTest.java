package com.cydeo.API_Review_EU8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ZipPathTest extends ZipBase{

    @Test
    public void pathTest(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("zip",22031)
                .when().
                get("/{zip}");

        response.prettyPrint();

        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("application/json",response.contentType());
        Assertions.assertEquals("cloudflare",response.header("Server"));
        Headers headers = response.getHeaders();

        for (Header header : headers) {
            System.out.println(header.getName() + ": " + header.getValue());
        }

        Assertions.assertTrue(response.headers().hasHeaderWithName("Report-To"));

        System.out.println("--------------------------");

        //PATH

        Assertions.assertEquals("22031",response.path("\'post code\'"));
        Assertions.assertEquals("United States",response.path("country"));

        Assertions.assertEquals("Fairfax", response.path("places.\'place name\'[0]"));
        Assertions.assertEquals("Virginia", response.path("places[0].state"));

        System.out.println("--------------------------");

        //JSONPATH

        JsonPath jsonPathForResponse = response.jsonPath();

        Assertions.assertEquals("United States",jsonPathForResponse.getString("country"));

    }

}
