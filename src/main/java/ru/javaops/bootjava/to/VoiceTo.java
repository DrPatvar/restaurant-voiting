package ru.javaops.bootjava.to;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.javaops.bootjava.HasId;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class VoiceTo implements Serializable, HasId {

    private Integer id;

    private boolean enabled;

    private Integer restaurantId;
}
