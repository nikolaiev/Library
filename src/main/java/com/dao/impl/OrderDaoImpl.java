package com.dao.impl;

import com.dao.OrderDao;
import com.model.entity.order.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 20.03.17.
 */
public class OrderDaoImpl extends AbstractDao implements OrderDao {
    private static final String SELECT_ALL="SELECT id, uid, bid, status, type, cdate" +
            "  FROM public.\"order\";";

    private static final String SELECT_ORDER_BY_ID=SELECT_ALL+" WHERE id =?";

    private static final String DELETE_ORDER_BY_ID="DELETE FROM public.\"order\" WHERE id=?";

    private static final String UPDATE_ORDER_BY_ID="UPDATE public.\"order\" " +
            "   SET uid=?, bid=?, status=?, type=?, cdate=?" +
            " WHERE id=?";

    private static final String INSERT_ORDER="INSERT INTO public.\"order\"" +
            " (uid, bid, status, type, cdate)\n" +
            "    VALUES (?, ?, ?, ?, ?);";

    private static final String ID_FIELD="id";
    private static final String USER_ID_FIELD="uid";
    private static final String TABLE="public.\"order\"";

    public OrderDaoImpl(Connection connection) {
        super(connection);
    }


    @Override
    public Order insert(Order obj) {
        return null;
    }

    @Override
    public void update(Order obj) {

    }

    @Override
    public List<Order> getAll(){
        return null;
    }

    @Override
    public Optional<Order> getById(int key){
        return null;
    }

    @Override
    public void removeById(int key) {

    }

    @Override
    public List<Order> getOrdersByDate(Date date) {
        return null;
    }
}
