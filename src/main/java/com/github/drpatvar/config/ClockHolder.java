package com.github.drpatvar.config;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.time.Clock;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@UtilityClass
public class ClockHolder {

    private static final AtomicReference<Clock> CLOCK_REFERENCE = new AtomicReference<>(Clock.systemDefaultZone());

    @NonNull
    public static Clock getClock() {
        return CLOCK_REFERENCE.get();
    }

    @NonNull
    public static Clock setClock(final Clock newClock) {
        Objects.requireNonNull(newClock, "newClock cannot be Null");
        final Clock oldClock = CLOCK_REFERENCE.getAndSet(newClock);
        log.info("Set new Clock {}. Old clock is {}", newClock, oldClock);
        return oldClock;
    }
}
