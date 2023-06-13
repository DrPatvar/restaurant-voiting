package com.github.drpatvar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;
import com.github.drpatvar.HasId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serial;
import java.io.Serializable;

@Entity
@Table(name = "meal")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Meal extends NamedEntity implements HasId, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Column(name = "price", nullable = false)
    @NotNull
    @Range(min = 5, max = 9999)
    private Integer price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Menu menu;

    public Meal(Integer id, String name, Integer price) {
        super(id, name);
        this.price = price;
    }

}