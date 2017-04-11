package com.controller.commands.dto;

import com.model.entity.order.OrderType;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlad on 10.04.17.
 */
public class OrderList {
    Map<Integer,OrderType> books;

    public OrderList(){
        books=new HashMap<>();
    }

    /*public void addBook(int id){
        books.merge(id, 1, (a, b) -> a + b);
    }*/

    public void addBook(int id, OrderType orderType){
        books.put(id, orderType);
    }

    public Map<Integer,OrderType> getBooks(){
        return books;
    }

    public void removeBookFromList(Integer bookId) {
        books.remove(bookId);
    }

    public void markBookAsGranted(Integer bookId) {
        books.put(bookId,null);
    }

    public void removeGrantedBooksFromList() {
        while(books.values().remove(null));
    }
}
