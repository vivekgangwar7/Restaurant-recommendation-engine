package com.restaurant.recommendation.engine.helper;

import com.restaurant.recommendation.engine.enums.Cuisine;
import com.restaurant.recommendation.engine.model.Restaurant;
import com.restaurant.recommendation.engine.model.User;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class RestaurantUserHelper {

    private final Cuisine primaryCuisine;
    private final Cuisine secondaryCuisine_1;
    private final Cuisine secondaryCuisine_2;

    private final Integer primaryCostBracket;
    private final Integer secondaryCostBracket_1;
    private final Integer secondaryCostBracket_2;

    private final Set<String> visitedRestaurants;
    private final List<Restaurant> availableRestaurants;

    public RestaurantUserHelper(User user, List<Restaurant> availableRestaurants) {
        this.primaryCuisine = user.getCuisineTrackings()[0].getType();
        this.secondaryCuisine_1 = user.getCuisineTrackings()[1].getType();
        this.secondaryCuisine_2 = user.getCuisineTrackings()[2].getType();

        this.primaryCostBracket = user.getCostTrackings()[0].getCostBracket();
        this.secondaryCostBracket_1 = user.getCostTrackings()[1].getCostBracket();
        this.secondaryCostBracket_2 = user.getCostTrackings()[2].getCostBracket();

        this.visitedRestaurants = new HashSet<>();
        this.availableRestaurants = availableRestaurants;
    }

    // Condition 1 : Featured restaurants of primary cuisine and primary cost bracket.
    // If none, then all featured restaurants of primary cuisine, secondary cost and secondary
    // cuisine, primary cost
    public List<Restaurant> getFeaturedRestaurants() {
        List<Restaurant> filteredRestaurants = new ArrayList<>();

        for (Restaurant restaurant : availableRestaurants) {
            Cuisine restCuisine = restaurant.getCuisine();
            Integer restCostBracket = restaurant.getCostBracket();
            if (!visitedRestaurants.contains(restaurant.getRestaurantId()) && restaurant.getRecommended()) {
                if ((restCuisine.equals(primaryCuisine) && restCostBracket.equals(primaryCostBracket))) {
                    //System.out.println("Condition 1, Block 1, " + restaurant.getRestaurantId());
                    filteredRestaurants.add(restaurant);
                    visitedRestaurants.add(restaurant.getRestaurantId());
                }
            }
        }

        if (visitedRestaurants.size() == 0) {
            for (Restaurant restaurant : availableRestaurants) {
                Cuisine restCuisine = restaurant.getCuisine();
                Integer restCostBracket = restaurant.getCostBracket();
                if (!visitedRestaurants.contains(restaurant.getRestaurantId()) && restaurant.getRecommended()) {
                    if (restCuisine.equals(primaryCuisine) &&
                            (restCostBracket.equals(secondaryCostBracket_1) || restCostBracket.equals(secondaryCostBracket_2))) {
                        //System.out.println("Condition 1, Block 2, " + restaurant.getRestaurantId());
                        filteredRestaurants.add(restaurant);
                        visitedRestaurants.add(restaurant.getRestaurantId());
                    }
                }
            }

            for (Restaurant restaurant : availableRestaurants) {
                Cuisine restCuisine = restaurant.getCuisine();
                Integer restCostBracket = restaurant.getCostBracket();
                if (!visitedRestaurants.contains(restaurant.getRestaurantId()) && restaurant.getRecommended()) {
                    if ((restCuisine.equals(secondaryCuisine_1) || (restCuisine.equals(secondaryCuisine_2)))
                            && restCostBracket.equals(primaryCostBracket)) {
                        //System.out.println("Condition 1, Block 3, " + restaurant.getRestaurantId());
                        filteredRestaurants.add(restaurant);
                        visitedRestaurants.add(restaurant.getRestaurantId());
                    }
                }
            }
        }

        return filteredRestaurants.stream()
                .sorted(Comparator.comparing(Restaurant::getRating).reversed())
                .collect(Collectors.toList());
    }

    // Condition 2: All restaurants of Primary cuisine, primary cost bracket with rating >= 4
    public List<Restaurant> getPrimaryCuisinePrimaryCostBracketGreater(Float value) {
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        for (Restaurant restaurant : availableRestaurants) {
            Cuisine restCuisine = restaurant.getCuisine();
            Integer restCostBracket = restaurant.getCostBracket();
            if (!visitedRestaurants.contains(restaurant.getRestaurantId())) {
                if (restCuisine.equals(primaryCuisine) && restCostBracket.equals(primaryCostBracket)
                        && restaurant.getRating() >= value) {
                    //System.out.println("Condition 2, " + restaurant.getRestaurantId());
                    filteredRestaurants.add(restaurant);
                    visitedRestaurants.add(restaurant.getRestaurantId());
                }
            }
        }

        return filteredRestaurants.stream()
                .sorted(Comparator.comparing(Restaurant::getRating).reversed())
                .collect(Collectors.toList());
    }

    // Condition 3: All restaurants of Primary cuisine, secondary cost bracket with rating >= 4.5
    public List<Restaurant> getPrimaryCuisineSecondaryCostBracketGreater(Float value) {
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        for (Restaurant restaurant : availableRestaurants) {
            Cuisine restCuisine = restaurant.getCuisine();
            Integer restCostBracket = restaurant.getCostBracket();
            if (!visitedRestaurants.contains(restaurant.getRestaurantId())) {
                if (restCuisine.equals(primaryCuisine)
                        && (restCostBracket.equals(secondaryCostBracket_1) || restCostBracket.equals(secondaryCostBracket_2))
                        && restaurant.getRating() >= value) {
                    //System.out.println("Condition 3, " + restaurant.getRestaurantId());
                    filteredRestaurants.add(restaurant);
                    visitedRestaurants.add(restaurant.getRestaurantId());
                }
            }
        }
        return filteredRestaurants.stream()
                .sorted(Comparator.comparing(Restaurant::getRating).reversed())
                .collect(Collectors.toList());
    }

    // Condition 4: All restaurants of secondary cuisine, primary cost bracket with rating >= 4.5
    public List<Restaurant> getSecondaryCuisinePrimaryCostBracketGreater(Float value) {
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        for (Restaurant restaurant : availableRestaurants) {
            Cuisine restCuisine = restaurant.getCuisine();
            Integer restCostBracket = restaurant.getCostBracket();
            if (!visitedRestaurants.contains(restaurant.getRestaurantId())) {
                if ((restCuisine.equals(secondaryCuisine_1) || (restCuisine.equals(secondaryCuisine_2)))
                        && restCostBracket.equals(primaryCostBracket)
                        && restaurant.getRating() >= value) {
                    //System.out.println("Condition 4, " + restaurant.getRestaurantId());
                    filteredRestaurants.add(restaurant);
                    visitedRestaurants.add(restaurant.getRestaurantId());
                }
            }
        }
        return filteredRestaurants.stream()
                .sorted(Comparator.comparing(Restaurant::getRating).reversed())
                .collect(Collectors.toList());
    }

    // Condition 5: Top 4 newly created restaurants by rating
    public List<Restaurant> getNewRestaurants() {
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        for (Restaurant restaurant : availableRestaurants) {
            boolean isWithin48hrs = restaurant.getOnboardedTime().isBefore(Instant.now().minus(48, ChronoUnit.HOURS));
            if (!visitedRestaurants.contains(restaurant.getRestaurantId()) && isWithin48hrs) {
                //System.out.println("Condition 5, " + restaurant.getRestaurantId());
                filteredRestaurants.add(restaurant);
                visitedRestaurants.add(restaurant.getRestaurantId());
            }
        }
        return filteredRestaurants.stream()
                .sorted(Comparator.comparing(Restaurant::getRating).reversed())
                .collect(Collectors.toList());
    }

    // Condition 6: All restaurants of Primary cuisine, primary cost bracket with rating < 4
    public List<Restaurant> getPrimaryCuisinePrimaryCostBracketLesser(Float value) {
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        for (Restaurant restaurant : availableRestaurants) {
            Cuisine restCuisine = restaurant.getCuisine();
            Integer restCostBracket = restaurant.getCostBracket();
            if (!visitedRestaurants.contains(restaurant.getRestaurantId())) {
                if (restCuisine.equals(primaryCuisine) && restCostBracket.equals(primaryCostBracket)
                        && restaurant.getRating() < value) {
                    //System.out.println("Condition 6, " + restaurant.getRestaurantId());
                    filteredRestaurants.add(restaurant);
                    visitedRestaurants.add(restaurant.getRestaurantId());
                }
            }
        }
        return filteredRestaurants.stream()
                .sorted(Comparator.comparing(Restaurant::getRating).reversed())
                .collect(Collectors.toList());
    }

    // Condition 7: All restaurants of Primary cuisine, secondary cost bracket with rating < 4.5
    public List<Restaurant> getPrimaryCuisineSecondaryCostBracketLesser(Float value) {
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        for (Restaurant restaurant : availableRestaurants) {
            Cuisine restCuisine = restaurant.getCuisine();
            Integer restCostBracket = restaurant.getCostBracket();
            if (!visitedRestaurants.contains(restaurant.getRestaurantId())) {
                if (restCuisine.equals(primaryCuisine)
                        && (restCostBracket.equals(secondaryCostBracket_1) || restCostBracket.equals(secondaryCostBracket_2))
                        && restaurant.getRating() < value) {
                    //System.out.println("Condition 7, " + restaurant.getRestaurantId());
                    filteredRestaurants.add(restaurant);
                    visitedRestaurants.add(restaurant.getRestaurantId());
                }
            }
        }
        return filteredRestaurants.stream()
                .sorted(Comparator.comparing(Restaurant::getRating).reversed())
                .collect(Collectors.toList());
    }

    // Condition 8: All restaurants of secondary cuisine, primary cost bracket with rating < 4.5
    public List<Restaurant> getSecondaryCuisinePrimaryCostBracketLesser(Float value) {
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        for (Restaurant restaurant : availableRestaurants) {
            Cuisine restCuisine = restaurant.getCuisine();
            Integer restCostBracket = restaurant.getCostBracket();
            if (!visitedRestaurants.contains(restaurant.getRestaurantId())) {
                if ((restCuisine.equals(secondaryCuisine_1) || (restCuisine.equals(secondaryCuisine_2)))
                        && restCostBracket.equals(primaryCostBracket)
                        && restaurant.getRating() < value) {
                    //System.out.println("Condition 8, " + restaurant.getRestaurantId());
                    filteredRestaurants.add(restaurant);
                    visitedRestaurants.add(restaurant.getRestaurantId());
                }
            }
        }
        return filteredRestaurants.stream()
                .sorted(Comparator.comparing(Restaurant::getRating).reversed())
                .collect(Collectors.toList());
    }

    // Condition 9: All restaurants of any cuisine, any cost bracket
    public List<Restaurant> getRemainingRestaurants() {
        List<Restaurant> filteredRestaurants = new ArrayList<>();
        for (Restaurant restaurant : availableRestaurants) {
            if (!visitedRestaurants.contains(restaurant.getRestaurantId())) {
                //System.out.println("Condition 9, " + restaurant.getRestaurantId());
                filteredRestaurants.add(restaurant);
                visitedRestaurants.add(restaurant.getRestaurantId());
            }
        }
        return filteredRestaurants.stream()
                .sorted(Comparator.comparing(Restaurant::getRating).reversed())
                .collect(Collectors.toList());
    }

}
