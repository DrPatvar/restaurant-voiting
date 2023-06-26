package com.github.drpatvar.web.menu;

import com.github.drpatvar.error.AppException;
import com.github.drpatvar.error.IllegalRequestDataException;
import com.github.drpatvar.to.MenuTo;
import com.github.drpatvar.util.MenuUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.github.drpatvar.model.Menu;
import com.github.drpatvar.repository.MenuRepository;
import com.github.drpatvar.repository.RestaurantRepository;
import com.github.drpatvar.util.validation.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalDate;

import static com.github.drpatvar.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@Slf4j
@RequestMapping(value = MenuController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {

    static final String REST_URL = "/api/menus";

    @Autowired
    protected MenuRepository menuRepository;

    @Autowired
    protected RestaurantRepository restaurantRepository;

    @GetMapping("/{id}")
    public Menu get(@PathVariable int id){
        log.info("getMenu");
        return menuRepository.get(id);
    }

    @GetMapping("/restaurantId/{id}")
    public Menu getTodayByRestaurant(@PathVariable int id){
        log.info("getMenu restaurantId");
        Menu menu = menuRepository.findMenu(id, LocalDate.now());
        if (menu == null){
            throw new AppException(HttpStatus.NO_CONTENT, "", null);
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
    public void update(@Valid @RequestBody Menu menu, @PathVariable int id){
        log.info("update menu");
        assureIdConsistent(menu, id);
        menuRepository.save(menu);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Menu> createWithLocation(@Valid @RequestBody MenuTo menuTo){
        log.info("create menu {}", menuTo);
        ValidationUtil.checkNew(menuTo);
        Menu menu  = MenuUtil.createNewFromTo(menuTo);
        menu.setRestaurant(restaurantRepository.getReferenceById(menuTo.getRestaurantId()));

        Menu created = menuRepository.save(menu);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }


}
