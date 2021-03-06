package com.appdirect.controller;

import com.appdirect.model.Product;
import com.appdirect.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostMapping(path = "/products")
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product newProduct = productRepository.save(product);
        return ResponseEntity
                .created(URI.create(String.format("/products/%d", newProduct.getId())))
                .build();

    }

    @GetMapping(path = "/products")
    public ResponseEntity<Iterable<Product>> getAll() {
        Iterable<Product> allProducts = productRepository.findAll();
        return ResponseEntity
                .ok()
                .body(allProducts);

    }

    @GetMapping(path = "/products/{id}")
    public ResponseEntity<Product> getBy(@PathVariable Long id) {
        Product oneProduct = productRepository.findById(id).get();
        return ResponseEntity
                .ok()
                .body(oneProduct);
    }

    @PutMapping(path = "/products/{id}")
    public ResponseEntity update(@PathVariable("id") Long id, @RequestBody Product product) {
        product.setId(id);
        productRepository.save(product);
        return ResponseEntity
                .noContent()
                .build();

    }

    @DeleteMapping(path = "/products/{id}")
    public ResponseEntity delete(@PathVariable("id") Long productId) {
        productRepository.deleteById(productId);
        return ResponseEntity
                .ok()
                .build();
    }
}
