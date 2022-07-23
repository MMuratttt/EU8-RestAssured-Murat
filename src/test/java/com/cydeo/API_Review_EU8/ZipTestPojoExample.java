package com.cydeo.API_Review_EU8;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class ZipTestPojoExample extends ZipBase {

    @Test
    public void pojoTest() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("zip", 22031)
                .when().
                get("/{zip}");

        response.prettyPrint();

        PostCodePOJO zip22031 = response.as(PostCodePOJO.class);

        System.out.println("zip22031.getCountry() = " + zip22031.getCountry());

        System.out.println("zip22031.getPlaces().get(0).getState() = " + zip22031.getPlaces().get(0).getState());

        Assertions.assertEquals("Virginia", zip22031.getPlaces().get(0).getState());
    }

        @Test
        public void Test() {

        String s = "gold";
            System.out.println(s.substring(1, 4));
        }
}