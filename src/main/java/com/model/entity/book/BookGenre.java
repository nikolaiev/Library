package com.model.entity.book;

import org.apache.log4j.Logger;

/**
 * Created by vlad on 20.03.17.
 */
public enum BookGenre {
    EDUCATION, DETECTIVE, KIDS, FANTASY, ADVENTURES;

    private static final Logger logger=Logger.getLogger(BookGenre.class);
    public static BookGenre getOrNull(String genre) {
        try{
            return BookGenre.valueOf(genre);
        }
        catch (IllegalArgumentException|NullPointerException e){
            logger.warn(e);
            return null;
        }
    }
}
