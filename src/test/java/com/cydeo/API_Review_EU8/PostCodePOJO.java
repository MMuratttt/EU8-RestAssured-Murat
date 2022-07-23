package com.cydeo.API_Review_EU8;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class PostCodePOJO {

    @JsonProperty("post code")
    private String postCode;

    private String country;

    @JsonProperty("country abbreviation")
    private String countryAbbreviation;

    private List<PlacePOJO> places = null;

}
