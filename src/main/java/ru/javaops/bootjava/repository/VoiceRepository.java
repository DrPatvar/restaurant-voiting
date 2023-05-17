package ru.javaops.bootjava.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.javaops.bootjava.model.Voice;

import java.util.List;

@Transactional(readOnly = true)
public interface VoiceRepository extends BaseRepository<Voice> {

    @Query("SELECT v FROM Voice v WHERE v.id = ?1 AND v.user.id = ?2")
    Voice getByIdWithUserId(int id, int userId);

    @Query("SELECT v FROM Voice v WHERE v.user.id = ?1")
    List<Voice> getAllByUserVoice(int userId);

    Voice findVoice();
}
