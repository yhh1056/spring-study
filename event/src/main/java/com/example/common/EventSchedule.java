package com.example.common;

import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EventSchedule {

    @Scheduled(fixedDelay = 5L, timeUnit = TimeUnit.SECONDS)
    public void test() {
        EventFailRepository.publish();
        EventFailRepository.delete();
    }
}

