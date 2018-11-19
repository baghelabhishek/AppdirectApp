package com.appdirect.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductPriceControllerIT {

    @Autowired
    private DataSource dataSource;
    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("TRUNCATE TABLE product");
        jdbcTemplate.execute("INSERT INTO product (name, base_price) VALUES ('abhishek', 100.00)");
        jdbcTemplate.execute("INSERT INTO product (name, base_price) VALUES ('baghel', 101.00)");

        jdbcTemplate.execute("INSERT INTO Store (name ) VALUES ('Amit')");
        jdbcTemplate.execute("INSERT INTO store (name ) VALUES ('Nike')");

        jdbcTemplate.execute("INSERT INTO product_store_mapping (product_id,store_id,base_price )" + " VALUES (1,1,123)");
        jdbcTemplate.execute("INSERT INTO product_store_mapping (product_id,store_id,base_price )" + " VALUES (1,2,13)");
        jdbcTemplate.execute("INSERT INTO product_store_mapping (product_id,store_id,base_price )" + " VALUES (1,2,23)");
        jdbcTemplate.execute("INSERT INTO product_store_mapping (product_id,store_id,base_price )" + " VALUES (1,2,223)");
        jdbcTemplate.execute("INSERT INTO product_store_mapping (product_id,store_id,base_price )" + " VALUES (1,1,323)");
        jdbcTemplate.execute("INSERT INTO product_store_mapping (product_id,store_id,base_price )" + " VALUES (1,2,123)");
        jdbcTemplate.execute("INSERT INTO product_store_mapping (product_id,store_id,base_price )" + " VALUES (1,1,23)");
        jdbcTemplate.execute("INSERT INTO product_store_mapping (product_id,store_id,base_price )" + " VALUES (1,2,12)");
        jdbcTemplate.execute("INSERT INTO product_store_mapping (product_id,store_id,base_price )" + " VALUES (1,1,44)");


    }

    @Test
    public void shouldReturnProductDetails()  {
        String expectedOutput = "{\"id\":1,\"name\":\"abhishek\",\"basePrice\":100.0,\"averageStorePrice\":68.0," +
                "\"lowestStorePrice\":13.0,\"highestStorePrice\":123.0,\"idealStorePrice\":0.0," +
                "\"numberOfPriceCollect\":0}";

        when()
                .get(String.format("http://localhost:%s/product/1/prices", port))
                .then()
                .statusCode(is(200))
                .body(containsString(expectedOutput));
    }

}