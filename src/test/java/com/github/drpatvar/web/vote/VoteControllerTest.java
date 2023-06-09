package com.github.drpatvar.web.vote;

import com.github.drpatvar.config.ClockHolder;
import com.github.drpatvar.model.Vote;
import com.github.drpatvar.repository.VoteRepository;
import com.github.drpatvar.to.VoteTo;
import com.github.drpatvar.util.JsonUtil;
import com.github.drpatvar.util.VoteUtil;
import com.github.drpatvar.web.AbstractControllerTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;

import static com.github.drpatvar.TestUtil.userHttpBasic;
import static com.github.drpatvar.web.user.UserTestData.admin;
import static com.github.drpatvar.web.user.UserTestData.user;
import static com.github.drpatvar.web.vote.VoteTestData.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class VoteControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoteController.REST_URL + '/';

    @Autowired
    protected VoteRepository voteRepository;

    @BeforeAll
    static void setUpClock(){
        final Clock fixed = Clock.fixed(Instant.parse("2023-06-23T10:00:00Z"), ZoneId.of("UTC"));
        ClockHolder.setClock(fixed);
    }

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + VOTE_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(user)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VOTES));
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + VOTE_ID)
                .with(userHttpBasic(user)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(VOTE_1));
    }

    @Test
    void createWithLocation() throws Exception {
        VoteTo newVoteTo = VoteTestData.getNew();
        Vote newVote = VoteUtil.createNewFromTo(newVoteTo);
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .with(userHttpBasic(admin))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVoteTo)))
                .andExpect(status().isCreated())
                .andDo(print());

        Vote created = VOTE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteRepository.get(newId), newVote);
    }

    @Test
    void update() throws Exception {
        VoteTo updatedTo = VoteTestData.updated();
        perform(MockMvcRequestBuilders.put(REST_URL + VOTE_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(user))
                .content(JsonUtil.writeValue(updatedTo)))
                .andExpect(status().isNoContent());
        Vote vote = VoteUtil.updateFromTo(updatedTo);
        VOTE_MATCHER.assertMatch(voteRepository.get(VOTE_ID), vote);
    }
}