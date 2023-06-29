package com.github.drpatvar.model;

import com.github.drpatvar.HasId;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "dish")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Dish extends NamedEntity implements HasId {

    @Column(name = "price", nullable = false)
    @NotNull
    @Range
    private Integer price;

    public Dish(Integer id, String name, Integer price) {
        super(id, name);
        this.price = price;
    }
}
