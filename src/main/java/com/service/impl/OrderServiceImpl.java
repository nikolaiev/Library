package com.service.impl;

import com.controller.commands.dto.OrderList;
import com.exception.ApplicationException;
import com.model.entity.book.Book;
import com.model.entity.order.Order;
import com.model.entity.order.OrderStatus;
import com.model.entity.order.OrderType;
import com.model.entity.user.User;
import com.service.OrderService;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

/**
 * Created by vlad on 30.03.17.
 */
public class OrderServiceImpl extends GenericService implements OrderService {



    private void createOrder(int userId, int bookId, OrderType orderType) {
        executeInTransactionalVoidWrapper((daoManager)->{

            daoManager.getBookDao().grantBook(bookId);

            Book book=new Book.Builder().setId(bookId).build();
            User user=new User.Builder().setId(userId).build();

            Order order=new Order.Builder()
                    .setBook(book)
                    .setType(orderType)
                    .setUser(user)
                    .setStatus(OrderStatus.GRANTED)
                    .build();
           daoManager.getOrderDao().insert(order);
        });
    }

    @Override
    public void createOrders(OrderList orderList, int userId) {
        Map<Integer,OrderType> books=orderList.getBooks();
        Set<Integer> keys=books.keySet();

        for(Integer bookId :keys){
            OrderType orderType=books.get(bookId);
            try {
                createOrder(userId, bookId, orderType);
                orderList.markBookAsGranted(bookId);
            }
            catch (ApplicationException e){
                //TODO logger
            }

        }

        orderList.removeGrantedBooksFromList();
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
            daoManager.getBookDao().returnBook(order.getBook());
            daoManager.getOrderDao().updateOrderStatus(order);
        });
    }

    @Override
    public Optional<Order> getOrderById(Integer id) {
        return executeInNonTransactionalWrapper(daoManager ->
            daoManager.getOrderDao().getById(id)
        );
    }

    private static class InstanceHolder{
        private static OrderServiceImpl INSTANCE=new OrderServiceImpl();

    }

    public static OrderService getInstance(){
        return InstanceHolder.INSTANCE;
    }
}
