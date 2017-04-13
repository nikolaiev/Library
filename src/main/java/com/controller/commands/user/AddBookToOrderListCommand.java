package com.controller.commands.user;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.commands.dto.OrderItemList;
import com.model.entity.order.OrderType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This class describes POST command
 * Responsible for adding new book to OrderList (user session object)
 * Created by vlad on 10.04.17.
 */
public class AddBookToOrderListCommand extends CommandWrapper implements Command {
    @Override
    protected String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session =request.getSession();
        OrderItemList orderItemList =(OrderItemList)session.getAttribute("orderList");

        if(orderItemList ==null){
            orderItemList =new OrderItemList();
            session.setAttribute("orderList", orderItemList);
        }

        Integer id=Integer.parseInt(request.getParameter("id"));
        OrderType orderType=OrderType.getValueOrDefault(request.getParameter("orderType"));

        orderItemList.addBook(id,orderType);

        /*closing request*/
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Goods was added!");

        return "REDIRECTED";
    }
}

