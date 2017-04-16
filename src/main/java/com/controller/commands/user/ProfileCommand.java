package com.controller.commands.user;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.model.entity.order.Order;
import com.service.OrderService;
import com.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by vlad on 12.04.17.
 */
public class ProfileCommand extends CommandWrapper implements Command {
    @Override
    protected String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        OrderService service= OrderServiceImpl.getInstance();

        HttpSession session=request.getSession();

        int userId= (int) session.getAttribute("userId");

        List<Order> orders=service.getOrdersByUserId(userId);
        request.setAttribute("orders",orders);

        return request.getContextPath()+"/WEB-INF/view/user/profilePage.jsp";
    }
}
