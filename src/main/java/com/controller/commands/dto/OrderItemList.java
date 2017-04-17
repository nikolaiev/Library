package com.controller.commands.dto;

import com.model.entity.book.Book;
import com.model.entity.order.OrderStatus;
import com.model.entity.order.OrderType;

import java.util.HashMap;
import java.util.Map;

/**
 * This class describes list of OrderItems elements
 * Created by vlad on 10.04.17.
 */
public class OrderItemList {
    /*Order List map
    * key -> book's id
    * orderItem -> associated OrderItem object
    * */
    private Map<Integer,OrderItem> bookOrders;

    /**
     * Public constructor
     */
    public OrderItemList(){
        bookOrders=new HashMap<>();
    }

    /**
     * Add book to order list
     * @param id    book's id
     * @param orderType order type
     */
    public void addBook(Integer id, OrderType orderType){
        bookOrders.put(id, new OrderItem(orderType));
    }

    /**
     * Returns orderList object
     * @return order list
     */
    public Map<Integer,OrderItem> getBookOrders(){
        return bookOrders;
    }

    /**
     * Removes book from order id
     * @param bookId book's id
     */
    public void removeBookFromList(Integer bookId) {
        bookOrders.remove(bookId);
    }

    /**
     * Sets orderItem object status
     * @param bookId    book's id
     * @param orderStatus   new order status
     */
    public void setOrderStatus(Integer bookId, OrderStatus orderStatus){
        OrderItem orderItem=bookOrders.get(bookId);
        orderItem.setOrderStatus(orderStatus);
    }

    /**
     * Removes granted books from list of orders
     */
    public void removeGrantedBooksFromList() {
        //while(bookOrders.values().remove(null));
        bookOrders.entrySet().removeIf(e->e.getValue()
                .getOrderStatus()
                .equals(OrderStatus.GRANTED));
    }
}
