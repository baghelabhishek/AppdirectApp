package com.appdirect.controller;

import com.appdirect.model.Store;
import com.appdirect.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class StoreController {

    private final StoreRepository storeRepository;

    @Autowired
    public StoreController(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @PostMapping(path = "/stores")
    public ResponseEntity<Store> create(@RequestBody Store store) {
        Store newStore = storeRepository.save(store);
        return ResponseEntity
                .created(URI.create(String.format("/stores/%d", newStore.getId())))
                .build();
    }

    @GetMapping(path = "/stores")
    public ResponseEntity<Iterable<Store>> getStore() {
        Iterable<Store> allProducts = storeRepository.findAll();
        return ResponseEntity
                .ok()
                .body(allProducts);
    }

    @PutMapping(path = "/stores/{id}")
    public ResponseEntity<Store> update(@PathVariable("id") long storeId, @RequestBody Store store) {
        store.setId(storeId);
        storeRepository.save(store);
        return ResponseEntity
                .noContent()
                .build();
    }

    @DeleteMapping(path = "/stores/{id}")
    public ResponseEntity<Store> delete(@PathVariable("id") Long id) {
        storeRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
