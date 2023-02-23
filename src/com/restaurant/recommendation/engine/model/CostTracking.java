package com.restaurant.recommendation.engine.model;

public class CostTracking {

    private Integer costBracket;
    private String numberOfOrders;

    public CostTracking(Integer costBracket, String numberOfOrders) {
        this.costBracket = costBracket;
        this.numberOfOrders = numberOfOrders;
    }

    public Integer getCostBracket() {
        return costBracket;
    }

    public void setType(Integer costBracket) {
        this.costBracket = costBracket;
    }

    public String getNumberOfOrders() {
        return numberOfOrders;
    }

    public void setNumberOfOrders(String numberOfOrders) {
        this.numberOfOrders = numberOfOrders;
    }
}
