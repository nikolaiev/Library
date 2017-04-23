package com.controller.commands;

import com.controller.responce.Dispatcher;
import com.controller.responce.ErrorViewDispatcher;
import com.controller.responce.RedirectDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vlad on 03.04.17.
 */
public class GoInvalidUrlCommand implements Command {


    private static final String REQUESTED_UNSUPPORTED_URI = "Requested unsupported URI. Redirecting to home page.";

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        HttpSession session=request.getSession();

        if(session.getAttribute("userId")==null){
            String redirectPage=request.getContextPath()+"/login";
            return  new RedirectDispatcher(redirectPage);
        }

        request.setAttribute("error",REQUESTED_UNSUPPORTED_URI);

        return new ErrorViewDispatcher("/WEB-INF/view/errorPage.jsp");
    }
}
