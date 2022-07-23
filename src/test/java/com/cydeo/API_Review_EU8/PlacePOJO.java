package com.cydeo.API_Review_EU8;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@ToString
public class PlacePOJO {

    @JsonProperty("place name")
    private String placeName;

    private String longitude;

    private String state;

    @JsonProperty("state abbreviation")
    private String stateAbbreviation;

    private String latitude;

}
