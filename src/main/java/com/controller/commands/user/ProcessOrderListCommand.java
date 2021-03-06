package com.controller.commands.user;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.commands.dto.OrderItemList;
import com.controller.responce.Dispatcher;
import com.controller.responce.RedirectDispatcher;
import com.service.OrderService;
import com.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.controller.constants.UrlsConst.USER_ORDERS;

/**
 * Created by vlad on 10.04.17.
 */
public class ProcessOrderListCommand extends CommandWrapper implements Command {
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OrderItemList orderItemList =(OrderItemList)request.getSession().getAttribute("orderList");

        if(orderItemList !=null) {
            //order list is not empty
            OrderService orderService = ServiceFactory.getInstance().getOrderService();
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            orderService.createOrdersAndClear(orderItemList, userId);
        }
        return new RedirectDispatcher(USER_ORDERS);
    }

}
