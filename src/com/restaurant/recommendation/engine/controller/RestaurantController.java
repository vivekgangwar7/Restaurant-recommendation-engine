package com.restaurant.recommendation.engine.controller;

import com.restaurant.recommendation.engine.helper.RestaurantUserHelper;
import com.restaurant.recommendation.engine.model.Restaurant;
import com.restaurant.recommendation.engine.model.User;

import java.util.ArrayList;
import java.util.List;

public class RestaurantController {

    // Takes user and restaurant while returning back array of restaurant Ids in the right sorting order
    public List<String> getRestaurantRecommendations(User user, List<Restaurant> availableRestaurants){
        RestaurantUserHelper restaurantUserHelper = new RestaurantUserHelper(user, availableRestaurants);
        List<Restaurant> restaurantList = new ArrayList<>();

        restaurantList.addAll(restaurantUserHelper.getFeaturedRestaurants());
        restaurantList.addAll(restaurantUserHelper.getPrimaryCuisinePrimaryCostBracketGreater(4f));
        restaurantList.addAll(restaurantUserHelper.getPrimaryCuisineSecondaryCostBracketGreater(4.5f));
        restaurantList.addAll(restaurantUserHelper.getSecondaryCuisinePrimaryCostBracketGreater(4.5f));
        restaurantList.addAll(restaurantUserHelper.getNewRestaurants());
        restaurantList.addAll(restaurantUserHelper.getPrimaryCuisinePrimaryCostBracketLesser(4f));
        restaurantList.addAll(restaurantUserHelper.getPrimaryCuisineSecondaryCostBracketLesser(4.5f));
        restaurantList.addAll(restaurantUserHelper.getSecondaryCuisinePrimaryCostBracketLesser(4.5f));
        restaurantList.addAll(restaurantUserHelper.getRemainingRestaurants());

        List<String> output = new ArrayList<>();
        for (Restaurant restaurant:restaurantList) {
            output.add(restaurant.getRestaurantId());
        }

        return output;
    }

}
