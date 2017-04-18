package com.view.function;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Jstl custom function
 * Created by vlad on 18.04.17.
 */
public final class Dates {
    private Dates(){}

    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
}
