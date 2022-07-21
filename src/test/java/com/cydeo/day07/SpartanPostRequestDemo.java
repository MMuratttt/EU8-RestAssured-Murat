package com.cydeo.day07;

import com.cydeo.pojo.Spartan;
import com.cydeo.utilities.SpartanTestBase;
import com.cydeo.utilities.SpartanUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class SpartanPostRequestDemo extends SpartanTestBase {

    /*
   Given accept type and Content type is JSON
   And request json body is:
   {
     "gender":"Male",
     "name":"Severus",
     "phone":8877445596
  }
   When user sends POST request to '/api/spartans'
   Then status code 201
   And content type should be application/json
   And json payload/response/body should contain:
   "A Spartan is Born!" message
   and same data what is posted
*/
    @Test
    public void postMethod1(){

        String requestJsonBody = "{\n" +
                "  \"gender\": \"Male\",\n" +
                "  \"name\": \"Dumbledore\",\n" +
                "  \"phone\": 789258159645\n" +
                "}";

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestJsonBody)
                .when()
                .post("/api/spartans");

        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedMessage = "A Spartan is Born!";
        assertThat(response.path("success"),equalTo(expectedMessage));

        System.out.println(response.path("data.name").toString());

    }

@DisplayName("POST with Map to JSon")
    @Test
    public void postMethod2(){


    Map<String,Object> requestJsonMap = new HashMap<>();
    requestJsonMap.put("name","Toriq");
    requestJsonMap.put("gender","Male");
    requestJsonMap.put("phone",6548321578L);

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(requestJsonMap).log().all()
                .when()
                .post("/api/spartans");

        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedMessage = "A Spartan is Born!";
        assertThat(response.path("success"),equalTo(expectedMessage));

        System.out.println(response.path("data.name").toString());
    System.out.println(response.path("data.id").toString());

    response.prettyPrint();

    }

    @DisplayName("POST with Map to Spartan Class")
    @Test
    public void postMethod3(){

        Spartan spartan = new Spartan();
        spartan.setName("Severus");
        spartan.setGender("Male");
        spartan.setPhone(6498321669874L);

        System.out.println("spartan = " + spartan);

        Response response = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(spartan).log().all()
                .when()
                .post("/api/spartans");

        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedMessage = "A Spartan is Born!";
        assertThat(response.path("success"),equalTo(expectedMessage));

        System.out.println(response.path("data.name").toString());
        System.out.println(response.path("data.id").toString());

        response.prettyPrint();

    }

    @Test
    public void postMethod4(){

        String expectedMessage = "A Spartan is Born!";

        int id = given().accept(ContentType.JSON)
                .contentType(ContentType.JSON)
                .body(SpartanUtils.createOneSpartan()).log().all()
                .when()
                .post("/api/spartans")
                .then()
                .statusCode(201)
                .contentType("application/json")
                .body("success", is(expectedMessage)).log().all()
                .extract().jsonPath().getInt("data.id");

        System.out.println(id);

        Spartan spartanWhichKnownId = given().accept("application/json")
                .pathParam("id", id)
                .get("/api/spartans/{id}")
                .then()
                .statusCode(200)
                .extract().as(Spartan.class);

        assertThat(spartanWhichKnownId.getId(),is(id));

                /*
        assertThat(response.statusCode(),is(201));
        assertThat(response.contentType(),is("application/json"));

        String expectedMessage = "A Spartan is Born!";
        assertThat(response.path("success"),equalTo(expectedMessage));

        System.out.println(response.path("data.name").toString());
        System.out.println(response.path("data.id").toString());

        response.prettyPrint();
*/
    }

}
