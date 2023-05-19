package ru.javaops.bootjava.web.voice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.bootjava.error.IllegalRequestDataException;
import ru.javaops.bootjava.model.Voice;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.repository.UserRepository;
import ru.javaops.bootjava.service.VoiceService;
import ru.javaops.bootjava.to.VoiceTo;
import ru.javaops.bootjava.util.validation.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;
import java.time.LocalTime;
import java.util.List;

import static ru.javaops.bootjava.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@Slf4j
@RequestMapping(value = VoiceController.REST_URL)
public class VoiceController {

    static final String REST_URL = "/api/voice";
    @Autowired
    protected VoiceService voiceService;
    @Autowired
    protected RestaurantRepository restaurantRepository;
    @Autowired
    protected UserRepository userRepository;


    @GetMapping
    public List<Voice> getAll() {
        log.info("getAll");
        return voiceService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Voice get(@PathVariable int id) {
        log.info("get {}", id);
        return voiceService.get(id);
    }

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Voice> createWithLocation(@Valid @RequestBody Voice voice, @PathVariable int restaurantId) {
        log.info("create restaurant {}", voice);
        ValidationUtil.checkNew(voice);
        Voice created = voiceService.create(voice, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        log.info("delete voice {}", id);
        voiceService.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody VoiceTo voiceTo, @PathVariable int id) {
        log.info("update {} with id = {}", voiceTo, id);
        if (!LocalTime.now().isBefore(LocalTime.of(11,00,00))){
            throw new IllegalRequestDataException("Извините голосовать можно только до 11:00");
        }
        assureIdConsistent(voiceTo, id);
        voiceService.update(voiceTo);
    }
}
