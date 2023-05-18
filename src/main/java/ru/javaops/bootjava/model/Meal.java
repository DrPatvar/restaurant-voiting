package ru.javaops.bootjava.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.validator.constraints.Range;
import ru.javaops.bootjava.HasId;

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
    @JoinColumn(name = "menu_id")
    @JsonIgnore
    private Menu menu;

    public Meal(Integer id, String name, Integer price) {
        super(id, name);
        this.price = price;
    }

}
