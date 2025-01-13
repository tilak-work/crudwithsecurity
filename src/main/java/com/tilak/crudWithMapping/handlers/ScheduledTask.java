package com.tilak.crudWithMapping.handlers;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

//@Component
public class ScheduledTask {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    // Task runs every 5 seconds
    @Scheduled(fixedRate = 5000)
    public void fixedRateTask() {
        System.out.println("Fixed Rate Task: " + LocalDateTime.now().format(formatter));
    }

    // Task runs 5 seconds after the previous one finishes
    @Scheduled(fixedDelay = 5000)
    public void fixedDelayTask() {
        System.out.println("Fixed Delay Task: " + LocalDateTime.now().format(formatter));
    }

    // Task runs every minute at 15 seconds
    @Scheduled(cron = "15 * * * * *")
    public void cronTask() {
        System.out.println("Cron Task: " + LocalDateTime.now().format(formatter));
    }
}