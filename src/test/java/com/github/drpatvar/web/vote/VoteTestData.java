package com.github.drpatvar.web.vote;

import com.github.drpatvar.model.Vote;
import com.github.drpatvar.to.VoteTo;
import com.github.drpatvar.web.MatcherFactory;

import java.util.List;

public class VoteTestData {
    public static final MatcherFactory.Matcher<Vote> VOTE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Vote.class,"votingDate", "user", "restaurant");

    public static final Integer VOTE_ID = 1;
    public static final int RESTAURANT_ID = 1;

    public static final Vote VOTE_1 = new Vote(VOTE_ID);
    public static final Vote VOTE_2 = new Vote(VOTE_ID + 1);
    public static final List<Vote> VOTES = List.of(VOTE_1);

    public static VoteTo getNew(){
        return new VoteTo(null, RESTAURANT_ID);
    }

    public static VoteTo updated(){
        return new VoteTo(VOTE_ID, RESTAURANT_ID);
    }

}
