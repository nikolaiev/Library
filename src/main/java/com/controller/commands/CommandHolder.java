package com.controller.commands;

import com.controller.commands.common.FindBookCommand;
import com.controller.commands.common.GoHomeCommand;
import com.controller.commands.common.GoInvalidUrlCommand;
import com.controller.commands.common.LogoutCommand;
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
        /*login logout commands*/
        commands.put(GET_PATH+"/login",new LoginCommand());
        commands.put(POST_PATH+"/login",new LoginSubmitCommand());
        commands.put(GET_PATH+"/logout",new LogoutCommand());

        /*home command*/
        commands.put(GET_PATH+"/home",new GoHomeCommand());
        //commands.put(GET_PATH+"/",new GoHomeCommand());

        /*book commands*/
        commands.put(GET_PATH+"/books",new FindBookCommand());

        /*order command*/
        //commands.put(GET_PATH)
    }

    public CommandHolder(){}

    public Command getCommand(String url){
        return commands.getOrDefault(url, INVALID_URL_COMMAND);
    }
}
