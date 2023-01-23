package com.github.khaliullin.skyscanner.client;

import com.github.khaliullin.skyscanner.dto.browseflight.FlightPricesDto;

public interface FlightPricesClient {
    FlightPricesDto browseQuotes(String country, String currency, String locale, String originalPlace,
                                 String destinationPlace, String outboundPartialDate);

    FlightPricesDto browseQuotes(String country, String currency, String locale, String originalPlace,
                                 String destinationPlace, String outboundPartialDate, String inboundPartialDate);
}
