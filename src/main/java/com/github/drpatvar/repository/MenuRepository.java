package com.github.drpatvar.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.github.drpatvar.model.Menu;

import java.time.LocalDate;
import java.util.Optional;

@Transactional(readOnly = true)
public interface MenuRepository extends BaseRepository<Menu> {

    @Query("SELECT m FROM Menu m WHERE m.restaurant.id = ?1 and m.date = ?2")
    Menu findMenu (int restaurantId, LocalDate date );
}
