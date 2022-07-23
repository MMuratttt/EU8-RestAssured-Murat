package com.cydeo.API_Review_EU8;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class ZipBase {

    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "https://api.zippopotam.us";
        RestAssured.basePath = "/us";

    }

}
