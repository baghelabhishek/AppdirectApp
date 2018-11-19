package com.appdirect.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "product_store_mapping")
public class PriceCollection implements Serializable {

    private Store store;
    private Product product;
    private Double storePrice;

    @Id
    @ManyToOne
    @JoinColumn(name = "store_id")
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Id
    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "base_price")
    public Double getStorePrice() {
        return storePrice;
    }

    public void setStorePrice(Double storePrice) {
        this.storePrice = storePrice;
    }

}
