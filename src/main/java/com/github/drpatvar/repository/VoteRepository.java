package com.github.drpatvar.repository;

import com.github.drpatvar.model.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface VoteRepository extends BaseRepository<Vote> {

 @Query("SELECT v FROM Vote v WHERE v.id = ?1 AND v.user.id = ?2")
 Vote findWithUser(int id, int userId);

 @Query("SELECT v FROM Vote v WHERE v.user.id = ?1")
 List<Vote> findAllByUserVoice(int userId);
}
