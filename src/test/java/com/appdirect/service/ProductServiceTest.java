package com.appdirect.service;

import com.appdirect.mapper.ProductToBeanMapper;
import com.appdirect.model.PriceCollection;
import com.appdirect.model.Product;
import com.appdirect.repository.ProductRepository;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    private static final long PRODUCT_ID = 34L;
    @InjectMocks
    private ProductService productService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductToBeanMapper mapper;

    @Test
    public void shouldCalculateIdealPrice() {
        ArrayList<Product> products = Lists.newArrayList(buildProduct(12.0), buildProduct(12.0), buildProduct(12.0), buildProduct(12.0), buildProduct(12.0),
                buildProduct(12.0), buildProduct(12.0));
        when(productRepository.findAll()).thenReturn(products);

        double idealPriceCalculator = productService.getIdealPriceCalculator();
        Assert.assertThat(idealPriceCalculator, org.hamcrest.Matchers.is(14.4));
    }

    @Test
    public void shouldGetProductDetails() {
        Product product = new Product();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        productService.getProductDetails(PRODUCT_ID);
        verify(productRepository).findById(PRODUCT_ID);
        verify(mapper).apply(product);
    }

    private Product buildProduct(double price) {
        Product product = new Product();
        PriceCollection priceCollection = buildPriceCollection(price);
        product.getPriceCollections().add(priceCollection);
        return product;
    }

    private PriceCollection buildPriceCollection(double price) {
        PriceCollection priceCollection = new PriceCollection();
        priceCollection.setStorePrice(price);
        return priceCollection;
    }
}