package com.controller.commands.admin.order;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.responce.Dispatcher;
import com.controller.responce.ForwardViewDispatcher;
import com.model.entity.order.Order;
import com.service.OrderService;
import com.service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.controller.constants.JspPathsConst.ADMIN_ORDER_VIEW;

/**
 * Created by vlad on 10.04.17.
 */
public class AdminOrderCommand extends CommandWrapper implements Command {
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        OrderService service= OrderServiceImpl.getInstance();
        List<Order> orderList=service.getAllOrders();
        request.setAttribute("orders",orderList);
        return new ForwardViewDispatcher(ADMIN_ORDER_VIEW);
    }
}
