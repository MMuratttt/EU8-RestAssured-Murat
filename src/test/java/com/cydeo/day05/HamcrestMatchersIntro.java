package com.cydeo.day05;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersIntro {

    @Test
    public void simpleTest1(){

        assertThat(5+5, is(10));
        assertThat(5+5,equalTo(10));
        assertThat(5+5,is(equalTo(10)));

        assertThat(5+5,not(9));
        assertThat(5+5,is(not(9)));
        assertThat(5+5,is(not(equalTo(9))));

        assertThat(5+5,is(greaterThan(9)));
       // lessThanOrEqualTo();

    }

    @DisplayName("Assertion with String")
    @Test
    public void stringHamcrest(){

        String text = "B22 is learning Hamcrest";

        assertThat(text,is("B22 is learning Hamcrest"));
        assertThat(text,equalTo("B22 is learning Hamcrest"));
        assertThat(text,is(equalTo("B22 is learning Hamcrest")));

        assertThat(text,startsWith("B22"));
        assertThat(text,endsWithIgnoringCase("Hamcrest"));

        assertThat(text,containsString("learning"));

        String str = "   ";
        assertThat(str, blankString());
        assertThat(str.trim(),emptyString());

    }

    @DisplayName("Hamcrest for collection")
    @Test
    public void testCollection(){

        List<Integer> listOfNumbers = Arrays.asList(1,4,5,6,32,54,66,77,45,23);

        assertThat(listOfNumbers,hasSize(10));

        assertThat(listOfNumbers,hasItem(77));
        assertThat(listOfNumbers,hasItems(77,54,23));

        assertThat(listOfNumbers,everyItem(greaterThan(0)));



    }

}
