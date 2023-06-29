package com.github.drpatvar.web.menu;

import com.github.drpatvar.error.IllegalRequestDataException;
import com.github.drpatvar.model.Dish;
import com.github.drpatvar.model.Menu;
import com.github.drpatvar.repository.MenuRepository;
import com.github.drpatvar.repository.RestaurantRepository;
import com.github.drpatvar.service.MenuService;
import com.github.drpatvar.to.MenuTo;
import com.github.drpatvar.util.MenuUtil;
import com.github.drpatvar.util.validation.ValidationUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static com.github.drpatvar.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@Slf4j
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {

    static final String REST_URL = "/api/menus";

    @Autowired
    protected MenuRepository menuRepository;

    @Autowired
    protected MenuService menuService;

    @Autowired
    protected RestaurantRepository restaurantRepository;

    @GetMapping("/{id}")
    public Menu get(@PathVariable int id) {
        log.info("getMenu");
        return menuRepository.get(id);
    }

    @GetMapping("/{id}/dishes")
    public Menu getDishes(@PathVariable int id) {
        log.info("getMenu");
        return menuRepository.findMenuByDishes(id);
    }

    @GetMapping("/restaurantId/{id}")
    public Menu getTodayByRestaurant(@PathVariable int id){
        log.info("getMenu restaurantId");
        Menu menu = menuRepository.findMenu(id, LocalDate.now());
        if (menu == null){
            throw new IllegalRequestDataException("there is no menu for today");
        }
        return menu;
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        log.info("delete menu");
        menuRepository.delete(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Menu menu, @PathVariable int id) {
        log.info("update menu");
        assureIdConsistent(menu, id);
        menuRepository.save(menu);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PutMapping("/{menuId}/dishes")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addDishes(@PathVariable int menuId, @RequestParam Integer[] dishes) {
        log.info("update menu");
        menuService.addDish(menuId, dishes);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@Valid @RequestBody MenuTo menuTo) {
        log.info("create menu {}", menuTo);
        ValidationUtil.checkNew(menuTo);
        Menu menu = MenuUtil.createNewFromTo(menuTo);
        menu.setRestaurant(restaurantRepository.getReferenceById(menuTo.getRestaurantId()));

        Menu created = menuRepository.save(menu);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }


}
