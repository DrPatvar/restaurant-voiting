package com.github.drpatvar.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.github.drpatvar.model.Meal;

import java.util.List;

@Transactional(readOnly = true)
public interface MealRepository extends BaseRepository<Meal> {

    @Query("SELECT m FROM Meal m WHERE m.menu.id = :id")
    List<Meal> findByIdMenu(Integer id);
}
