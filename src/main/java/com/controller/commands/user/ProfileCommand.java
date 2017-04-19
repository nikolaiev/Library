package com.controller.commands.user;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.model.entity.order.Order;
import com.model.entity.order.OrderStatus;
import com.model.entity.order.OrderType;
import com.model.entity.user.User;
import com.service.OrderService;
import com.service.UserService;
import com.service.impl.OrderServiceImpl;
import com.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 12.04.17.
 */
public class ProfileCommand extends CommandWrapper implements Command {
    private static int DEFAULT_LIMIT_VALUE=20;

    @Override
    protected String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO make data validation!

        HttpSession session=request.getSession();
        int userId= (int) session.getAttribute("userId");

        String bookTitle = paramExtractor.getStringParamOrNull(request,"title");

        OrderStatus orderStatus = paramExtractor.getEnumParamOrNull(request,"status",OrderStatus.class);
        OrderType orderType = paramExtractor.getEnumParamOrNull(request,"type",OrderType.class);
        Date beforeDate=paramExtractor.getDateParamOrNull(request,"before_date");
        int limit= getLimitFromRequest(request,"limit",DEFAULT_LIMIT_VALUE);
        int offset= getOffsetFromRequest(request,"page",limit);

        /*services*/
        OrderService orderService= OrderServiceImpl.getInstance();
        UserService userService= UserServiceImpl.getInstance();

        /*get data for jsp*/
        List<Order> orders=orderService.getOrdersByParams(userId,bookTitle,orderStatus ,
                orderType,beforeDate,limit,offset);
        User user=userService.getUserById(userId).orElse(null);

        int ordersCount=orderService.getOrdersCountByParams(userId,bookTitle,orderStatus ,orderType,beforeDate);

        /*pagination count*/
        int totalPages=(int)Math.ceil((double) ordersCount/limit);

        request.setAttribute("orders",orders);
        request.setAttribute("user",user);
        request.setAttribute("statuses",OrderStatus.values());
        request.setAttribute("types",OrderType.values());
        request.setAttribute("totalPages",totalPages);
        request.setAttribute("totalCount",ordersCount);
        request.setAttribute("defLimit",DEFAULT_LIMIT_VALUE);

        return request.getContextPath()+"/WEB-INF/view/user/profilePage.jsp";
    }

}
