package com.github.khaliullin.skyscanner.service;

import com.github.khaliullin.skyscanner.dto.SubscriptionCreateDto;
import com.github.khaliullin.skyscanner.dto.SubscriptionDto;
import com.github.khaliullin.skyscanner.dto.SubscriptionUpdateDto;
import com.github.khaliullin.skyscanner.dto.browseflight.FlightPricesDto;
import com.github.khaliullin.skyscanner.entity.Subscription;
import com.github.khaliullin.skyscanner.repository.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@Slf4j
public class SubscriptionServiceImpl implements SubscriptionService {

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
    public SubscriptionDto create(SubscriptionCreateDto dto) {
        Subscription subscription = toEntity(dto);
        Optional<Subscription> one = subscriptionRepository.findOne(Example.of(subscription));

        if(one.isPresent()){
            log.info("The same subscription has been found for Subscription={}", subscription);
            Subscription fromDatabase = one.get();
            FlightPricesDto flightPricesResponse = flightPriceService.findFlightPrice(subscription);
            subscription.setMinPrice(flightPriceService.findMinPrice(flightPricesResponse));
            return toDto(subscription, flightPricesResponse);
        }else{
            FlightPricesDto flightPricesResponse = flightPriceService.findFlightPrice(subscription);
            subscription.setMinPrice(flightPriceService.findMinPrice(flightPricesResponse));
            Subscription saved = subscriptionRepository.save(subscription);
            log.info("Added new subscription={}", saved);
            emailNotifierService.notifyAddingSubscription(saved);
            return toDto(saved, flightPricesResponse);
        }
    }

    @Override
    public List<SubscriptionDto> findByEmail(String email) {
        return subscriptionRepository.findByEmail(email).stream()
                .map(subscription -> {
                    FlightPricesDto flightPricesResponse = flightPriceService.findFlightPrice(subscription);
                    if (!subscription.getMinPrice().equals(flightPriceService.findMinPrice(flightPricesResponse))) {
                        subscription.setMinPrice(flightPriceService.findMinPrice(flightPricesResponse));
                        subscriptionRepository.save(subscription);
                    }
                    return toDto(subscription, flightPricesResponse);
                }).collect(Collectors.toList());
    }

    @Override
    public void delete(Long subscriptionId) {
        subscriptionRepository.deleteById(subscriptionId);
    }

    @Override
    public SubscriptionDto update(Long subscriptionId, SubscriptionUpdateDto dto) {
        Subscription subscription = subscriptionRepository.getOne(subscriptionId);
        subscription.setDestinationPlace(dto.getDestinationPlace());
        subscription.setEmail(dto.getEmail());
        subscription.setLocale(dto.getLocale());
        subscription.setCurrency(dto.getCurrency());
        subscription.setCountry(dto.getCountry());
        subscription.setInboundPartialDate(dto.getInboundPartialDate());
        subscription.setOutboundPartialDate(dto.getOutboundPartialDate());

        FlightPricesDto flightPricesDto = flightPriceService.findFlightPrice(subscription);
        subscription.setMinPrice(flightPriceService.findMinPrice(flightPricesDto));

        return toDto(subscriptionRepository.save(subscription), flightPricesDto);
    }

    private Subscription toEntity(SubscriptionCreateDto dto) {
        Subscription subscription = new Subscription();
        subscription.setCountry(dto.getCountry());
        subscription.setCurrency(dto.getCurrency());
        subscription.setDestinationPlace(dto.getDestinationPlace());
        subscription.setInboundPartialDate(dto.getInboundPartialDate());
        subscription.setOutboundPartialDate(dto.getOutboundPartialDate());
        subscription.setLocale(dto.getLocale());
        subscription.setOriginPlace(dto.getOriginPlace());
        subscription.setEmail(dto.getEmail());
        return subscription;
    }

    private SubscriptionDto toDto(Subscription entity, FlightPricesDto response) {
        SubscriptionDto dto = new SubscriptionDto();
        dto.setEmail(entity.getEmail());
        dto.setCountry(entity.getCountry());
        dto.setCurrency(entity.getCurrency());
        dto.setDestinationPlace(entity.getDestinationPlace());
        dto.setLocale(entity.getLocale());
        dto.setOriginPlace(entity.getOriginPlace());
        dto.setOutboundPartialDate(entity.getOutboundPartialDate());
        dto.setInboundPartialDate(entity.getInboundPartialDate());
        dto.setMinPrice(entity.getMinPrice());
        dto.setId(entity.getId());
        dto.setFlightPricesDto(response);
        return dto;
    }
}
