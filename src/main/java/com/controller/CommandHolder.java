package com.controller;

import com.controller.commands.Command;
import com.controller.commands.admin.author.AdminAddAuthorCommand;
import com.controller.commands.admin.author.AdminAuthorCommand;
import com.controller.commands.admin.author.AdminRemoveAuthorCommand;
import com.controller.commands.admin.book.AdminAddBookCommand;
import com.controller.commands.admin.book.AdminUpdateBookCommand;
import com.controller.commands.admin.book.AdminUpdateBookSubmitCommand;
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

import static com.controller.constants.JspPathsConst.ADMIN_BOOK_VIEW;
import static com.controller.constants.JspPathsConst.USER_BOOKS_VIEW;
import static com.controller.constants.UrlsConst.*;

/**
 * Created by vlad on 03.04.17.
 */

//TODO think about regex URIs
public class CommandHolder {

    private final static String GET_PATH="GET:";
    private final static String POST_PATH="POST:";
    private final String deplyPath;
    private final Command invalidUrlCommand;
    private Map<String,Command> commands;

    public CommandHolder(String deployPath){
        this.deplyPath =deployPath;
        this.invalidUrlCommand =new GoInvalidUrlCommand();
        init();
    }


    private void init(){
        commands=new HashMap<>();

        /*COMMON COMMANDS*/
        /*login logout commands*/
        commands.put(GET_PATH + deplyPath + LOGIN ,new LoginCommand());
        commands.put(GET_PATH + deplyPath + LOGOUT,new LogoutCommand());
        commands.put(POST_PATH + deplyPath + LOGIN,new LoginSubmitCommand());
        commands.put(POST_PATH + deplyPath + REGISTER,new RegistrationSubmitCommand());

        /*USER COMMAND*/
        /*book commands*/
        commands.put(GET_PATH + deplyPath + USER_BOOKS,new FindBookCommand(USER_BOOKS_VIEW));
        commands.put(GET_PATH + deplyPath + USER_ORDERS,new ShowOrderListCommand());
        commands.put(GET_PATH + deplyPath + USER_PROFILE,new ProfileCommand());

        commands.put(POST_PATH + deplyPath + USER_PROCESS_ORDERS,new ProcessOrderListCommand());
        commands.put(POST_PATH + deplyPath + USER_BOOKS_ADD,new AddBookToOrderListCommand());
        commands.put(POST_PATH + deplyPath + USER_BOOKS_REMOVE,new RemoveBookFromOrderListCommand());

        /*ADMIN COMMANDS*/
        commands.put(GET_PATH + deplyPath + ADMIN_BOOKS,new FindBookCommand(ADMIN_BOOK_VIEW));
        commands.put(GET_PATH + deplyPath + ADMIN_AUTHORS,new AdminAuthorCommand());
        commands.put(GET_PATH + deplyPath + ADMIN_PUBLISHERS,new AdminPublisherCommand());
        commands.put(GET_PATH + deplyPath + ADMIN_ORDERS,new AdminOrderCommand());

        /*author*/
        commands.put(POST_PATH + deplyPath + ADMIN_AUTHORS_ADD,new AdminAddAuthorCommand());
        commands.put(POST_PATH + deplyPath + ADMIN_AUTHORS_REMOVE,new AdminRemoveAuthorCommand());

        /*books*/
        commands.put(POST_PATH + deplyPath + ADMIN_BOOKS_ADD,new AdminAddBookCommand());
        //commands.put(POST_PATH + deplyPath + ADMIN_BOOKS_REMOVE,new AdminRemoveBookCommand());
        commands.put(POST_PATH + deplyPath + ADMIN_BOOK_UPDATE,new AdminUpdateBookSubmitCommand());
        commands.put(GET_PATH+ deplyPath + ADMIN_BOOK_UPDATE,new AdminUpdateBookCommand());

        /*order*/
        commands.put(POST_PATH + deplyPath + ADMIN_ORDERS_UPDATE,new AdminChangeOrderStatusCommand());

        /*publisher*/
        commands.put(POST_PATH + deplyPath + ADMIN_PUBLISHERS_ADD,new AdminAddPublisherCommand());
        commands.put(POST_PATH + deplyPath + ADMIN_PUBLISHERS_REMOVE,new AdminRemovePublisherCommand());
    }

    public Command getCommand(String url){

        if(url.startsWith(GET_PATH + deplyPath + STATIC))
            return new GetStaticFileCommand();

        return commands.getOrDefault(url, invalidUrlCommand);
    }
}
