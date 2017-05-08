package com.controller;

import com.controller.commands.Command;
import com.controller.commands.FindBookCommand;
import com.controller.commands.admin.author.AdminAddAuthorCommand;
import com.controller.commands.admin.author.AdminAuthorCommand;
import com.controller.commands.admin.author.AdminRemoveAuthorCommand;
import com.controller.commands.admin.author.AdminUpdateAuthorCommand;
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
import com.controller.commands.admin.publisher.AdminUpdatePublishersCommand;
import com.controller.commands.login.LoginCommand;
import com.controller.commands.login.LoginSubmitCommand;
import com.controller.commands.login.LogoutCommand;
import com.controller.commands.login.RegistrationSubmitCommand;
import com.controller.commands.user.*;
import com.controller.commands.GoInvalidUrlCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    private final Map<String,Command> mappedCommands;

    /**
     * Must be private
     */
    private CommandFabric(){
        this.invalidUrlCommand =new GoInvalidUrlCommand();
        commands=new HashMap<>();
        mappedCommands=new HashMap<>();
        initRegularCommand();
        initMappedCommand();
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
        //get not mapped command
        Command command=getSimpleCommand(url);

        if(command!=null)
            return command;

        //get mapped command
        command=getMappedCommand(url);

        if(command!=null)
            return command;

        //possible GET params passed
        return invalidUrlCommand;
    }

    /**
     * Initialize CommandFabric object
     */
    private void initRegularCommand(){
        /*COMMON COMMANDS*/
        /*login logout commands*/
        commands.put(GET_PATH + LOGIN ,new LoginCommand());
        commands.put(GET_PATH + LOGOUT,new LogoutCommand());
        commands.put(POST_PATH + LOGIN,new LoginSubmitCommand());
        commands.put(POST_PATH + REGISTER,new RegistrationSubmitCommand());

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
        commands.put(POST_PATH + ADMIN_AUTHORS_UPDATE,new AdminUpdateAuthorCommand());

        /*books*/
        commands.put(GET_PATH + ADMIN_BOOK_ADD,new AdminAddBookCommand());
        commands.put(POST_PATH + ADMIN_BOOK_ADD,new AdminAddBookSubmitCommand());
        commands.put(POST_PATH + ADMIN_BOOK_UPDATE,new AdminUpdateBookSubmitCommand());


        /*order*/
        commands.put(POST_PATH + ADMIN_ORDERS_UPDATE,new AdminChangeOrderStatusCommand());

        /*publisher*/
        commands.put(POST_PATH + ADMIN_PUBLISHERS_ADD,new AdminAddPublisherCommand());
        commands.put(POST_PATH + ADMIN_PUBLISHERS_REMOVE,new AdminRemovePublisherCommand());
        commands.put(POST_PATH + ADMIN_PUBLISHERS_UPDATE,new AdminUpdatePublishersCommand());
    }

    /**
     * Initialize CommandFabric object
     */
    private void initMappedCommand() {
        //mappedCommands.put(POST_PATH + ADMIN_BOOK_UPDATE,new AdminUpdateBookSubmitCommand());
        mappedCommands.put(GET_PATH + ADMIN_BOOK,new AdminUpdateBookCommand());
        mappedCommands.put(GET_PATH + MAPPED_STATIC,new GetStaticFileCommand());
    }

    private Command getSimpleCommand(String url){
        return commands.get(url.split("\\?")[0]);
    }

    private Command getMappedCommand(String url){
        Set<String> mappedCommandsKeys=mappedCommands.keySet();
        String nakedUrl=url.split("\\?")[0];

        return mappedCommandsKeys.parallelStream()
                .filter(commandKey->nakedUrl.matches(commandKey.replaceAll("\\{.*}", ".*")))
                .findAny()
                .map(mappedCommands::get).orElse(null);
    }
}
