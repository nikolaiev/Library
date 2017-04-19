package com.controller.commands.user;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.commands.dto.OrderItemList;
import com.service.OrderService;
import com.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 10.04.17.
 */
public class ProcessOrderListCommand extends CommandWrapper implements Command {
    @Override
    protected String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OrderItemList orderItemList =(OrderItemList)request.getSession().getAttribute("orderList");

        if(orderItemList !=null) {
            //order list is not empty
            OrderService orderService = OrderServiceImpl.getInstance();
            Integer userId = (Integer) request.getSession().getAttribute("userId");
            orderService.createOrdersAndClear(orderItemList, userId);
        }

        response.sendRedirect(request.getContextPath()+"/user/orders");
        return "REDIRECTED";
    }

}
