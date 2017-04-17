package com.model.entity.book;

/**
 * Created by vlad on 21.03.17.
 */
public enum BookLanguage {
    ENG,RUS,UA;

    public static BookLanguage getOrNull(String language) {
        try{
            return BookLanguage.valueOf(language);
        }
        catch (IllegalArgumentException|NullPointerException e){
            return null;
        }
    }
}
