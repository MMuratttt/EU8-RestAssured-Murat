package com.cydeo.day03;

import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithParameters {

    @BeforeAll
    public static void init() {
        baseURI = "http://44.206.228.3:8000";
    }



}
