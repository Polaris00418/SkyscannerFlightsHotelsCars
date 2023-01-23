package com.github.khaliullin.skyscanner.schedule;

import com.github.khaliullin.skyscanner.dto.browseflight.FlightPricesDto;
import com.github.khaliullin.skyscanner.repository.SubscriptionRepository;
import com.github.khaliullin.skyscanner.service.EmailNotifierService;
import com.github.khaliullin.skyscanner.service.FlightPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class RecountPriceServiceImpl implements RecountPriceService{

    private SubscriptionRepository subscriptionRepository;

    private FlightPriceService flightPriceService;

    private EmailNotifierService emailNotifierService;

    @Autowired
    public void setSubscriptionRepository(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Autowired
    public void setFlightPriceService(FlightPriceService flightPriceService) {
        this.flightPriceService = flightPriceService;
    }

    @Autowired
    public void setEmailNotifierService(EmailNotifierService emailNotifierService) {
        this.emailNotifierService = emailNotifierService;
    }

    @Override
    public void recount() {
        subscriptionRepository.findAll().forEach( subscription -> {

            if (subscription.getOutboundPartialDate().isAfter(LocalDate.now().minusDays(1))) {
                FlightPricesDto flightPricesDto = flightPriceService.findFlightPrice(subscription);
                Integer newMinPrice = flightPriceService.findMinPrice(flightPricesDto);
                if (subscription.getMinPrice() > newMinPrice) {
                    emailNotifierService.notifySubscriber(subscription, subscription.getMinPrice(), newMinPrice);
                    subscription.setMinPrice(newMinPrice);
                    subscriptionRepository.save(subscription);
                }
            }else subscriptionRepository.delete(subscription);

        });
    }
}
