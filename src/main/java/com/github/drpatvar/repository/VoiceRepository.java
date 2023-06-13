package com.github.drpatvar.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import com.github.drpatvar.model.Voice;

import java.util.List;

@Transactional(readOnly = true)
public interface VoiceRepository extends BaseRepository<Voice> {

    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT v FROM Voice v WHERE v.id = ?1 AND v.user.id = ?2")
    Voice getWithUser(int id, int userId);

    @EntityGraph(attributePaths = {"user"}, type = EntityGraph.EntityGraphType.LOAD)
    @Query("SELECT v FROM Voice v join FETCH v.user")
    List<Voice> findVoices();

    @Query("SELECT v FROM Voice v WHERE v.user.id = ?1")
    List<Voice> getAllByUserVoice(int userId);
}
