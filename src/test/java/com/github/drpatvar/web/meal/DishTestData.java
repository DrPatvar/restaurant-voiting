package com.github.drpatvar.web.meal;

import com.github.drpatvar.model.Dish;
import com.github.drpatvar.web.MatcherFactory;

import java.util.List;

public class DishTestData {

    public static final MatcherFactory.Matcher<Dish> MEAL_MATCHER = MatcherFactory.usingEqualsComparator(Dish.class);

    public static final int MEAL1_ID = 1;

    public static final int MENU_ID = 1;

    public static final Dish DISH_1 = new Dish(MEAL1_ID, "Soup", 50);
    public static final Dish DISH_2 = new Dish(MEAL1_ID + 1, "Coffee", 50);
    public static final Dish DISH_3 = new Dish(MEAL1_ID + 2, "Bread", 5);
    public static final Dish DISH_4 = new Dish(MEAL1_ID + 3, "Soup", 100);
    public static final Dish DISH_5 = new Dish(MEAL1_ID + 4, "Tea", 100);
    public static final Dish DISH_6 = new Dish(MEAL1_ID + 5, "Bread", 10);

    public static List<Dish> dishes = List.of(DISH_1, DISH_2, DISH_3, DISH_4, DISH_5, DISH_6);

    public static List<Dish> mealsMenus = List.of(DISH_1, DISH_2, DISH_3);

    public static Dish getNew() {
        return new Dish(null, "newMeal", 33);
    }

    public static Dish getUpdated() {
        return new Dish(MEAL1_ID, "обновленная еда", 7);
    }


}
