package com.model.entity.order;

import com.model.entity.IdContainer;
import com.model.entity.book.Book;
import com.model.entity.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.util.List;

/**
 * Created by vlad on 20.03.17.
 */
public class Order extends IdContainer {

    private User user;
    private Book book;
    private OrderStatus status;
    private OrderType type;
    private LocalDateTime orderDateTime;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }


    public static class Builder{
        Order order=new Order();

        public Builder setId(int id){
            order.setId(id);
            return this;
        }

        public Builder setBook(Book book){
            order.setBook(book);
            return this;
        }

        public Builder setUser(User user){
            order.setUser(user);
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

        public Builder setOrderDateTime(LocalDateTime dateTime){
            order.setOrderDateTime(dateTime);
            return this;
        }

        public Order build(){
            return order;
        }
    }
}
