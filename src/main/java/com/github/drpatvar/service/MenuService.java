package com.github.drpatvar.service;

import com.github.drpatvar.model.Dish;
import com.github.drpatvar.model.Menu;
import com.github.drpatvar.repository.DishRepository;
import com.github.drpatvar.repository.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class MenuService {

    @Autowired
    protected MenuRepository menuRepository;

    @Autowired
    protected DishRepository dishRepository;

    @Transactional
    public void addDish(Integer menuId, Integer[] dishesId){
        Menu menu = menuRepository.get(menuId);
        List<Dish> dishes = new ArrayList<>();
        for (Integer id: dishesId){
            dishes.add(dishRepository.get(id));
        }
        menu.setDishes(dishes);
        menuRepository.save(menu);
    }
}
