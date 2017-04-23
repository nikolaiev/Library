package com.model.entity.book;

import org.apache.log4j.Logger;

/**
 * Created by vlad on 21.03.17.
 */
public enum BookLanguage {
    ENG,RUS,UA;

    private static final Logger logger=Logger.getLogger(BookLanguage.class);

    public static BookLanguage getOrNull(String language) {
        try{
            return BookLanguage.valueOf(language);
        }
        catch (IllegalArgumentException|NullPointerException e){
            logger.warn(e);
            return null;
        }
    }
}
