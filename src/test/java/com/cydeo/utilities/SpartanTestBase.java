package com.cydeo.utilities;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import static io.restassured.RestAssured.baseURI;

public abstract class SpartanTestBase {

    @BeforeAll
    public static void init() {
        baseURI = ConfigurationReader.getProperty("spartanUrl");

        String dbUrl = "jdbc:oracle:thin:@44.206.228.3:1521:XE";
        String dbUsername = "SP";
        String dbPassword = "SP";
      //  utilities.DBUtils.createConnection(dbUrl, dbUsername, dbPassword);

    }

    @AfterAll
    public static void tearDown() {
      //  utilities.DBUtils.destroy();
    }

}
