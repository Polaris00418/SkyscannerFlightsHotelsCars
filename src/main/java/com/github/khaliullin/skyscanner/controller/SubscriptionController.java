package com.github.khaliullin.skyscanner.controller;

import com.github.khaliullin.skyscanner.dto.SubscriptionCreateDto;
import com.github.khaliullin.skyscanner.dto.SubscriptionDto;
import com.github.khaliullin.skyscanner.dto.SubscriptionUpdateDto;
import com.github.khaliullin.skyscanner.entity.Subscription;
import com.github.khaliullin.skyscanner.service.SubscriptionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Path;
import javax.validation.Valid;
import java.util.List;


@Api(value = "Operations with Subscriptions", tags = "Subscription Controller")
@RequestMapping(SubscriptionController.SUBSCRIPTION_CONTROLLER_EP)
@Controller
public class SubscriptionController {

    public static final String SUBSCRIPTION_CONTROLLER_EP = "/subscription";

    private SubscriptionService subscriptionService;

    @Autowired
    public void setSubscriptionService(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @ApiOperation("Create new subscription based on SubscriptionDto")
    @PostMapping
    @ResponseBody
    public SubscriptionDto create(@RequestBody @Valid SubscriptionCreateDto subscriptionCreateDto) {
        return subscriptionService.create(subscriptionCreateDto);
    }

    @ApiOperation("Finds all subscriptions based on email")
    @GetMapping("/{email}")
    @ResponseBody
    public List<SubscriptionDto> findByEmail(@PathVariable final String email) {
        return subscriptionService.findByEmail(email);
    }

    @ApiOperation("Updates subscription based on its ID")
    @PutMapping("/{id}")
    public SubscriptionDto update(@PathVariable final Long id, @RequestBody @Valid SubscriptionUpdateDto subscriptionUpdateDto) {
        return subscriptionService.update(id, subscriptionUpdateDto);
    }

    @ApiOperation("Deletes subscription based on its ID")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable final Long id) {
        subscriptionService.delete(id);
    }

}
