package com.github.khaliullin.skyscanner.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class SchedulerTasks {

    private RecountPriceService recountPriceService;

    @Autowired
    public void setRecountPriceService(RecountPriceService recountPriceService) {
        this.recountPriceService = recountPriceService;
    }

    private static final long TEN_MINUTES = 1000 * 60 * 10;

    @Scheduled(fixedRate = TEN_MINUTES)
    public void recountMinPrice() {
        log.debug("recount minPrice started");
        recountPriceService.recount();
        log.debug("recount minPrice finished");
    }
}
