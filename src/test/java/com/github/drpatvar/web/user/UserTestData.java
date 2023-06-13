package com.github.drpatvar.web.user;

import com.github.drpatvar.model.Role;
import com.github.drpatvar.model.User;
import com.github.drpatvar.util.JsonUtil;
import com.github.drpatvar.web.MatcherFactory;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UserTestData {
    public static final MatcherFactory.Matcher<User> USER_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(User.class, "registered", "password");

    public static final int USER_ID = 1;
    public static final int ADMIN_ID = 6;
    public static final int NOT_FOUND = 100;
    public static final String USER_MAIL = "user1@yandex.ru";
    public static final String USER_MAIL2 = "user2@yandex.ru";
    public static final String USER_MAIL3 = "user3@yandex.ru";
    public static final String USER_MAIL4 = "user4@yandex.ru";
    public static final String USER_MAIL5 = "user5@yandex.ru";

    public static final String ADMIN_MAIL = "admin@yandex.ru";

    public static final User user = new User(USER_ID, "User1", USER_MAIL, "password", Role.USER);
    public static final User user2 = new User(USER_ID + 1, "User2", USER_MAIL2, "password", Role.USER);
    public static final User user3 = new User(USER_ID + 2, "User3", USER_MAIL3, "password", Role.USER);
    public static final User user4 = new User(USER_ID + 3, "User4", USER_MAIL4, "password", Role.USER);
    public static final User user5 = new User(USER_ID + 4, "User5", USER_MAIL5, "password", Role.USER);
    public static final User admin = new User(ADMIN_ID, "Admin", ADMIN_MAIL, "admin", Role.ADMIN, Role.USER);

    public static List<User> users = List.of(user,user2,user3,user4,user5);

    public static User getNew() {
        return new User(null, "New", "new@gmail.com", "newPass", false, new Date(), Collections.singleton(Role.USER));
    }

    public static User getUpdated() {
        return new User(USER_ID, "UpdatedName", USER_MAIL, "newPass", false, new Date(), Collections.singleton(Role.ADMIN));
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
