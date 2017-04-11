package com.controller.commands.user;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.commands.dto.OrderList;
import com.model.entity.order.OrderType;
import com.model.entity.user.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vlad on 10.04.17.
 */
public class AddBookToOrderListCommand extends CommandWrapper implements Command {
    @Override
    protected String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session =request.getSession();
        OrderList orderList=(OrderList)session.getAttribute("orderList");

        if(orderList==null){
            orderList=new OrderList();
            session.setAttribute("orderList",orderList);
        }

        Integer id=Integer.parseInt(request.getParameter("id"));
        OrderType orderType=OrderType.getValueOrDefault(request.getParameter("orderType"));

        orderList.addBook(id,orderType);

        /*closing request*/
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Goods was added!");

        return "REDIRECTED";
    }
}

