package com.appdirect.service;

import com.appdirect.bean.PriceCollectionBean;
import com.appdirect.model.PriceCollection;
import com.appdirect.model.Product;
import com.appdirect.model.Store;
import com.appdirect.repository.ProductRepository;
import com.appdirect.repository.StoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PriceCollectionService {


    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;

    @Autowired
    public PriceCollectionService(ProductRepository productRepository, StoreRepository storeRepository) {
        this.productRepository = productRepository;
        this.storeRepository = storeRepository;
    }

    public Product saveProduct(PriceCollectionBean priceCollectionBean){
        Product product = productRepository.findById(priceCollectionBean.getProductId()).get();
        Store store = storeRepository.findById(priceCollectionBean.getStoreId()).get();
        PriceCollection priceCollection = buildPriceCollection(priceCollectionBean, product, store);
        product.getPriceCollections().add(priceCollection);

        productRepository.save(product);
        storeRepository.save(store);
        return product;
    }

    private PriceCollection buildPriceCollection(PriceCollectionBean priceCollectionBean, Product product, Store store) {
        PriceCollection priceCollection = new PriceCollection();
        priceCollection.setStore(store);
        priceCollection.setProduct(product);
        priceCollection.setStorePrice(priceCollectionBean.getStorePrice());
        return priceCollection;
    }


}
