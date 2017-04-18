package com.model.entity.order;

/**
 * Created by vlad on 20.03.17.
 */
public enum OrderType {
    HOME,LIBRARY;

    public static OrderType getOrDefault(String orderType) {
        try{
            return OrderType.valueOf(orderType);
        }
        catch (IllegalArgumentException|NullPointerException e){
            return HOME;
        }
    }

    public static OrderType getOrNull(String language) {
        try{
            return OrderType.valueOf(language);
        }
        catch (IllegalArgumentException|NullPointerException e){
            return null;
        }
    }
}
