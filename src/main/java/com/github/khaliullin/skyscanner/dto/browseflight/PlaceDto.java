package com.github.khaliullin.skyscanner.dto.browseflight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PlaceDto {

    @JsonProperty("PlaceId")
    private String placeId;

    @JsonProperty("IataCode")
    private String iataCode;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("SkyscannerCode")
    private String skyscannerCode;

    @JsonProperty("CityName")
    private String cityName;

    @JsonProperty("CityId")
    private String cityId;

    @JsonProperty("CountryName")
    private String countryName;
}