package com.appdirect.mapper;


import com.appdirect.bean.ProductBean;
import com.appdirect.model.PriceCollection;
import com.appdirect.model.Product;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class ProductToBeanMapper implements PriceRule{



    public ProductBean apply(Product product){
        Set<PriceCollection> priceCollections = product.getPriceCollections();
        ProductBean productBean = new ProductBean();
        productBean.setId(product.getId());
        productBean.setName(product.getName());
        productBean.setBasePrice(product.getBasePrice());
        productBean.setAverageStorePrice(AveragePriceRule.apply(priceCollections));
        productBean.setHighestStorePrice(HighestPriceRule.apply(priceCollections));
        productBean.setIdealStorePrice(IdealPriceRule.apply(priceCollections));
        productBean.setLowestStorePrice(LowestPriceRule.apply(priceCollections));
        return productBean;
    }
}
