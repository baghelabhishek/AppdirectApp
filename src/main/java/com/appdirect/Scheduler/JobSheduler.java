package com.appdirect.Scheduler;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JobSheduler {

    public void startJob(Date date) {

        JobDetail job = JobBuilder.newJob(ProductJob.class)
                .withIdentity("IdealPriceCalulator", "appDirect")
                .build();


        TriggerBuilder<Trigger> triggerTriggerBuilder = TriggerBuilder
                .newTrigger()
                .withIdentity("IdealPriceCalulator", "appDirect")
                .startAt(date);


        try {
            Scheduler  scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
            scheduler.scheduleJob(job, triggerTriggerBuilder.build());
        } catch (SchedulerException e) {
            throw new RuntimeException("Something went wrong");
        }
    }
}
