package com.github.drpatvar.to;

import lombok.Value;
import javax.validation.constraints.NotNull;


@Value
public class VoiceTo extends BaseTo {

    private boolean enabled;

    @NotNull
    private Integer restaurantId;

    public VoiceTo(Integer id, boolean enabled, Integer restaurantId) {
        super(id);
        this.enabled = enabled;
        this.restaurantId = restaurantId;
    }
}
