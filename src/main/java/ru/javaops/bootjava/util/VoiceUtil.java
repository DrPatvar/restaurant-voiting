package ru.javaops.bootjava.util;

import ru.javaops.bootjava.model.Voice;
import ru.javaops.bootjava.to.VoiceTo;

public class VoiceUtil {

    public static Voice createNewFromTo(VoiceTo voiceTo){
        return new Voice(voiceTo.getId(), voiceTo.isEnabled());
    }

}
