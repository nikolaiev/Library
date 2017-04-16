package com.model.entity.book;

/**
 * Created by vlad on 20.03.17.
 */
public enum BookGenre {
    EDUCATION, DETECTIVE, KIDS, FANTASY, ADVENTURES;

    public static BookGenre getOrNull(String genre) {
        try{
            return BookGenre.valueOf(genre);
        }
        catch (IllegalArgumentException|NullPointerException e){
            return null;
        }
    }
}
