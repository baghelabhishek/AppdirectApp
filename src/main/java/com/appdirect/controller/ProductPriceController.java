package com.appdirect.controller;

import com.appdirect.bean.ProductBean;
import com.appdirect.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductPriceController {

   private final ProductService productService;

    @Autowired
    public ProductPriceController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "/product/{product-id}/prices")
    public ResponseEntity<ProductBean> getProductDetails(@PathVariable(value = "product-id") Long productId) {
        return ResponseEntity
                .ok()
                .body(productService.getProductDetails(productId));

    }
}
