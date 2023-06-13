package com.github.drpatvar;

import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import com.github.drpatvar.model.User;

public class TestUtil {

    public static RequestPostProcessor userHttpBasic(User user){
        return SecurityMockMvcRequestPostProcessors.httpBasic(user.getEmail(), user.getPassword());
    }
}
