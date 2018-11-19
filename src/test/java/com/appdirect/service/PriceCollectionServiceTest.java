package com.appdirect.service;

import com.appdirect.bean.PriceCollectionBean;
import com.appdirect.model.Product;
import com.appdirect.model.Store;
import com.appdirect.repository.ProductRepository;
import com.appdirect.repository.StoreRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Optional;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PriceCollectionServiceTest {

    private static final long PRODUCT_ID = 846L;
    private static final long STORE_ID = 8409L;
    @InjectMocks
    private PriceCollectionService priceCollectionService;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private StoreRepository storeRepository;


    @Test
    public void shouldSaveProduct() {
        Product product = new Product();
        Store store = new Store();
        when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        when(storeRepository.findById(anyLong())).thenReturn(Optional.of(store));

        PriceCollectionBean priceCollection = buildPriceCollectionBean(PRODUCT_ID, STORE_ID);
        priceCollectionService.saveProduct(priceCollection);
        verify(productRepository).findById(PRODUCT_ID);
        verify(storeRepository).findById(STORE_ID);
        verify(productRepository).save(product);
        verify(storeRepository).save(store);
    }

    private PriceCollectionBean buildPriceCollectionBean(long productId, long storeId) {
        PriceCollectionBean priceCollection = new PriceCollectionBean();
        priceCollection.setProductId(productId);
        priceCollection.setStoreId(storeId);
        return priceCollection;
    }
}