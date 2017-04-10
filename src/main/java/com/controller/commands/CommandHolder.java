package com.controller.commands;

import com.controller.commands.admin.author.AdminAddAuthorCommand;
import com.controller.commands.admin.book.AdminAddBookCommand;
import com.controller.commands.admin.publisher.AdminAddPublisherCommand;
import com.controller.commands.admin.order.AdminChangeOrderStatusCommand;
import com.controller.commands.user.*;
import com.controller.commands.common.GoHomeCommand;
import com.controller.commands.common.GoInvalidUrlCommand;
import com.controller.commands.login.LogoutCommand;
import com.controller.commands.login.LoginCommand;
import com.controller.commands.login.LoginSubmitCommand;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlad on 03.04.17.
 */
public class CommandHolder {

    private final String GET_PATH="GET:";
    private final String POST_PATH="POST:";
    private final Command INVALID_URL_COMMAND;

    private Map<String,Command> commands;
    {
        INVALID_URL_COMMAND =new GoInvalidUrlCommand();
        commands=new HashMap<>();

        /*COMMON COMMANDS*/
        /*login logout commands*/
        commands.put(GET_PATH+"/login",new LoginCommand());
        commands.put(POST_PATH+"/login",new LoginSubmitCommand());
        commands.put(GET_PATH+"/logout",new LogoutCommand());

        /*USER COMMAND*/
        /*home command*/
        commands.put(GET_PATH+"/user/home",new GoHomeCommand());
        /*book commands*/
        commands.put(GET_PATH+"/user/books",new FindBookCommand());
        //commands.put(GET_PATH+"/orderBook",new OrderBookCommand());

        commands.put(GET_PATH+"/user/process",new ProcessOrderListCommand());
        commands.put(GET_PATH+"/user/orders",new ShowOrderListCommand());
        commands.put(POST_PATH+"/user/books/addToOrderList",new AddBookToOrderListCommand());

        /*ADMIN COMMANDS*/
        commands.put(GET_PATH+"/admin/addBook",new AdminAddBookCommand());
        commands.put(GET_PATH+"/admin/authors",new AdminAddAuthorCommand());
        commands.put(GET_PATH+"/admin/addPublisher",new AdminAddPublisherCommand());
        commands.put(GET_PATH+"/admin/changeOrderStatus",new AdminChangeOrderStatusCommand());
    }

    public CommandHolder(){}

    public Command getCommand(String url){
        return commands.getOrDefault(url, INVALID_URL_COMMAND);
    }
}
