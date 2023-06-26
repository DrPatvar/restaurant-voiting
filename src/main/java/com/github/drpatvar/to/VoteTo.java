package com.github.drpatvar.to;

import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import javax.validation.constraints.NotNull;


@Value
@Getter
@Setter
public class VoteTo extends BaseTo {

    @NotNull Integer restaurantId;

    public VoteTo(Integer id, Integer restaurantId) {
        super(id);
        this.restaurantId = restaurantId;
    }
}
