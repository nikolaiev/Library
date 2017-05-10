package com.view.function;

import org.apache.log4j.Logger;
import sun.rmi.runtime.Log;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

/**
 * Jstl custom function
 * Created by vlad on 18.04.17.
 */
public final class Dates {
    private static final Logger logger= Logger.getLogger(Dates.class);
    private Dates(){}


    public static String formatLocalDateTime(LocalDateTime localDateTime, String pattern) {
        if(localDateTime!=null) {
            return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
        }
        else {
            logger.error("passed null parameter to JSP ");
            return "";
        }
    }

    public static String formatLocalDate(LocalDate localDate, String pattern) {
        if(localDate!=null) {
            return localDate.format(DateTimeFormatter.ofPattern(pattern));
        }
        else {
            logger.error("passed null parameter to JSP ");
            return "";
        }
    }

    public static String formatInstantToLocale(Instant instant,String pattern){
        if(instant!=null) {
            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(pattern )
                            .withZone( ZoneId.systemDefault() );
            logger.info("formatter: " + formatter + " with zone: " + formatter.getZone() + " and Locale: " + formatter.getLocale());
            return formatter.format( instant );
        }
        else {
            logger.error("passed null parameter to JSP ");
            return "";
        }
    }
}
