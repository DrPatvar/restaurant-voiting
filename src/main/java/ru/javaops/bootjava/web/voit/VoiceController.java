package ru.javaops.bootjava.web.voit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.javaops.bootjava.model.Voice;
import ru.javaops.bootjava.repository.RestaurantRepository;
import ru.javaops.bootjava.repository.UserRepository;
import ru.javaops.bootjava.repository.VoiceRepository;
import ru.javaops.bootjava.util.validation.ValidationUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = VoiceController.REST_URL)
public class VoiceController {

    static final String REST_URL = "/api/voice";
    @Autowired
    protected VoiceRepository voiceRepository;
    @Autowired
    protected RestaurantRepository restaurantRepository;
    @Autowired
    protected UserRepository userRepository;

    @GetMapping
    public List<Voice> getAll() {
        log.info("getAll");
        return voiceRepository.findAll();
    }

    @GetMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public Voice get(@PathVariable int id) {
        log.info("get {}", id);
        return voiceRepository.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Voice> createWithLocation(@Valid @RequestBody Voice voice) {
        log.info("create restaurant {}", voice);
        ValidationUtil.checkNew(voice);
        Voice created = voiceRepository.save(voice);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable int id) {
        log.info("delete voice {}", id);
        voiceRepository.delete(id);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestParam Voice voice, @PathVariable int id) {
        log.info("update {} with id = {}", voice, id);
        ValidationUtil.assureIdConsistent(voice, id);
        Voice voiceDB = voiceRepository.get(voice.id());
    }
}
