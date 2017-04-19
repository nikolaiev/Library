package com.model.entity.order;

/**
 * Created by vlad on 20.03.17.
 */
public enum OrderType {
    HOME,LIBRARY;

    public static OrderType getOrNull(String type) {
        try{
            return OrderType.valueOf(type);
        }
        catch (IllegalArgumentException|NullPointerException e){
            return null;
        }
    }

    public static OrderType getOrDefault(String type) {
        try{
            return OrderType.valueOf(type);
        }
        catch (IllegalArgumentException|NullPointerException e){
            return HOME;
        }
    }
}
