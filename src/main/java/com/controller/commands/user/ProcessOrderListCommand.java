package com.controller.commands.user;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.commands.dto.OrderList;
import com.model.entity.order.OrderType;
import com.service.OrderService;
import com.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlad on 10.04.17.
 */
public class ProcessOrderListCommand extends CommandWrapper implements Command {
    @Override
    protected String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OrderList orderList=(OrderList )request.getSession().getAttribute("orderList");
        if(orderList==null){
            response.sendRedirect("/user/books");
            return "REDIRECTED";
        }
        //order list is not empty

        OrderService orderService= OrderServiceImpl.getInstance();
        Integer userId=(Integer)request.getSession().getAttribute("userId");
        orderService.createOrders(orderList,userId);


        //request.getSession().setAttribute("orderList",null);
        response.sendRedirect("/user/books");
        return "REDIRECTED";
    }

}
