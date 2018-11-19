package com.appdirect.controller;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PriceCollectionControllerIT {

    @Autowired
    private DataSource dataSource;
    @LocalServerPort
    private int port;

    @Before
    public void setUp()  {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("TRUNCATE TABLE product");
        jdbcTemplate.execute("INSERT INTO product (name, base_price) VALUES ('abhishek', 100.00)");
        jdbcTemplate.execute("INSERT INTO product (name, base_price) VALUES ('baghel', 101.00)");

        jdbcTemplate.execute("INSERT INTO Store (name ) VALUES ('Amit')");
        jdbcTemplate.execute("INSERT INTO store (name ) VALUES ('Nike')");
    }

    @Test
    public void shouldPostPrice() {
        String POST_NEW_PRICE_COLLECTION = String.format("http://localhost:%s/store/product", port);

        Map<String, String> priceCollection = new HashMap<>();
        priceCollection.put("storeId", "1");
        priceCollection.put("productId", "1");
        priceCollection.put("storePrice", "234.9");

        given()
                .contentType("application/json")
                .body(priceCollection)
                .when().post(POST_NEW_PRICE_COLLECTION)
                .then()
                .statusCode(201)
                .header("Location", Matchers.is("/product/1"));
    }
}