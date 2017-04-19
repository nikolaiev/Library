package com.controller.commands.admin.order;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.model.entity.order.Order;
import com.model.entity.order.OrderStatus;
import com.service.OrderService;
import com.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * post from crud
 * Created by vlad on 03.04.17.
 */
public class AdminChangeOrderStatusCommand extends CommandWrapper implements Command {
    @Override
    protected String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Integer id=Integer.parseInt(request.getParameter("id"));
        OrderService service= OrderServiceImpl.getInstance();
        Optional<Order> order=service.getById(id);

        order.ifPresent(ord -> {
            ord.setStatus(OrderStatus.RETURNED);
            service.updateOrderStatus(ord);
        });


        //TODO check why does not work
        response.sendRedirect("/admin/orders");

        return "REDIRECTED";
    }
}
