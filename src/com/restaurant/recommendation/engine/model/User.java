package com.restaurant.recommendation.engine.model;

public class User {

    private String userId;
    private String userName;
    private CuisineTracking[] cuisineTrackings;
    private CostTracking[] costTrackings;

    public User(String userId, String userName, CuisineTracking[] cuisineTrackings, CostTracking[] costTrackings) {
        this.userId = userId;
        this.userName = userName;
        this.cuisineTrackings = cuisineTrackings;
        this.costTrackings = costTrackings;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public CostTracking[] getCostTrackings() {
        return costTrackings;
    }

    public CuisineTracking[] getCuisineTrackings() {
        return cuisineTrackings;
    }

    public void setCostTrackings(CostTracking[] costTrackings) {
        this.costTrackings = costTrackings;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setCuisineTrackings(CuisineTracking[] cuisineTrackings) {
        this.cuisineTrackings = cuisineTrackings;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
