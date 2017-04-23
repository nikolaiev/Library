package com.controller;

import com.controller.commands.Command;
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
import com.controller.commands.GetStaticFileCommand;
import com.controller.commands.login.LoginCommand;
import com.controller.commands.login.LoginSubmitCommand;
import com.controller.commands.login.LogoutCommand;
import com.controller.commands.login.RegistrationSubmitCommand;
import com.controller.commands.user.*;
import com.controller.commands.GoInvalidUrlCommand;

import java.util.HashMap;
import java.util.Map;

import static com.controller.constants.UrlsConst.*;

/**
 * Created by vlad on 03.04.17.
 */

//TODO think about regex URIs
public class CommandHolder {

    private final String GET_PATH="GET:";
    private final String POST_PATH="POST:";
    private final String DEPLOY_PATH;
    private final Command INVALID_URL_COMMAND;

    public CommandHolder(String deployPath){
        this.DEPLOY_PATH =deployPath;
        INVALID_URL_COMMAND =new GoInvalidUrlCommand();
        init();
    }

    private Map<String,Command> commands;

    private void init(){
        commands=new HashMap<>();

        /*COMMON COMMANDS*/
        /*login logout commands*/
        commands.put(GET_PATH + DEPLOY_PATH + LOGIN ,new LoginCommand());
        commands.put(POST_PATH + DEPLOY_PATH + LOGIN,new LoginSubmitCommand());
        commands.put(POST_PATH + DEPLOY_PATH + REGISTER,new RegistrationSubmitCommand());
        commands.put(GET_PATH + DEPLOY_PATH + LOGOUT,new LogoutCommand());

        /*USER COMMAND*/
        /*book commands*/
        commands.put(GET_PATH + DEPLOY_PATH + USER_BOOKS,new FindBookCommand());
        commands.put(GET_PATH + DEPLOY_PATH + USER_ORDERS,new ShowOrderListCommand());
        commands.put(GET_PATH + DEPLOY_PATH + USER_PROFILE,new ProfileCommand());

        commands.put(POST_PATH + DEPLOY_PATH + USER_PROCESS_ORDERS,new ProcessOrderListCommand());
        commands.put(POST_PATH + DEPLOY_PATH + USER_BOOKS_ADD,new AddBookToOrderListCommand());
        commands.put(POST_PATH + DEPLOY_PATH + USER_BOOKS_REMOVE,new RemoveBookFromOrderListCommand());

        /*ADMIN COMMANDS*/
        commands.put(GET_PATH + DEPLOY_PATH + ADMIN_BOOKS,new AdminBookCommand());
        commands.put(GET_PATH + DEPLOY_PATH + ADMIN_AUTHORS,new AdminAuthorCommand());
        commands.put(GET_PATH + DEPLOY_PATH + ADMIN_PUBLISHERS,new AdminPublisherCommand());
        commands.put(GET_PATH + DEPLOY_PATH + ADMIN_ORDERS,new AdminOrderCommand());

        /*author*/
        commands.put(POST_PATH + DEPLOY_PATH + ADMIN_AUTHORS_ADD,new AdminAddAuthorCommand());
        commands.put(POST_PATH + DEPLOY_PATH + ADMIN_AUTHORS_REMOVE,new AdminRemoveAuthorCommand());

        /*books*/
        commands.put(POST_PATH + DEPLOY_PATH + ADMIN_BOOKS_ADD,new AdminAddBookCommand());
        commands.put(POST_PATH + DEPLOY_PATH + ADMIN_BOOKS_REMOVE,new AdminRemoveBookCommand());
        commands.put(POST_PATH + DEPLOY_PATH + ADMIN_BOOKS_UPDATE,new AdminUpdateBookCommand());

        /*order*/
        commands.put(POST_PATH + DEPLOY_PATH + ADMIN_ORDERS_UPDATE,new AdminChangeOrderStatusCommand());

        /*publisher*/
        commands.put(POST_PATH + DEPLOY_PATH + ADMIN_PUBLISHERS_ADD,new AdminAddPublisherCommand());
        commands.put(POST_PATH + DEPLOY_PATH + ADMIN_PUBLISHERS_REMOVE,new AdminRemovePublisherCommand());
    }

    public Command getCommand(String url){

        if(url.startsWith(GET_PATH + DEPLOY_PATH + STATIC))
            return new GetStaticFileCommand();

        return commands.getOrDefault(url, INVALID_URL_COMMAND);
    }
}
