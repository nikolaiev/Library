package com.controller;

import com.controller.commands.Command;
import com.controller.commands.FindBookCommand;
import com.controller.commands.admin.author.AdminAddAuthorCommand;
import com.controller.commands.admin.author.AdminAuthorCommand;
import com.controller.commands.admin.author.AdminRemoveAuthorCommand;
import com.controller.commands.admin.book.AdminAddBookCommand;
import com.controller.commands.admin.book.AdminAddBookSubmitCommand;
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

import static com.controller.constants.JspPathsConst.ADMIN_BOOKS_VIEW;
import static com.controller.constants.JspPathsConst.USER_BOOKS_VIEW;
import static com.controller.constants.UrlsConst.*;

/**
 * Created by vlad on 03.04.17.
 */

public class CommandFabric {

    private final static String GET_PATH="GET:";
    private final static String POST_PATH="POST:";
    private final Command invalidUrlCommand;
    private final Map<String,Command> commands;

    /**
     * Must be private
     */
    private CommandFabric(){
        this.invalidUrlCommand =new GoInvalidUrlCommand();
        commands=new HashMap<>();
        init();
    }

    /**
     * Lazy instance holder
     */
    private static class InstanceHolder{
        static CommandFabric INSTANCE=new CommandFabric();
    }

    /**
     * Static method
     * @return singleton instance
     */
    public static CommandFabric getInstance(){
        return InstanceHolder.INSTANCE;
    }

    /**
     * Returns Command
     * @param url requested url
     * @return appropriate Command object
     */
    Command getCommand(String url){

        Command command=commands.get(url);

        if(command!=null)
            return command;

        //check static mapped file request URL
        if(url.startsWith(GET_PATH + STATIC))
            return new GetStaticFileCommand();

        //possible GET params passed
        return commands.entrySet().stream()
                .filter(e->{
                    String urlPattern=e.getKey();
                    return url.startsWith(urlPattern+"?");
                })
                .findFirst()
                .map(Map.Entry::getValue)
                .orElse(invalidUrlCommand);
    }

    /**
     * Initialize CommandFabric object
     */
    private void init(){
        /*COMMON COMMANDS*/
        /*login logout commands*/
        commands.put(GET_PATH + LOGIN ,new LoginCommand());
        commands.put(GET_PATH + LOGOUT,new LogoutCommand());
        commands.put(POST_PATH + LOGIN,new LoginSubmitCommand());
        commands.put(POST_PATH + REGISTER,new RegistrationSubmitCommand());
        commands.put(GET_PATH + STATIC,new GetStaticFileCommand());

        /*USER COMMAND*/
        /*book commands*/
        commands.put(GET_PATH + USER_BOOKS,new FindBookCommand(USER_BOOKS_VIEW));
        commands.put(GET_PATH + USER_ORDERS,new ShowOrderListCommand());
        commands.put(GET_PATH + USER_PROFILE,new ProfileCommand());

        commands.put(POST_PATH + USER_PROCESS_ORDERS,new ProcessOrderListCommand());
        commands.put(POST_PATH + USER_BOOKS_ADD,new AddBookToOrderListCommand());
        commands.put(POST_PATH + USER_BOOKS_REMOVE,new RemoveBookFromOrderListCommand());

        /*ADMIN COMMANDS*/
        commands.put(GET_PATH + ADMIN_BOOKS,new FindBookCommand(ADMIN_BOOKS_VIEW));
        commands.put(GET_PATH + ADMIN_AUTHORS,new AdminAuthorCommand());
        commands.put(GET_PATH + ADMIN_PUBLISHERS,new AdminPublisherCommand());
        commands.put(GET_PATH + ADMIN_ORDERS,new AdminOrderCommand());

        /*author*/
        commands.put(POST_PATH + ADMIN_AUTHORS_ADD,new AdminAddAuthorCommand());
        commands.put(POST_PATH + ADMIN_AUTHORS_REMOVE,new AdminRemoveAuthorCommand());

        /*books*/
        commands.put(GET_PATH + ADMIN_BOOK_ADD,new AdminAddBookCommand());
        commands.put(POST_PATH + ADMIN_BOOK_ADD,new AdminAddBookSubmitCommand());
        commands.put(POST_PATH + ADMIN_BOOK_UPDATE,new AdminUpdateBookSubmitCommand());
        commands.put(GET_PATH+ ADMIN_BOOK,new AdminUpdateBookCommand());

        /*order*/
        commands.put(POST_PATH + ADMIN_ORDERS_UPDATE,new AdminChangeOrderStatusCommand());

        /*publisher*/
        commands.put(POST_PATH + ADMIN_PUBLISHERS_ADD,new AdminAddPublisherCommand());
        commands.put(POST_PATH + ADMIN_PUBLISHERS_REMOVE,new AdminRemovePublisherCommand());
    }
}
