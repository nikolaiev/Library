package com.model.entity.order;

import com.model.entity.Identified;
import com.model.entity.book.Book;
import com.model.entity.user.User;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Created by vlad on 20.03.17.
 */
public class Order implements Identified{
    private int id;
    private int userId;
    private int bookId;
    private OrderStatus status;
    private OrderType type;
    private LocalDate orderDateTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public LocalDate getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDate orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id=id;
    }

    public static class Builder{
        Order order=new Order();

        public Builder setId(int id){
            order.setId(id);
            return this;
        }

        public Builder setBookId(int bookId){
            order.setBookId(bookId);
            return this;
        }

        public Builder setUserId(int userId){
            order.setUserId(userId);
            return this;
        }

        public Builder setStatus(OrderStatus status){
            order.setStatus(status);
            return this;
        }

        public Builder setType(OrderType type){
            order.setType(type);
            return this;
        }

        public Builder setOrderDateTime(LocalDate dateTime){
            order.setOrderDateTime(dateTime);
            return this;
        }

        public Order build(){
            return order;
        }
    }
}
