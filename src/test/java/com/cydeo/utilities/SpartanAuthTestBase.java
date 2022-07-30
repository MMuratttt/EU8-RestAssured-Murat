package com.cydeo.utilities;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public class SpartanAuthTestBase {

    @BeforeAll
    public static void init() {
        baseURI = "http://44.206.228.3:7000";

    }
}
