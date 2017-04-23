package com.controller.commands.dto;

import com.model.entity.order.OrderStatus;
import com.model.entity.order.OrderType;

import java.io.Serializable;

/**
 * This class describes Order item element
 * Created by vlad on 11.04.17.
 */
public class OrderItem {
    /*order status*/
    private OrderStatus orderStatus;
    /*order type*/
    private OrderType orderType;

    /**
     * Public Constructor
     * @param orderType orderType val
     */
    public OrderItem(OrderType orderType) {
        this.orderStatus=OrderStatus.REQUESTED;
        this.orderType=orderType;
    }

    /**
     * Getter for orderStatus
     * @return orderStatus
     */
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    /**
     * Setter for orderStatus
     * @param orderStatus new val
     */
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * Getter for orderType
     * @return orderType
     */
    public OrderType getOrderType() {
        return orderType;
    }

    /**
     * Setter for orderType
     * @param orderType new val
     */
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
}
