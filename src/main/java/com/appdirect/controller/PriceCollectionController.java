package com.appdirect.controller;

import com.appdirect.bean.PriceCollectionBean;
import com.appdirect.model.PriceCollection;
import com.appdirect.model.Product;
import com.appdirect.service.PriceCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class PriceCollectionController {

    private final PriceCollectionService priceCollectionService;

    @Autowired
    public PriceCollectionController(PriceCollectionService priceCollectionService) {
        this.priceCollectionService = priceCollectionService;
    }

    @PostMapping(path = "/store/product")
    public ResponseEntity<PriceCollection> create(@RequestBody PriceCollectionBean priceCollectionBean) {
        Product product = priceCollectionService.saveProduct(priceCollectionBean);
        return ResponseEntity
                .created(URI.create(String.format("/product/%d", product.getId())))
                .build();
    }

}
