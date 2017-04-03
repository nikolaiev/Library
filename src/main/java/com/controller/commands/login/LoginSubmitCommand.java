package com.controller.commands.login;

import com.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by vlad on 03.04.17.
 */

/*POST method*/
public class LoginSubmitCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        //TODO redirect if logged in ect.
        return null;
    }
}
