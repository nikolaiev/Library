package com.controller.commands.user;

import com.controller.commands.Command;
import com.controller.commands.dto.OrderList;
import com.model.entity.order.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 10.04.17.
 */
public class ShowOrderListCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OrderList orderList=(OrderList) request.getSession().getAttribute("orderList");
        if(orderList!=null)
            request.setAttribute("books",
                orderList.getBooks());

        return request.getContextPath()+"/WEB-INF/view/user/orderListPage.jsp";

    }
}
