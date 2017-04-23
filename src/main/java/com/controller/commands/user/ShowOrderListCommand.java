package com.controller.commands.user;

import com.controller.commands.Command;
import com.controller.commands.dto.OrderItem;
import com.controller.commands.dto.OrderItemList;
import com.controller.responce.Dispatcher;
import com.controller.responce.ForwardViewDispatcher;
import com.model.entity.book.Book;
import com.service.OrderService;
import com.service.impl.OrderServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

import static com.controller.constants.JspPathsConst.USER_ORDER_LIST_VIEW;

/**
 * Created by vlad on 10.04.17.
 */
public class ShowOrderListCommand implements Command {
    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        OrderItemList orderItemList =(OrderItemList) request.getSession().getAttribute("orderList");

        if(orderItemList !=null) {
            OrderService service= OrderServiceImpl.getInstance();

            /*key - > book's id field*/
            Map<Integer,OrderItem> sessionBookOrders=orderItemList.getBookOrders();

            /*get books from session object*/
            Map<Book,OrderItem > detailBookOrders=service.getDetailedBookOrders(sessionBookOrders);

            request.setAttribute("bookOrders", detailBookOrders);
        }

        return new ForwardViewDispatcher(USER_ORDER_LIST_VIEW);
    }
}
