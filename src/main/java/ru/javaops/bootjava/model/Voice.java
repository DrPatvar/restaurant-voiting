package ru.javaops.bootjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import ru.javaops.bootjava.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "voice")
@Getter
@Setter
public class Voice extends BaseEntity implements HasId, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "registered", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime registered = LocalDateTime.now();

    @Column(name = "enabled", nullable = false, columnDefinition = "bool default true")
    private boolean enabled = true;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"name", "email", "enabled", "registered", "roles"})
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnoreProperties({"name"})
    private Restaurant restaurant;

    public Voice(Integer id, boolean enabled, LocalDateTime now, User user, Restaurant restaurant) {
        super(id);
        this.enabled = enabled;
        this.registered = now;
        this.user = user;
        this.restaurant = restaurant;
    }

    public Voice() {
    }
}
