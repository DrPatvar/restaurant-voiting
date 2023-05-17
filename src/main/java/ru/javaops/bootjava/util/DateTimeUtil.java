package ru.javaops.bootjava.util;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DateTimeUtil {

    public static LocalDate parseLocaleDate(LocalDateTime dateTime){
        return dateTime.toLocalDate();
    }


}
