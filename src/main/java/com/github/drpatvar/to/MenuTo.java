package com.github.drpatvar.to;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class MenuTo extends BaseTo {

    String name;

    @NotNull Integer restaurantId;

    LocalDate date;

    Integer[] dishes;

    public MenuTo(){}

    public MenuTo(Integer id,String name, LocalDate date, Integer restaurantId) {
        super(id);
        this.name = name;
        this.date = date;
        this.restaurantId = restaurantId;
    }

    public MenuTo(Integer menuId, String updatedMenu) {
        super(menuId);
        this.name = updatedMenu;
    }
}
