package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class Region {

    @JsonProperty("region_id")
    private int regionId;
    private String region_name;
    private List<Link> links;


}
