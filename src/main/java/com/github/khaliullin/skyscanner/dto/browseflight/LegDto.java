package com.github.khaliullin.skyscanner.dto.browseflight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class LegDto {

    @JsonProperty("OriginId")
    private Integer originId;

    @JsonProperty("DestinationId")
    private Integer destinationId;

    @JsonProperty("DepartureDate")
    private String departureDate;

    @JsonProperty("CarrierIds")
    private List<Integer> carrierIds;
}