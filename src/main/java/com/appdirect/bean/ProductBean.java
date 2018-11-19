package com.appdirect.bean;

public class ProductBean {

    private long id;
    private String name;
    private double basePrice;
    private double averageStorePrice;
    private double lowestStorePrice;
    private double highestStorePrice;
    private double idealStorePrice;
    private int numberOfPriceCollect;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public double getAverageStorePrice() {
        return averageStorePrice;
    }

    public void setAverageStorePrice(double averageStorePrice) {
        this.averageStorePrice = averageStorePrice;
    }

    public double getLowestStorePrice() {
        return lowestStorePrice;
    }

    public void setLowestStorePrice(double lowestStorePrice) {
        this.lowestStorePrice = lowestStorePrice;
    }

    public double getHighestStorePrice() {
        return highestStorePrice;
    }

    public void setHighestStorePrice(double highestStorePrice) {
        this.highestStorePrice = highestStorePrice;
    }

    public double getIdealStorePrice() {
        return idealStorePrice;
    }

    public void setIdealStorePrice(double idealStorePrice) {
        this.idealStorePrice = idealStorePrice;
    }

    public int getNumberOfPriceCollect() {
        return numberOfPriceCollect;
    }

    public void setNumberOfPriceCollect(int numberOfPriceCollect) {
        this.numberOfPriceCollect = numberOfPriceCollect;
    }
}
