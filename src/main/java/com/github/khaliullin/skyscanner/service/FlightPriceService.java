package com.github.khaliullin.skyscanner.service;

import com.github.khaliullin.skyscanner.dto.browseflight.FlightPricesDto;
import com.github.khaliullin.skyscanner.entity.Subscription;

public interface FlightPriceService {

    Integer findMinPrice(FlightPricesDto flightPricesDto);

    FlightPricesDto findFlightPrice(Subscription subscription);


}
