package com.github.drpatvar.util;

import com.github.drpatvar.model.Voice;
import com.github.drpatvar.to.VoiceTo;

public class VoiceUtil {

    public static Voice createNewFromTo(VoiceTo voiceTo){
        return new Voice(voiceTo.getId(), voiceTo.isEnabled());
    }

}
