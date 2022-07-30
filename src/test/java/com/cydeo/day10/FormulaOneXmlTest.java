package com.cydeo.day10;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class FormulaOneXmlTest {

    @BeforeAll
    public static void setup(){

        //http://ergast.com/api/f1/drivers/alonso
        RestAssured.baseURI = "http://ergast.com";
        RestAssured.basePath = "/api/f1";

    }

    @Test
    public void test1(){

        Response response = RestAssured.given().pathParam("driver", "alonso")
                .get("drivers/{driver}")
                .then().statusCode(200).log().all()
                .extract().response();

        XmlPath xmlPath = response.xmlPath();

        Assertions.assertEquals(xmlPath.getString("MRData.DriverTable.Driver.GivenName"),"Fernando");
        Assertions.assertEquals(xmlPath.getString("MRData.DriverTable.Driver.FamilyName"),"Alonso");

        String driverId = xmlPath.getString("MRData.DriverTable.Driver.@driverId"); //'@' gives value of attribute
        Assertions.assertEquals("alonso",driverId);

        Assertions.assertEquals("ALO",xmlPath.getString("MRData.DriverTable.Driver.@code"));
        Assertions.assertEquals("http://en.wikipedia.org/wiki/Fernando_Alonso",xmlPath.getString("MRData.DriverTable.Driver.@url"));



    }



}
