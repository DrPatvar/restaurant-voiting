package ru.javaops.bootjava.web.meal;

import ru.javaops.bootjava.model.Meal;
import ru.javaops.bootjava.web.MatcherFactory;

import java.util.List;

public class MealTestData {

    public static final MatcherFactory.Matcher<Meal> MEAL_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Meal.class, "menu");

    public static final int MEAL1_ID = 1;

    public static final int MENU_ID = 1;

    public static final Meal meal1 = new Meal(MEAL1_ID, "Soup", 50);
    public static final Meal meal2 = new Meal(MEAL1_ID + 1, "Coffee", 50);
    public static final Meal meal3 = new Meal(MEAL1_ID + 2, "Bread", 5);
    public static final Meal meal4 = new Meal(MEAL1_ID + 3, "Soup", 100);
    public static final Meal meal5 = new Meal(MEAL1_ID + 4, "Tea", 100);
    public static final Meal meal6 = new Meal(MEAL1_ID + 5, "Bread", 10);

    public static List<Meal> meals = List.of(meal1, meal2, meal3, meal4, meal5, meal6);

    public static List<Meal> mealsMenu = List.of(meal1, meal2, meal3);

    public static Meal getNew() {
        return new Meal(null, "newMeal", 33);
    }

    public static Meal getUpdated() {
        return new Meal(MEAL1_ID, "обновленная еда", 7);
    }


}
