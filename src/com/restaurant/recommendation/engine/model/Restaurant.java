package com.restaurant.recommendation.engine.model;

import com.restaurant.recommendation.engine.enums.Cuisine;

import java.time.Instant;

public class Restaurant {

    private String restaurantId;
    private Cuisine cuisine;
    private Integer costBracket;
    private Float rating;
    private Boolean isRecommended;
    private Instant onboardedTime;

    public Restaurant(String restaurantId, Cuisine cuisine, Integer costBracket,
                      Float rating, Boolean isRecommended, Instant onboardedTime) {
        this.restaurantId = restaurantId;
        this.cuisine = cuisine;
        this.costBracket = costBracket;
        this.rating = rating;
        this.isRecommended = isRecommended;
        this.onboardedTime = onboardedTime;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Cuisine getCuisine() {
        return cuisine;
    }

    public void setCuisine(Cuisine cuisine) {
        this.cuisine = cuisine;
    }

    public Integer getCostBracket() {
        return costBracket;
    }

    public void setCostBracket(Integer costBracket) {
        this.costBracket = costBracket;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Boolean getRecommended() {
        return isRecommended;
    }

    public void setRecommended(Boolean recommended) {
        isRecommended = recommended;
    }

    public Instant getOnboardedTime() {
        return onboardedTime;
    }

    public void setOnboardedTime(Instant onboardedTime) {
        this.onboardedTime = onboardedTime;
    }
}
