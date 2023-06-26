package com.github.drpatvar.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "vote", uniqueConstraints = {@UniqueConstraint(columnNames = {"votingDate", "user_id"},
        name = "vote_unique_user_date_idx")})
@Getter
@Setter
public class Vote extends BaseEntity {

    @Column(name = "votingDate", nullable = false, columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDate votingDate = LocalDate.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"name", "email", "enabled", "registered", "roles"})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"name"})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Restaurant restaurant;

    public Vote() {
    }

    public Vote(Integer id, LocalDate now, User user, Restaurant restaurant) {
        super(id);
        this.votingDate = now;
        this.user = user;
        this.restaurant = restaurant;
    }

   /* public Vote(Integer id, int restaurantId) {
        super(id);
    }*/

    public Vote(Integer id) {
        super(id);
    }
}
