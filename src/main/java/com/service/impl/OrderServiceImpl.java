package com.service.impl;

import com.controller.commands.dto.OrderList;
import com.model.entity.order.Order;
import com.model.entity.order.OrderStatus;
import com.model.entity.order.OrderType;
import com.service.OrderService;

import java.util.Map;

/**
 * Created by vlad on 30.03.17.
 */
public class OrderServiceImpl extends GenericService implements OrderService {



    private void createOrder(int userId, int bookId, OrderType orderType) {
        executeInTransactionalVoidWrapper((daoManager)->{
           daoManager.getBookDao().grantBook(bookId);
            Order order=new Order.Builder()
                    .setBookId(bookId)
                    .setType(orderType)
                    .setUserId(userId)
                    .setStatus(OrderStatus.GRANTED)
                    .build();
           daoManager.getOrderDao().insert(order);
        });
    }

    @Override
    public void createOrders(OrderList orderList, int userId) {
        Map<Integer,OrderType> books=orderList.getBooks();

        for (Map.Entry<Integer, OrderType> entry : books.entrySet())
        {
            Integer bookId=entry.getKey();
            OrderType orderType=entry.getValue();
            createOrder(userId,bookId,orderType);

            //TODO clear session from books!
            //clearing ordered book from session
            //orderList.removeBookFromList(bookId);
        }

    }

    private static class InstanceHolder{
        private static OrderServiceImpl INSTANCE=new OrderServiceImpl();

    }

    public static OrderService getInstance(){
        return InstanceHolder.INSTANCE;
    }
}
