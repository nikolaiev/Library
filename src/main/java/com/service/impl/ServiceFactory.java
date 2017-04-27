package com.service.impl;

import com.service.*;

/**
 * Created by vlad on 27.04.17.
 */
public class ServiceFactory {
    private AuthorService authorService;
    private BookService bookService;
    private OrderService orderService;
    private PublisherService publisherService;
    private UserService userService;

    private ServiceFactory(){
        authorService=new AuthorServiceImpl();
        bookService=new BookServiceImpl();
        orderService=new OrderServiceImpl();
        publisherService=new PublisherServiceImpl();
        userService=new UserServiceImpl();
    }


    private static class InstanceHolder{
        static ServiceFactory INSTANCE=new ServiceFactory();
    }

    public static ServiceFactory getInstance(){
        return InstanceHolder.INSTANCE;
    }

    public AuthorService getAuthorService() {
        return authorService;
    }

    public BookService getBookService() {
        return bookService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public PublisherService getPublisherService() {
        return publisherService;
    }

    public UserService getUserService() {
        return userService;
    }
}
