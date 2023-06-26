package com.github.drpatvar.service;

import com.github.drpatvar.error.IllegalRequestDataException;
import com.github.drpatvar.model.Vote;
import com.github.drpatvar.repository.RestaurantRepository;
import com.github.drpatvar.repository.UserRepository;
import com.github.drpatvar.repository.VoteRepository;
import com.github.drpatvar.to.VoteTo;
import com.github.drpatvar.util.VoteUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static com.github.drpatvar.util.validation.ValidationUtil.checkTime;

@Service
@Transactional(readOnly = true)
public class VoteService {

    LocalDate dateNow = LocalDate.now();

    @Autowired
    protected VoteRepository voteRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RestaurantRepository restaurantRepository;

    public Vote get(int id, int userId) {
        return voteRepository.findWithUser(id, userId);
    }

    public List<Vote> findAllVoice(int authUserId) {
        return voteRepository.findAllByUserVoice(authUserId);
    }

    @Transactional
    public void update(VoteTo voteTo, int userId) {
        checkTime();
        Vote dbVote = voteRepository.get(voteTo.getId());
        int id = dbVote.getUser().getId();
        if (id != userId) {
            throw new IllegalRequestDataException("This voice does not belong to you");
        }
        dbVote.setRestaurant(restaurantRepository.getReferenceById(voteTo.getRestaurantId()));
        LocalDate dbDate = dbVote.getVotingDate();
        if (dbDate.isEqual(dateNow)) {
            voteRepository.save(dbVote);
        } else {
            throw new IllegalRequestDataException("You can change your vote only on the voting day before 11:00");
        }
    }

    @Transactional
    public Vote create(VoteTo voteTo, int userId) {
        checkTime();
        Vote vote = VoteUtil.createNewFromTo(voteTo);
        vote.setUser(userRepository.getReferenceById(userId));
        vote.setRestaurant(restaurantRepository.getReferenceById(voteTo.getRestaurantId()));
        return voteRepository.save(vote);
    }
}
