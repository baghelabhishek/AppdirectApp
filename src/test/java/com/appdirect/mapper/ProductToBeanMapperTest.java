package com.appdirect.mapper;

import com.appdirect.bean.ProductBean;
import com.appdirect.model.PriceCollection;
import com.appdirect.model.Product;
import com.google.common.collect.ImmutableSet;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class ProductToBeanMapperTest {
    private static final long PRODUCT_ID = 348L;
    private static final String PRODUCT_NAME = "ProductName";
    private static final double BASE_PRICE = 34.67D;
    @InjectMocks
    private ProductToBeanMapper mapper;



    @Test
    public void shouldMapProductPropertyToProductBean() {
        Product product = buildProduct(PRODUCT_ID,PRODUCT_NAME,BASE_PRICE);

        ProductBean productBean = mapper.apply(product);

        assertThat(productBean.getId(), is(PRODUCT_ID));
        assertThat(productBean.getBasePrice(),is(BASE_PRICE));
        assertThat(productBean.getName(),is(PRODUCT_NAME));
    }

    @Test
    public void shouldCalculateAveragePrice() {
        ImmutableSet<PriceCollection> priceCollections =  ImmutableSet.of(buildPriceCollection(1.0), buildPriceCollection(2.0), buildPriceCollection(3.0));
        Product product = buildProduct(PRODUCT_ID,PRODUCT_NAME,BASE_PRICE);
        product.setPriceCollections(priceCollections);

        ProductBean productBean = mapper.apply(product);

        assertThat(productBean.getAverageStorePrice(),is(2.0));
    }

    @Test
    public void shouldCalculateLowestPrice() {
        ImmutableSet<PriceCollection> priceCollections =  ImmutableSet.of(buildPriceCollection(1.0), buildPriceCollection(2.0), buildPriceCollection(3.0));
        Product product = buildProduct(PRODUCT_ID,PRODUCT_NAME,BASE_PRICE);
        product.setPriceCollections(priceCollections);

        ProductBean productBean = mapper.apply(product);

        assertThat(productBean.getLowestStorePrice(),is(1.0));
    }

    @Test
    public void shouldCalCulateHighestPrice() {
        ImmutableSet<PriceCollection> priceCollections = ImmutableSet.of(buildPriceCollection(1.0), buildPriceCollection(2.0), buildPriceCollection(3.0));
        Product product = buildProduct(PRODUCT_ID,PRODUCT_NAME,BASE_PRICE);
        product.setPriceCollections(priceCollections);

        ProductBean productBean = mapper.apply(product);

        assertThat(productBean.getHighestStorePrice(),is(3.0));
    }

    private PriceCollection buildPriceCollection(double storePrice) {
        PriceCollection priceCollection = new PriceCollection();
        priceCollection.setStorePrice(storePrice);
        return priceCollection;
    }

    private Product buildProduct(long productId, String productName, double basePrice) {
        Product product = new Product();
        product.setId(productId);
        product.setName(productName);
        product.setBasePrice(basePrice);
        return product;
    }
}