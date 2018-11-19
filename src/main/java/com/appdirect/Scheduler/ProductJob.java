package com.appdirect.Scheduler;

import com.appdirect.service.ProductService;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductJob implements Job {

    @Autowired
    private ProductService jobService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        System.out.println("job started");
        jobService.getIdealPriceCalculator();
        System.out.println("job ended");

    }
}
