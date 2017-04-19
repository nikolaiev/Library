package com.controller.commands.login;

import com.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vlad on 03.04.17.
 */
public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();

        if(session!=null)
            session.invalidate();

        response.sendRedirect(request.getContextPath()+"/login");
        return "REDIRECTED";
    }
}
