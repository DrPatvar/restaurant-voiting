package ru.javaops.bootjava;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import ru.javaops.bootjava.model.User;

public class TestUtil {

    public static RequestPostProcessor userHttpBasic(User user){
        return SecurityMockMvcRequestPostProcessors.httpBasic(user.getEmail(), user.getPassword());
    }
}
