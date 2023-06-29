package com.github.drpatvar.web.menu;

import com.github.drpatvar.model.Menu;
import com.github.drpatvar.to.MenuTo;
import com.github.drpatvar.web.MatcherFactory;

import java.time.LocalDate;

public class MenuTestData {

    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Menu.class, "date", "dishes", "restaurant");

    public static final int MENU_ID = 1;
    public static final int RESTAURANT_ID = 1;
    public static final LocalDate date = LocalDate.now();

    public static final Menu menu1 = new Menu(MENU_ID, "cheap");
    public static final Menu menu2 = new Menu(MENU_ID + 1, "expensive");

    public static MenuTo getNewTo(){
        return new MenuTo(null, "newMenu", date, RESTAURANT_ID);
    }

    public static MenuTo updatedTo(){
        return new MenuTo(MENU_ID, "updatedMenu", date, RESTAURANT_ID);
    }
}
