package com.cydeo.day10;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.common.input.LineSeparatorDetector;

import java.util.List;

import static io.restassured.RestAssured.*;

public class SpartanWithXml extends SpartanTestBase {

    @DisplayName("GET request to /api/spartans and verify xml")
    @Test
    public void getSpartanXml(){

        given().accept(ContentType.XML)
                .and().auth().basic("admin","admin")
                .get("/api/spartans")
                .then().statusCode(200)
                .contentType(ContentType.XML)
                .body("List.item[0].name", Matchers.is("Meade"))
                .body("List.item[0].gender", Matchers.is("Male"))
                .log().all();


    }

    @DisplayName("GET request to /api/spartans with xmlPath")
    @Test
    public void testXmlPath(){

        Response response = given().accept(ContentType.XML)
                .and().auth().basic("admin", "admin")
                .get("/api/spartans");

        XmlPath xmlPath = response.xmlPath();

        xmlPath.getString("List.item[0].name");

        xmlPath.getInt("List.item[2].id");

        List<String> names = xmlPath.getList("List.item.name");

        System.out.println(names);

    }
}
