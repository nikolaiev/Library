package com.controller.commands;

import com.controller.commands.admin.author.AdminAddAuthorCommand;
import com.controller.commands.admin.author.AdminAuthorCommand;
import com.controller.commands.admin.author.AdminRemoveAuthorCommand;
import com.controller.commands.admin.book.AdminAddBookCommand;
import com.controller.commands.admin.book.AdminBookCommand;
import com.controller.commands.admin.book.AdminRemoveBookCommand;
import com.controller.commands.admin.book.AdminUpdateBookCommand;
import com.controller.commands.admin.order.AdminOrderCommand;
import com.controller.commands.admin.publisher.AdminAddPublisherCommand;
import com.controller.commands.admin.order.AdminChangeOrderStatusCommand;
import com.controller.commands.admin.publisher.AdminPublisherCommand;
import com.controller.commands.admin.publisher.AdminRemovePublisherCommand;
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
        commands.put(GET_PATH+"/user/process",new ProcessOrderListCommand());
        commands.put(GET_PATH+"/user/orders",new ShowOrderListCommand());
        commands.put(GET_PATH+"/user/profile",new ProfileCommand());

        commands.put(POST_PATH+"/user/books/add",new AddBookToOrderListCommand());
        commands.put(POST_PATH+"/user/books/remove",new RemoveBookFromOrderListCommand());

        /*ADMIN COMMANDS*/
        commands.put(GET_PATH+"/admin/books",new AdminBookCommand());
        commands.put(GET_PATH+"/admin/authors",new AdminAuthorCommand());
        commands.put(GET_PATH+"/admin/publishers",new AdminPublisherCommand());
        commands.put(GET_PATH+"/admin/orders",new AdminOrderCommand());

        /*author*/
        commands.put(POST_PATH+"/admin/authors/add",new AdminAddAuthorCommand());
        commands.put(POST_PATH+"/admin/authors/remove",new AdminRemoveAuthorCommand());

        /*books*/
        commands.put(POST_PATH+"/admin/books/add",new AdminAddBookCommand());
        commands.put(POST_PATH+"/admin/books/remove",new AdminRemoveBookCommand());
        commands.put(POST_PATH+"/admin/books/update",new AdminUpdateBookCommand());

        /*order*/
        commands.put(POST_PATH+"/admin/orders/update",new AdminChangeOrderStatusCommand());

        /*publisher*/
        commands.put(POST_PATH+"/admin/publishers/add",new AdminAddPublisherCommand());
        commands.put(POST_PATH+"/admin/publishers/remove",new AdminRemovePublisherCommand());
    }

    public CommandHolder(){}

    public Command getCommand(String url){
        return commands.getOrDefault(url, INVALID_URL_COMMAND);
    }
}
