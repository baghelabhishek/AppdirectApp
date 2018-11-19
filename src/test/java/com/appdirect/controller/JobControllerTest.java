package com.appdirect.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class JobControllerTest {

    @LocalServerPort
    private int port;

    @Test
    public void shouldPostJob() {
        String JOB_ENDPOINT = String.format("http://localhost:%s/jobs/pricecalculator", port);

        Map<String, String> jobBean = new HashMap<>();
        jobBean.put("jobName", "started");
        jobBean.put("startDate", "2018-07-22T11:55:11.782Z");

        given()
                .contentType("application/json")
                .body(jobBean)
                .when().post(JOB_ENDPOINT)
                .then()
                .statusCode(202);
    }


}