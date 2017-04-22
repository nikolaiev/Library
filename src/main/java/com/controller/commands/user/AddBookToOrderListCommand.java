package com.controller.commands.user;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.commands.dto.OrderItemList;
import com.controller.exception.ControllerException;
import com.model.entity.order.OrderType;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.controller.constants.UrlsConst.REDIRECTED;

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

        int id=paramExtractor.getIntParam(request,"id");
        OrderType orderType=OrderType.getOrDefault(request.getParameter("order_type"));

        orderItemList.addBook(id,orderType);

        /*closing request*/
        //response.setStatus(HttpServletResponse.SC_OK);
        //response.getWriter().write("Goods was added!");
        request.setAttribute("error","POST REQUEST REDIRECTION ERROR");

        if(true)
            throw new ControllerException().addMessageKey("123");
        response.sendRedirect("/user/orders");

        //TODO replace with POST request dispatcher
        return REDIRECTED;
    }
}

