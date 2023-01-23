package com.github.khaliullin.skyscanner.service;

import com.github.khaliullin.skyscanner.client.FlightPricesClient;
import com.github.khaliullin.skyscanner.dto.browseflight.FlightPricesDto;
import com.github.khaliullin.skyscanner.entity.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FlightPriceServiceImpl implements FlightPriceService{

    private FlightPricesClient flightPricesClient;

    @Autowired
    public void setFlightPricesClient(FlightPricesClient flightPricesClient) {
        this.flightPricesClient = flightPricesClient;
    }

    @Override
    public Integer findMinPrice(FlightPricesDto flightPricesDto) {
        return flightPricesDto.getQuotas().get(0).getMinPrice();
    }

    @Override
    public FlightPricesDto findFlightPrice(Subscription subscription) {

        if (subscription.getInboundPartialDate() == null) {
            return flightPricesClient.browseQuotes(subscription.getCountry(), subscription.getCurrency(),
                    subscription.getLocale(), subscription.getOriginPlace(), subscription.getDestinationPlace(),
                    subscription.getOutboundPartialDate().toString());
        }else{
            return flightPricesClient.
                    browseQuotes(subscription.getCountry(), subscription.getCurrency(), subscription.getLocale(),
                            subscription.getOriginPlace(), subscription.getDestinationPlace(), subscription.getOutboundPartialDate().toString(),
                            subscription.getInboundPartialDate().toString());
        }
    }
}
