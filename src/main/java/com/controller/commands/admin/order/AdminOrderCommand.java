package com.controller.commands.admin.order;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.responce.Dispatcher;
import com.controller.responce.ForwardViewDispatcher;
import com.model.entity.order.Order;
import com.model.entity.order.OrderStatus;
import com.model.entity.order.OrderType;
import com.model.entity.user.User;
import com.service.OrderService;
import com.service.impl.ServiceFactory;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import static com.controller.constants.JspPathsConst.ADMIN_ORDER_VIEW;

/**
 * Created by vlad on 10.04.17.
 */
public class AdminOrderCommand extends CommandWrapper implements Command {
    private static final int DEFAULT_LIMIT_VALUE=20;
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /*OrderService service= OrderServiceImpl.getInstance();
        List<Order> orderList=service.getAllOrders();
        request.setAttribute("orders",orderList);*/

        Integer userId= paramExtractor.getIntParamOrNull(request,"user_id");

        String bookTitle = paramExtractor.getStringParamOrNull(request, "table_title");

        OrderStatus orderStatus = paramExtractor.getEnumParamOrNull(request,"status",OrderStatus.class);
        OrderType orderType = paramExtractor.getEnumParamOrNull(request,"type",OrderType.class);
        Date beforeDate=paramExtractor.getDateParamOrNull(request,"before_date");
        int limit= getLimitFromRequest(request,"limit",DEFAULT_LIMIT_VALUE);
        int offset= getOffsetFromRequest(request,"page",limit);

        /*services*/
        OrderService orderService= ServiceFactory.getInstance().getOrderService();

        /*get data for jsp*/
        List<Order> orders=orderService.getOrdersByParams(userId,bookTitle,orderStatus ,
                orderType,beforeDate,limit,offset);

        User user=getUserOrNullByParam(userId);

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

        return new ForwardViewDispatcher(ADMIN_ORDER_VIEW);
    }

    private User getUserOrNullByParam(Integer userId) {
        UserService userService= ServiceFactory.getInstance().getUserService();
        User user=null;

        if(userId!=null)
            user=userService.getById(userId).orElse(null);
        return user;
    }
}
