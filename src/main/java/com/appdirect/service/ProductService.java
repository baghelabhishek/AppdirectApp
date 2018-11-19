package com.appdirect.service;

import com.appdirect.bean.ProductBean;
import com.appdirect.mapper.ProductToBeanMapper;
import com.appdirect.model.PriceCollection;
import com.appdirect.model.Product;
import com.appdirect.repository.ProductRepository;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import static com.appdirect.mapper.PriceRule.IdealPriceRule;

@Component
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductToBeanMapper mapper;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductToBeanMapper mapper) {
        this.productRepository = productRepository;
        this.mapper = mapper;
    }

    public double getIdealPriceCalculator(){
        ArrayList<Product> products = Lists.newArrayList(productRepository.findAll());
        Set<PriceCollection> priceCollectionSet = products.stream()
                .flatMap(product -> product.getPriceCollections().stream())
                .collect(Collectors.toSet());
        return IdealPriceRule.apply(priceCollectionSet);
    }

    public ProductBean getProductDetails(long productId){
        Product product = productRepository.findById(productId).get();
        return mapper.apply(product);
    }

}
