package com.github.drpatvar.repository;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.github.drpatvar.model.User;
import com.github.drpatvar.model.Voice;

import java.util.Optional;

@Transactional(readOnly = true)
@Tag(name = "User Controller")
public interface UserRepository extends BaseRepository<User> {

    @Query("SELECT u FROM User u WHERE u.email = LOWER(:email)")
    Optional<User> findByEmailIgnoreCase(String email);


    @Query("SELECT v FROM Voice v JOIN FETCH v.user WHERE v.user.id = ?1")
    Optional<Voice> findAllVoice(int userId);
}