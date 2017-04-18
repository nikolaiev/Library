package com.model.entity.order;

/**
 * Created by vlad on 20.03.17.
 */
public enum OrderStatus {
    REQUESTED, GRANTED, RETURNED, REJECTED;

    public static OrderStatus getOrNull(String language) {
        try{
            return OrderStatus.valueOf(language);
        }
        catch (IllegalArgumentException|NullPointerException e){
            return null;
        }
    }
}
