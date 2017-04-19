package com.service.impl;

import com.controller.commands.dto.OrderItem;
import com.controller.commands.dto.OrderItemList;
import com.dao.BookDao;
import com.dao.exception.DaoException;
import com.exception.ApplicationException;
import com.model.entity.book.Book;
import com.model.entity.order.Order;
import com.model.entity.order.OrderStatus;
import com.model.entity.order.OrderType;
import com.model.entity.user.User;
import com.service.OrderService;
import com.service.exception.ServiceException;

import java.util.*;

/**
 * Created by vlad on 30.03.17.
 */
public class OrderServiceImpl extends GenericService implements OrderService {

    private final static String NO_SUCH_BOOK_EXCEPTION="DB error. No such book exception";



    private static class InstanceHolder{
        private static OrderServiceImpl INSTANCE=new OrderServiceImpl();

    }

    public static OrderService getInstance(){
        return InstanceHolder.INSTANCE;
    }



    /**
     * Creates orders from user OrderItemList session object
     * and removes successful ones from session object
     * @param orderItemList user OrderItemListObject
     * @param userId    userId
     */
    @Override
    public void createOrdersAndClear(OrderItemList orderItemList, int userId) {
        Map<Integer,OrderItem> bookOrders= orderItemList.getBookOrders();
        Set<Integer> keys=bookOrders.keySet();

        for(Integer bookId :keys) {
            OrderType orderType = bookOrders.get(bookId).getOrderType();

            if(createOrder(userId, bookId, orderType)) {
                orderItemList.setOrderStatus(bookId, OrderStatus.GRANTED);
            }
            else {
                orderItemList.setOrderStatus(bookId, OrderStatus.REJECTED);
            }
        }
        /*clear session orderList object*/
        orderItemList.removeGrantedBooksFromList();
    }

    @Override
    public List<Order> getAllOrders() {
        return executeInNonTransactionalWrapper((daoManager)->
           daoManager.getOrderDao().getAll()
        );
    }

    @Override
    public void updateOrderStatus(Order order) {
        executeInTransactionalVoidWrapper((daoManager)-> {
            daoManager.getBookDao().returnBook(order.getBook().getId());
            daoManager.getOrderDao().updateOrderStatus(order);
        });
    }


    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return executeInNonTransactionalWrapper(transactionManager ->
        transactionManager.getOrderDao().getOrdersByUserId(userId));
    }

    @Override
    public Map<Book, OrderItem> getDetailedBookOrders(Map<Integer, OrderItem> sessionBookOrders) {
        Map<Book,OrderItem> resultDetailedOrders=new HashMap<>();

        for(Map.Entry<Integer,OrderItem> entry:sessionBookOrders.entrySet()){
            int bookId=entry.getKey();
            OrderItem orderItem=entry.getValue();

            Book book=executeInNonTransactionalWrapper(transactionManager ->
                transactionManager.getBookDao().getById(bookId)).orElseThrow(()->
                    new ServiceException()
                            .addLogMessage(NO_SUCH_BOOK_EXCEPTION)
                            .addMessageKey(NO_SUCH_BOOK_EXCEPTION));

            resultDetailedOrders.put(book,orderItem);
        }

        return resultDetailedOrders;
    }

    @Override
    public List<Order> getOrdersByParams(Integer userId, String bookTitle, OrderStatus orderStatus,
                                         OrderType orderType, Date beforeDate,int limit,int offset) {
        return executeInNonTransactionalWrapper(transactionManager ->
                transactionManager.getOrderDao()
                        .getOrdersByParams(userId, bookTitle, orderStatus,
                        orderType, beforeDate,limit,offset));
    }

    @Override
    public int getOrdersCountByParams(Integer userId, String bookTitle, OrderStatus orderStatus, OrderType orderType, Date beforeDate) {
        return executeInNonTransactionalWrapper(transactionManager ->
                transactionManager.getOrderDao()
                        .getOrdersCountByParams(userId, bookTitle, orderStatus,
                                orderType, beforeDate));
    }

    @Override
    public Order create(Order order) {
        return null;
    }

    @Override
    public Optional<Order> getById(int id) {
        return executeInNonTransactionalWrapper(daoManager ->
                daoManager.getOrderDao().getById(id)
        );
    }

    @Override
    public void update(Order order) {

    }

    @Override
    public void deleteById(int id) {

    }

    private boolean createOrder(int userId, int bookId, OrderType orderType) {
        return executeInSerializableWrapper((daoManager)->{

            BookDao bookDao=daoManager.getBookDao();

            if(bookDao.getCountAvailable(bookId)>0){
                bookDao.grantBook(bookId);
                Book book=new Book.Builder().setId(bookId).build();
                User user=new User.Builder().setId(userId).build();

                Order order=new Order.Builder()
                        .setBook(book)
                        .setType(orderType)
                        .setUser(user)
                        .setStatus(OrderStatus.GRANTED)
                        .build();
                daoManager.getOrderDao().insert(order);
                return true;
            }
            return false;
        });
    }
}
