package com.github.drpatvar.web.menu;

import com.github.drpatvar.model.Menu;
import com.github.drpatvar.web.MatcherFactory;

public class MenuTestData {

    public static final MatcherFactory.Matcher<Menu> MENU_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Menu.class, "registered", "meals", "restaurant");

    public static final int MENU_ID = 1;
    public static final int RESTAURANT_ID = 1;

    public static final Menu menu1 = new Menu(MENU_ID, "cheap");
    public static final Menu menu2 = new Menu(MENU_ID + 1, "expensive");

    public static Menu getNew(){
        return new Menu(null, "newMenu");
    }

    public static Menu updated(){
        return new Menu(MENU_ID, "updatedMenu");
    }
}
