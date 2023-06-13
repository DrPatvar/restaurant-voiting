package com.github.drpatvar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.github.drpatvar.error.IllegalRequestDataException;
import com.github.drpatvar.model.Voice;
import com.github.drpatvar.repository.RestaurantRepository;
import com.github.drpatvar.repository.UserRepository;
import com.github.drpatvar.repository.VoiceRepository;
import com.github.drpatvar.to.VoiceTo;
import com.github.drpatvar.util.VoiceUtil;
import com.github.drpatvar.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class VoiceService {


    @Autowired
    protected VoiceRepository voiceRepository;

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected RestaurantRepository restaurantRepository;

    public Voice get(int id) {
        return voiceRepository.getWithUser(id, SecurityUtil.authId());
    }

    public List<Voice> getAll() {
        return voiceRepository.findVoices();
    }

    @Transactional
    public void update(VoiceTo voiceTo) {
        Voice voice = null;
        Voice voiceDB = get(voiceTo.id());
        if (voiceDB.getUser().id() != SecurityUtil.authId()){
            throw new IllegalRequestDataException("Данный голос принадлежит не Вам");
        }
        voice = VoiceUtil.createNewFromTo(voiceTo);
        voice.setRegistered(LocalDateTime.now());
        voice.setUser(userRepository.getReferenceById(SecurityUtil.authId()));
        voice.setRestaurant(restaurantRepository.getReferenceById(voiceTo.getRestaurantId()));
        LocalDate localDateDB = voiceDB.getRegistered().toLocalDate();
        LocalDate dateNow = LocalDate.now();
        if (localDateDB.isEqual(dateNow)) {
            voiceRepository.save(voice);
        }else{
            throw new IllegalRequestDataException("Изменить голос можно только в день голосования до 11:00");
        }
    }

    @Transactional
    public Voice create(Voice voice, int restaurantId) {
        List<Voice> voiceList = voiceRepository.getAllByUserVoice(SecurityUtil.authId());
        for (Voice v: voiceList) {
            if (v.getRegistered().toLocalDate().equals(LocalDate.now())){
                throw new IllegalRequestDataException("Вы сегодня уже голосовали, можете изменить его");
            }
        }
        voice.setUser(userRepository.getReferenceById(SecurityUtil.authId()));
        voice.setRestaurant(restaurantRepository.getReferenceById(restaurantId));
        return voiceRepository.save(voice);
    }

    @Transactional
    public void delete(int id) {
        voiceRepository.delete(id);
    }
}
