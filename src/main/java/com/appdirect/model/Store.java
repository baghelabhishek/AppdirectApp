package com.appdirect.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "store")
public class Store implements Serializable {


    private long id;


    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    private Set<PriceCollection> priceCollections = new HashSet<>();

    @OneToMany(mappedBy = "store")
    public Set<PriceCollection> getPriceCollections() {
        return priceCollections;
    }

    public void setPriceCollections(Set<PriceCollection> priceCollections) {
        this.priceCollections = priceCollections;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Store store = (Store) o;
        return Objects.equals(id, store.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
