package com.github.drpatvar.web.voice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.github.drpatvar.model.Voice;
import com.github.drpatvar.repository.VoiceRepository;
import com.github.drpatvar.to.VoiceTo;
import com.github.drpatvar.util.JsonUtil;
import com.github.drpatvar.util.VoiceUtil;
import com.github.drpatvar.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static com.github.drpatvar.TestUtil.userHttpBasic;
import static com.github.drpatvar.web.user.UserTestData.admin;
import static com.github.drpatvar.web.user.UserTestData.user;
import static com.github.drpatvar.web.voice.VoiceTestData.*;

class VoiceControllerTest extends AbstractControllerTest {

    private static final String REST_URL = VoiceController.REST_URL + '/';

    @Autowired
    protected VoiceRepository voiceRepository;

    @Test
    void getUnauth() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + VOICE_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(user)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOICE_MATCHER.contentJson(voices));
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + VOICE_ID)
                .with(userHttpBasic(user)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOICE_MATCHER.contentJson(voice1));
    }

    @Test
    void createWithLocation() throws Exception {
        Voice newVoice = VoiceTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT_ID)
                .with(userHttpBasic(admin))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVoice)))
                .andExpect(status().isCreated())
                .andDo(print());

        Voice created = VOICE_MATCHER.readFromJson(action);
        int newId = created.id();
        newVoice.setId(newId);
        VOICE_MATCHER.assertMatch(created, newVoice);
        VOICE_MATCHER.assertMatch(voiceRepository.get(newId), newVoice);
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + VOICE_ID)
                .with(userHttpBasic(user)))
                .andExpect(status().isNoContent());
    }

    @Test
    //тест необходимо запускать до 11:00
    void update() throws Exception {
        VoiceTo updated = VoiceTestData.updated();
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(user))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        Voice voice = VoiceUtil.createNewFromTo(updated);
        VOICE_MATCHER.assertMatch(voiceRepository.get(VOICE_ID), voice);
    }
}