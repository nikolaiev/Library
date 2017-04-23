package com.model.entity.order;

import org.apache.log4j.Logger;

/**
 * Created by vlad on 20.03.17.
 */
public enum OrderType {
    HOME,LIBRARY;

    private static final Logger logger=Logger.getLogger(OrderType.class);

    public static OrderType getOrNull(String type) {
        try{
            return OrderType.valueOf(type);
        }
        catch (IllegalArgumentException|NullPointerException e){
            logger.warn(e);
            return null;
        }
    }

    public static OrderType getOrDefault(String type) {
        try{
            return OrderType.valueOf(type);
        }
        catch (IllegalArgumentException|NullPointerException e){
            logger.warn(e);
            return HOME;
        }
    }
}
