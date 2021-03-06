package com.controller.commands.user;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.commands.dto.OrderItemList;
import com.controller.responce.Dispatcher;
import com.controller.responce.EmptyDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vlad on 11.04.17.
 */
public class RemoveBookFromOrderListCommand extends CommandWrapper implements Command {
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();
        OrderItemList orderList= (OrderItemList) session.getAttribute("orderList");

        if(orderList!=null){
            int bookId=paramExtractor.getIntParam(request,"id");
            orderList.removeBookFromList(bookId);
        }

        return new EmptyDispatcher();
    }
}
