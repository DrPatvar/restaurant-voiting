package com.github.drpatvar.web.vote;

import com.github.drpatvar.model.Vote;
import com.github.drpatvar.repository.RestaurantRepository;
import com.github.drpatvar.repository.UserRepository;
import com.github.drpatvar.service.VoteService;
import com.github.drpatvar.to.VoteTo;
import com.github.drpatvar.util.validation.ValidationUtil;
import com.github.drpatvar.web.SecurityUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static com.github.drpatvar.util.validation.ValidationUtil.assureIdConsistent;

@RestController
@Slf4j
@RequestMapping(value = VoteController.REST_URL)
public class VoteController {

    static final String REST_URL = "/api/votes";
    @Autowired
    protected VoteService voteService;
    @Autowired
    protected RestaurantRepository restaurantRepository;
    @Autowired
    protected UserRepository userRepository;

    @GetMapping(value = "/{id}")
    public Vote get(@PathVariable int id) {
        log.info("get {}", id);
        int authUserId = SecurityUtil.authId();
        return voteService.get(id, authUserId);
    }

    @GetMapping
    public List<Vote> getAll() {
        int authUserId = SecurityUtil.authId();
        log.info("getAll user voices{}", authUserId);
        return voteService.findAllVoice(authUserId);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> createWithLocation(@Valid @RequestBody VoteTo voteTo) {
        log.info("create restaurant {}", voteTo);
        int authUserId = SecurityUtil.authId();
        ValidationUtil.checkNew(voteTo);
        Vote created = voteService.create(voteTo, authUserId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@Valid @RequestBody VoteTo voteTo, @PathVariable int id) {
        log.info("update {} with id = {}", voteTo, id);
        int authUserId = SecurityUtil.authId();
        assureIdConsistent(voteTo, id);
        voteService.update(voteTo, authUserId);
    }
}
