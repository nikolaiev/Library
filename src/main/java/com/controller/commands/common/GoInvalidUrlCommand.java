package com.controller.commands.common;

import com.controller.commands.Command;
import com.controller.commands.login.LogoutCommand;
import com.model.entity.user.UserRole;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by vlad on 03.04.17.
 */
public class GoInvalidUrlCommand implements Command {


    private static final String REQUESTED_UNSUPPORTED_URI = "Requested unsupported URI. Redirecting to home page.";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();

        if(session.getAttribute("userId")==null){
            response.sendRedirect(request.getContextPath()+"/login");
            return "REDIRECTED";
        }

        request.setAttribute("error",REQUESTED_UNSUPPORTED_URI);
        return "/WEB-INF/view/errorPage.jsp";
    }
}
