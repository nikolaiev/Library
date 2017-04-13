package com.controller.commands.user;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 12.04.17.
 */
public class ProfileCommand extends CommandWrapper implements Command {
    @Override
    protected String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO add all necessary data to request
        return request.getContextPath()+"/WEB-INF/view/user/profilePage.jsp";
    }
}
