package com.controller.commands.admin.order;

import com.controller.commands.Command;
import com.model.entity.order.Order;
import com.service.OrderService;
import com.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by vlad on 10.04.17.
 */
public class AdminOrderCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OrderService service= OrderServiceImpl.getInstance();
        List<Order> orderList=service.getAllOrders();
        request.setAttribute("orders",orderList);
        return request.getContextPath()+"/WEB-INF/view/admin/orderPage.jsp";
    }
}
