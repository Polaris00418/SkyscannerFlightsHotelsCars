package com.github.khaliullin.skyscanner.service;

import com.github.khaliullin.skyscanner.entity.Subscription;

public interface EmailNotifierService {

    void notifySubscriber(Subscription subscription, Integer oldMinPrice, Integer newMinPrice);

    void notifyAddingSubscription(Subscription subscription);


}
