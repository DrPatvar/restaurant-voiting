package com.github.drpatvar.web.menu;

import com.github.drpatvar.to.MenuTo;
import com.github.drpatvar.util.MenuUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.github.drpatvar.model.Menu;
import com.github.drpatvar.repository.MenuRepository;
import com.github.drpatvar.util.JsonUtil;
import com.github.drpatvar.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.github.drpatvar.TestUtil.userHttpBasic;
import static com.github.drpatvar.web.menu.MenuTestData.*;
import static com.github.drpatvar.web.user.UserTestData.admin;

class MenuControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MenuController.REST_URL + '/';

    @Autowired
    protected MenuRepository menuRepository;

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MENU_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + MENU_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MENU_MATCHER.contentJson(menu1));
    }

    @Test
    void delete() throws Exception{
        perform(MockMvcRequestBuilders.delete(REST_URL + MENU_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isNoContent());
    }

    @Test
    void update() throws Exception {
        MenuTo updatedTo = MenuTestData.updatedTo();
        perform(MockMvcRequestBuilders.put(REST_URL + MENU_ID).contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin))
                .content(JsonUtil.writeValue(updatedTo)))
                .andExpect(status().isNoContent());
        Menu menu = MenuUtil.updateFromTo(updatedTo);
        MENU_MATCHER.assertMatch(menuRepository.get(MENU_ID), menu);
    }

    @Test
    void createWithLocation() throws Exception{
        MenuTo newMenuTo = MenuTestData.getNewTo();
        Menu newMenu = MenuUtil.createNewFromTo(newMenuTo);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .with(userHttpBasic(admin))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newMenuTo)))
                .andExpect(status().isCreated())
                .andDo(print());

        Menu created = MENU_MATCHER.readFromJson(action);
        int newId = created.id();
        newMenu.setId(newId);
        MENU_MATCHER.assertMatch(created, newMenu);
        MENU_MATCHER.assertMatch(menuRepository.get(newId), newMenu);
    }
}