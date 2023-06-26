package com.github.drpatvar.util;

import com.github.drpatvar.model.Vote;
import com.github.drpatvar.to.VoteTo;

public class VoteUtil {

    public static Vote createNewFromTo(VoteTo voteTo){
        return new Vote(null);
    }
}
