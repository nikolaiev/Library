package com.controller.commands.common;

import com.controller.commands.Command;
import com.controller.commands.login.LogoutCommand;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by vlad on 03.04.17.
 */
public class GoInvalidUrlCommand implements Command {


    private static final String REQUESTED_UNSUPPORTED_URI = "Requested unsupported URI. Redirecting to home page.";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setAttribute("error",REQUESTED_UNSUPPORTED_URI);
        response.sendRedirect(request.getContextPath() + "/login");
        return "REDIRECTED";
    }
}
