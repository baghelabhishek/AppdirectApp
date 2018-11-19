package com.appdirect.controller;

import com.appdirect.repository.ProductRepository;
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
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerIT {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private ProductRepository productRepository;

    @LocalServerPort
    private int port;

    @Before
    public void setUp() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.execute("TRUNCATE TABLE product");
        jdbcTemplate.execute("INSERT INTO product (name, base_price) VALUES ('abhishek', 100.00)");
        jdbcTemplate.execute("INSERT INTO product (name, base_price) VALUES ('baghel', 101.00)");
    }

    @Test
    public void shouldCreateNewProduct()  {
        String POST_NEW_PRODUCT = String.format("http://localhost:%s/products", port);

        Map<String, String> product = new HashMap<>();
        product.put("name", "jaquar");
        product.put("base_price", "2.0");

        given()
                .contentType("application/json")
                .body(product)
                .when().post(POST_NEW_PRODUCT)
                .then()
                .statusCode(201)
                .header("Location", Matchers.is("/products/3"));

    }

    @Test
    public void shouldReturnAllProducts()  {
        String GET_ALL_PRODUCTS = String.format("http://localhost:%s/products", port);

        when()
                .get(GET_ALL_PRODUCTS)
                .then()
                .statusCode(is(200))
                .body(Matchers.containsString("[{\"id\":9,\"name\":\"abhishek\",\"basePrice\":100.0," +
                        "\"priceCollections\":[]},{\"id\":10,\"name\":\"baghel\",\"basePrice\":101.0," +
                        "\"priceCollections\":[]}]"));
    }

    @Test
    public void shouldReturnOnlyOneProduct()  {
        String GET_BY_PRODUCT_ID = String.format("http://localhost:%s/products/4", port);

        when()
                .get(GET_BY_PRODUCT_ID)
                .then()
                .statusCode(is(200))
                .body(Matchers.containsString("{\"id\":4,\"name\":\"abhishek\",\"basePrice\":100.0,\"priceCollections\":[]}"));
    }

    @Test
    public void shouldUpdateProduct() {
        String UPDATE_NEW_PRODUCT = String.format("http://localhost:%s/products/1", port);

        Map<String, String> product = new HashMap<>();
        product.put("name", "Audi");
        product.put("base_price", "2.0");

        given()
                .contentType("application/json")
                .body(product)
                .when().put(UPDATE_NEW_PRODUCT)
                .then()
                .statusCode(204);
    }

    @Test
    public void shouldDeleteProductById()  {
        String DELETE_BY_PRODUCT_ID = String.format("http://localhost:%s/products/11", port);

        when()
                .delete(DELETE_BY_PRODUCT_ID)
                .then()
                .statusCode(is(200));
    }
}