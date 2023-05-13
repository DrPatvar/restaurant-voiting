package ru.javaops.bootjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javaops.bootjava.model.Voice;
import ru.javaops.bootjava.repository.VoiceRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VoiceService {

    @Autowired
    protected VoiceRepository voiceRepository;

    public List<Voice> getAll(){
        return voiceRepository.findAll();
    }

    public void update(Voice voice){
        LocalDateTime localDateTimeDB = voiceRepository.get(voice.id()).getRegistered();
    }

    public Voice create (Voice voice){
        return voiceRepository.save(voice);
    }

}
