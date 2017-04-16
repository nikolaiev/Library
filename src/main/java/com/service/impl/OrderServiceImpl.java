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

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Created by vlad on 30.03.17.
 */
public class OrderServiceImpl extends GenericService implements OrderService {

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

    /**
     * Creates orders from user OrderItemList session object
     * @param orderItemList user OrderItemListObject
     * @param userId    userId
     */
    @Override
    public void createOrders(OrderItemList orderItemList, int userId) {
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
    public Optional<Order> getOrderById(Integer id) {
        return executeInNonTransactionalWrapper(daoManager ->
            daoManager.getOrderDao().getById(id)
        );
    }

    @Override
    public List<Order> getOrdersByUserId(int userId) {
        return executeInNonTransactionalWrapper(transactionManager ->
        transactionManager.getOrderDao().getOrdersByUserId(userId));
    }

    private static class InstanceHolder{
        private static OrderServiceImpl INSTANCE=new OrderServiceImpl();

    }

    public static OrderService getInstance(){
        return InstanceHolder.INSTANCE;
    }
}
