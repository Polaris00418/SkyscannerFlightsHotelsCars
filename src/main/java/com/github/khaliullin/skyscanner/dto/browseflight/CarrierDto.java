package com.github.khaliullin.skyscanner.dto.browseflight;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CarrierDto {

    @JsonProperty("CarrierId")
    private Integer carrierId;

    @JsonProperty("Name")
    private String name;
}
