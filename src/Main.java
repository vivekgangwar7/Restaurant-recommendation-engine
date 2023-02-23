import com.restaurant.recommendation.engine.controller.RestaurantController;
import com.restaurant.recommendation.engine.enums.Cuisine;
import com.restaurant.recommendation.engine.model.CostTracking;
import com.restaurant.recommendation.engine.model.CuisineTracking;
import com.restaurant.recommendation.engine.model.Restaurant;
import com.restaurant.recommendation.engine.model.User;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        RestaurantController restaurantController = new RestaurantController();
        CuisineTracking[] cuisineTrackings =
                {       new CuisineTracking(Cuisine.SOUTH_INDIAN, "10"),
                        new CuisineTracking(Cuisine.SOUTH_INDIAN, "12"),
                        new CuisineTracking(Cuisine.NORTH_INDIAN, "1"),
                        new CuisineTracking(Cuisine.NORTH_INDIAN, "2"),
                        new CuisineTracking(Cuisine.CHINESE, "5"),
                        new CuisineTracking(Cuisine.CHINESE, "3"),
                        new CuisineTracking(Cuisine.SOUTH_INDIAN, "12"),
                        new CuisineTracking(Cuisine.CHINESE, "15")
                };

        CostTracking[] costTrackings =
                {       new CostTracking(3, "10"),
                        new CostTracking(1, "12"),
                        new CostTracking(2, "1"),
                        new CostTracking(3, "2"),
                        new CostTracking(1, "5"),
                        new CostTracking(1, "3"),
                        new CostTracking(2, "12"),
                        new CostTracking(5, "15")
                };

        User user = new User("1", "John", cuisineTrackings, costTrackings);

        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant("restaurant1", Cuisine.SOUTH_INDIAN, 1, 5f, true, Instant.now().minus(100, ChronoUnit.HOURS)));
        restaurants.add(new Restaurant("restaurant2", Cuisine.NORTH_INDIAN, 2, 2.5f, false, Instant.now().minus(300, ChronoUnit.HOURS)));
        restaurants.add(new Restaurant("restaurant3", Cuisine.SOUTH_INDIAN, 3, 3f, false, Instant.now().minus(200, ChronoUnit.HOURS)));
        restaurants.add(new Restaurant("restaurant4", Cuisine.CHINESE, 2, 4f, false, Instant.now().minus(50, ChronoUnit.HOURS)));
        restaurants.add(new Restaurant("restaurant5", Cuisine.NORTH_INDIAN, 3, 4.7f, true, Instant.now().minus(10, ChronoUnit.HOURS)));
        restaurants.add(new Restaurant("restaurant6", Cuisine.CHINESE, 4, 3.5f, false, Instant.now().minus(11, ChronoUnit.HOURS)));
        restaurants.add(new Restaurant("restaurant7", Cuisine.SOUTH_INDIAN, 3, 4.1f, false, Instant.now().minus(36, ChronoUnit.HOURS)));
        restaurants.add(new Restaurant("restaurant8", Cuisine.CHINESE, 3, 4.8f, false, Instant.now().minus(200, ChronoUnit.MINUTES)));
        restaurants.add(new Restaurant("restaurant9", Cuisine.SOUTH_INDIAN, 2, 4.6f, false, Instant.now().minus(110, ChronoUnit.HOURS)));
        restaurants.add(new Restaurant("restaurant10", Cuisine.NORTH_INDIAN, 1, 3.9f, false, Instant.now().minus(80, ChronoUnit.HOURS)));
        restaurants.add(new Restaurant("restaurant11", Cuisine.SOUTH_INDIAN, 4, 3.0f, false, Instant.now().minus(60, ChronoUnit.HOURS)));
        restaurants.add(new Restaurant("restaurant12", Cuisine.CHINESE, 5, 4.6f, true, Instant.now().minus(10000, ChronoUnit.HOURS)));
        restaurants.add(new Restaurant("restaurant13", Cuisine.NORTH_INDIAN, 3, 4.5f, true, Instant.now().minus(350, ChronoUnit.HOURS)));
        restaurants.add(new Restaurant("restaurant14", Cuisine.CHINESE, 5, 4.3f, false, Instant.now().minus(100, ChronoUnit.HOURS)));
        restaurants.add(new Restaurant("restaurant15", Cuisine.SOUTH_INDIAN, 2, 3.4f, true, Instant.now().minus(200, ChronoUnit.HOURS)));
        restaurants.add(new Restaurant("restaurant16", Cuisine.NORTH_INDIAN, 1, 4.9f, false, Instant.now().minus(110, ChronoUnit.HOURS)));
        restaurants.add(new Restaurant("restaurant17", Cuisine.SOUTH_INDIAN, 2, 4.3f, true, Instant.now().minus(9, ChronoUnit.HOURS)));
        restaurants.add(new Restaurant("restaurant18", Cuisine.NORTH_INDIAN, 4, 4.4f, false, Instant.now().minus(360, ChronoUnit.HOURS)));
        restaurants.add(new Restaurant("restaurant19", Cuisine.SOUTH_INDIAN, 2, 3.8f, false, Instant.now().minus(230, ChronoUnit.HOURS)));
        restaurants.add(new Restaurant("restaurant20", Cuisine.CHINESE, 3, 5f, true, Instant.now().minus(340, ChronoUnit.HOURS)));

        System.out.println("List of Restaurants : " + restaurantController.getRestaurantRecommendations(user, restaurants));
    }
}
