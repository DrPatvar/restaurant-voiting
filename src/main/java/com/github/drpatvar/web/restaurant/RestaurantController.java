package com.github.drpatvar.web.restaurant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.github.drpatvar.model.Restaurant;
import com.github.drpatvar.repository.RestaurantRepository;
import com.github.drpatvar.util.validation.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.github.drpatvar.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@Slf4j
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    static final String REST_URL = "/api/admin/restaurants";

    @Autowired
    protected RestaurantRepository restaurantRepository;

    @GetMapping("/{id}")
    public Restaurant get (@PathVariable int id){
        log.info("get restaurant");
        return restaurantRepository.get(id);
    }

    @GetMapping
    public List<Restaurant> getAll(){
        log.info("getAll restaurant");
        return restaurantRepository.findAll();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody Restaurant restaurant, @PathVariable int id){
        log.info("update restaurant");
        assureIdConsistent(restaurant, id);
        restaurantRepository.save(restaurant);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id){
        log.info("delete restaurant");
        restaurantRepository.delete(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> createWithLocation (@Valid @RequestBody Restaurant restaurant){
        log.info("create restaurant {}", restaurant);
        ValidationUtil.checkNew(restaurant);
        Restaurant created = restaurantRepository.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
