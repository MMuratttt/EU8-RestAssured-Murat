package com.cydeo.utilities;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class SpartanUtils {

    @DisplayName("Create one random spartan in Map")
    @Test
    public static Map<String, Object> createOneSpartan() {

        Faker faker = new Faker();
        Random random = new Random();
        String[] gender = {"Male", "Female"};

        Map<String, Object> spartanInfo = new HashMap<>();
        spartanInfo.put("name", faker.name().firstName());
        spartanInfo.put("gender", gender[random.nextInt(2)]);
        spartanInfo.put("phone", faker.numerify("##########"));

        return spartanInfo;
    }

}
