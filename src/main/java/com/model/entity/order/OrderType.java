package com.model.entity.order;

/**
 * Created by vlad on 20.03.17.
 */
public enum OrderType {
    HOME,LIBRARY;

    public static OrderType getValueOrDefault(String orderType) {
        if(orderType==null){
            return HOME;
        }
        else
            return OrderType.valueOf(orderType);
    }
}
