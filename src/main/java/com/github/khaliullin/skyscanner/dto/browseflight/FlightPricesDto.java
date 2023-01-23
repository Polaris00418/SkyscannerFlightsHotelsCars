package com.github.khaliullin.skyscanner.dto.browseflight;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.khaliullin.skyscanner.dto.CurrencyDto;
import lombok.Data;

import java.util.List;

@Data
public class FlightPricesDto {

    @JsonProperty("Quotes")
    private List<QuoteDto> quotas;

    @JsonProperty("Places")
    private List<PlaceDto> places;

    @JsonProperty("Carrier")
    private List<CarrierDto> carriers;

    @JsonProperty("Currencies")
    private List<CurrencyDto> currencies;
}
