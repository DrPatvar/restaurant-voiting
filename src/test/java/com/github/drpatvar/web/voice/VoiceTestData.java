package com.github.drpatvar.web.voice;

import com.github.drpatvar.model.Voice;
import com.github.drpatvar.to.VoiceTo;
import com.github.drpatvar.web.MatcherFactory;

import java.util.List;

public class VoiceTestData {
    public static final MatcherFactory.Matcher<Voice> VOICE_MATCHER = MatcherFactory.usingIgnoringFieldsComparator(Voice.class,"registered", "user", "restaurant");

    public static final int VOICE_ID = 1;
    public static final int RESTAURANT_ID =1;

    public static final Voice voice1 = new Voice(VOICE_ID, true);
    public static final Voice voice2 = new Voice(VOICE_ID + 1, true);
    public static final Voice voice3 = new Voice(VOICE_ID + 2, true);
    public static final List<Voice> voices = List.of(voice1, voice2, voice3);

    public static Voice getNew(){
        return new Voice(null, false);
    }

    public static VoiceTo updated(){
        return new VoiceTo(VOICE_ID, false, 1);
    }

}
