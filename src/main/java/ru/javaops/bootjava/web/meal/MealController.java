package ru.javaops.bootjava.web.meal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.bootjava.model.Meal;
import ru.javaops.bootjava.repository.MealRepository;
import ru.javaops.bootjava.repository.MenuRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static ru.javaops.bootjava.util.validation.ValidationUtil.assureIdConsistent;
import static ru.javaops.bootjava.util.validation.ValidationUtil.checkNew;

@RestController
@Slf4j
@RequestMapping(value = MealController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MealController {

    static final String REST_URL = "/api/meals";

    @Autowired
    protected MealRepository mealRepository;
    @Autowired
    protected MenuRepository menuRepository;

    @GetMapping
    public List<Meal> getAllMeals() {
        log.info("getAll");
        return mealRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<Meal> getAllMealMenu(@PathVariable int id) {
        log.info("getMealMenu");
        List<Meal> mealList = mealRepository.findByIdMenu(id);
        return mealList.isEmpty() ? new ArrayList<Meal>() : mealList;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete with id ={}", id);
        mealRepository.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Meal meal, @PathVariable int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        Meal mealWithDB = mealRepository.get(meal.id());
        meal.setMenu(mealWithDB.getMenu());
        mealRepository.save(meal);
    }

    @PostMapping(value = "/{menuId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@Valid @RequestBody Meal meal, @PathVariable int menuId) {
        log.info("create {}", meal);
        checkNew(meal);
        meal.setMenu(menuRepository.getReferenceById(menuId));
        Meal created = mealRepository.save(meal);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

}
