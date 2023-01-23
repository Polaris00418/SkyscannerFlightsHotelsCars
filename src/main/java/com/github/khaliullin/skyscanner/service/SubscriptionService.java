package com.github.khaliullin.skyscanner.service;

import com.github.khaliullin.skyscanner.dto.SubscriptionCreateDto;
import com.github.khaliullin.skyscanner.dto.SubscriptionDto;
import com.github.khaliullin.skyscanner.dto.SubscriptionUpdateDto;
import com.github.khaliullin.skyscanner.entity.Subscription;
import com.github.khaliullin.skyscanner.repository.SubscriptionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SubscriptionService {


    SubscriptionDto create(SubscriptionCreateDto dto);

    List<SubscriptionDto> findByEmail(String email);

    void delete(Long subscriptionId);

    SubscriptionDto update(Long subscriptionId, SubscriptionUpdateDto dto);

}
