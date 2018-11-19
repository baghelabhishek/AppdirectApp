package com.appdirect.controller;

import com.appdirect.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.core.Is.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StoreControllerIT {

    private static final String STORE_NAME = "StoreName";

    @Autowired
    private ProductRepository productService;

    @LocalServerPort
    private int port;



    @Test
    public void shouldCreateNewStore() {
        String POST_NEW_STORE = String.format("http://localhost:%s/stores", port);

        Map<String, String> store = new HashMap<>();
        store.put("name", STORE_NAME);

        given()
                .contentType("application/json")
                .body(store)
                .when().post(POST_NEW_STORE)
                .then()
                .statusCode(201);

    }

    @Test
    public void shouldReturnAllStores() {
        String GET_ALL_STORES = String.format("http://localhost:%s/stores", port);

        when()
                .get(GET_ALL_STORES)
                .then()
                .statusCode(is(200));
    }

    @Test
    public void shouldUpdateProduct() {
        String UPDATE_NEW_STORE = String.format("http://localhost:%s/stores/1", port);

        Map<String, String> store = new HashMap<>();
        store.put("name", "new Store");

        given()
                .contentType("application/json")
                .body(store)
                .when().put(UPDATE_NEW_STORE)
                .then()
                .statusCode(204);
    }

    @Test
    public void shouldDeleteStoreById() {
        String DELETE_BY_STORE_ID = String.format("http://localhost:%s/stores/1", port);

        when()
                .delete(DELETE_BY_STORE_ID)
                .then()
                .statusCode(is(200));
    }


}