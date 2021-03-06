package com.controller.commands.admin.order;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.responce.Dispatcher;
import com.controller.responce.RedirectDispatcher;
import com.model.entity.order.Order;
import com.model.entity.order.OrderStatus;
import com.service.OrderService;
import com.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.controller.constants.UrlsConst.ADMIN_ORDERS;

/**
 * post from crud
 * Created by vlad on 03.04.17.
 */
public class AdminChangeOrderStatusCommand extends CommandWrapper implements Command {
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Integer id=Integer.parseInt(request.getParameter("id"));
        OrderService service= ServiceFactory.getInstance().getOrderService();
        Optional<Order> order=service.getById(id);

        order.ifPresent(ord -> {
            ord.setStatus(OrderStatus.RETURNED);
            service.updateOrderStatus(ord);
        });

        return new RedirectDispatcher(ADMIN_ORDERS);
    }
}
