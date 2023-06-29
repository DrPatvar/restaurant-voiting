package com.github.drpatvar.util;

import com.github.drpatvar.model.Menu;
import com.github.drpatvar.to.MenuTo;

public class MenuUtil {

    public static Menu createNewFromTo (MenuTo menuTo){
        return new Menu(null, menuTo.getName(), menuTo.getDate());
    }

    public static Menu updateFromTo (MenuTo menuTo){
        return new Menu(menuTo.getId(), menuTo.getName(), menuTo.getDate());
    }
}
