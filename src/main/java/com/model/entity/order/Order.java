package com.model.entity.order;

import com.model.entity.Identified;
import com.model.entity.book.Book;
import com.model.entity.user.User;

import java.time.OffsetDateTime;
import java.util.List;

/**
 * Created by vlad on 20.03.17.
 */
public class Order implements Identified{
    private int id;
    private User user;
    private List<Book> books;
    private OrderStatus status;
    private OrderType type;
    /*
    * Timestamp with timezone
    * */
    private OffsetDateTime orderDateTime;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
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

    public OffsetDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(OffsetDateTime orderDateTime) {
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

        public Builder setBooks(List<Book> books){
            order.setBooks(books);
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

        public Builder setOrderDateTime(OffsetDateTime dateTime){
            order.setOrderDateTime(dateTime);
            return this;
        }

        public Order build(){
            return order;
        }
    }
}
