package com.cydeo.day06;

import com.cydeo.pojo.Employee;
import com.cydeo.pojo.Link;
import com.cydeo.pojo.Region;
import com.cydeo.pojo.Regions;
import com.cydeo.utilities.HRTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ORDSPojoGetRequestTest extends HRTestBase {

    @Test
    public void regionTest() {

        JsonPath jsonPath = get("/regions").then().statusCode(200).extract().jsonPath();

        Region region1 = jsonPath.getObject("items[0]", Region.class);

        System.out.println(region1);

        System.out.println("region1.getRegion_id() = " + region1.getRegionId());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        Link rel = region1.getLinks().get(0);
        System.out.println("rel = " + rel);
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());

    }

    @DisplayName("GET request to  /employees and only get couple of values as a Pojo class")
    @Test

    public void employeeGet(){

        JsonPath jsonPath = given().accept(ContentType.JSON)
                .get("/employees")
                .then()
                .statusCode(200)
                .extract().jsonPath();

        Employee employee1 = jsonPath.getObject("items[0]", Employee.class);

        System.out.println(employee1);

    }

    /* send a get request to regions
        verify that region ids are 1,2,3,4
        verify that regions names Europe ,Americas , Asia, Middle East and Africa
        verify that count is 4
        try to use pojo as much as possible
        ignore non used fields
     */

    @DisplayName("GET request to region only some fields test")
    @Test
    public void regionPojoTest(){

        Regions regions = get("/regions").
                then()
                .statusCode(200)
                .extract().as(Regions.class);

        assertThat(regions.getCount(),is(4));

       List<String> regionNames = new ArrayList<>();
       List<Integer> regionIds = new ArrayList<>();

       List<Region> items = regions.getItems();

        for (Region region : items) {

            regionIds.add(region.getRegionId());
            regionNames.add(region.getRegion_name());

        }

        System.out.println("regionIds = " + regionIds);
        System.out.println("regionNames = " + regionNames);

        List<Integer> expectedRegionIds = Arrays.asList(1,2,3,4);
        List<String> expectedRegionNames = Arrays.asList("Europe", "Americas", "Asia", "Middle East and Africa");


        assertThat(regionNames,is(expectedRegionNames));
        assertThat(regionIds,equalTo(expectedRegionIds));

    }



}
