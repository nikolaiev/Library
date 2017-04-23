package com.model.entity.order;

import org.apache.log4j.Logger;

/**
 * Created by vlad on 20.03.17.
 */
public enum OrderStatus {
    REQUESTED, GRANTED, RETURNED, REJECTED;

    private static final Logger logger=Logger.getLogger(OrderStatus.class);

    public static OrderStatus getOrNull(String language) {
        try{
            return OrderStatus.valueOf(language);
        }
        catch (IllegalArgumentException|NullPointerException e){
            logger.warn(e);
            return null;
        }
    }
}
