package ru.javaops.bootjava.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.web.AbstractControllerTest;

import static org.junit.jupiter.api.Assertions.*;

class RestaurantControllerTest extends AbstractControllerTest {

    @Autowired
    protected RestaurantRepository restaurantRepository;

    @Test
    void get() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void createWithLocation() {
    }
}