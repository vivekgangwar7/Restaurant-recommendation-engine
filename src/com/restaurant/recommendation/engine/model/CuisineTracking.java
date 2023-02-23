package com.restaurant.recommendation.engine.model;

import com.restaurant.recommendation.engine.enums.Cuisine;

public class CuisineTracking {

    private Cuisine type;
    private String numberOfOrders;

    public CuisineTracking(Cuisine type, String numberOfOrders) {
        this.type = type;
        this.numberOfOrders = numberOfOrders;
    }

    public Cuisine getType() {
        return type;
    }

    public void setType(Cuisine type) {
        this.type = type;
    }

    public String getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(String numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }
}
