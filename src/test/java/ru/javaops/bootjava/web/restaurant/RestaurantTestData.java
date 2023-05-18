package ru.javaops.bootjava.web.restaurant;

import ru.javaops.bootjava.model.Restaurant;
import ru.javaops.bootjava.web.MatcherFactory;

public class RestaurantTestData {

    public static final MatcherFactory.Matcher<Restaurant> RESTAURANT_MATCHER = MatcherFactory.usingEqualsComparator(Restaurant.class);

    public static final int RESTAURANT_ID = 1;

    public static final Restaurant restaurant1 = new Restaurant(RESTAURANT_ID, "4 stars");
    public static final Restaurant restaurant2 = new Restaurant(RESTAURANT_ID + 1, "5 stars");

    public static Restaurant getNew(){
        return new Restaurant(null, "newName");
    }

    public static Restaurant updated(){
        return new Restaurant(RESTAURANT_ID, "updatedName");
    }
}
