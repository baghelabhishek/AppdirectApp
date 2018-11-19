package com.appdirect.controller;

import com.appdirect.Scheduler.JobSheduler;
import com.appdirect.bean.JobBean;
import com.appdirect.model.PriceCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobController {

    private final JobSheduler jobSheduler;

    @Autowired
    public JobController(JobSheduler jobSheduler) {
        this.jobSheduler = jobSheduler;
    }

    @PostMapping(path = "/jobs/pricecalculator")
    public ResponseEntity<PriceCollection> createJob(@RequestBody JobBean jobBean) {
        jobSheduler.startJob(jobBean.getStartDate());
        return ResponseEntity
                .accepted()
                .build();
    }
}
