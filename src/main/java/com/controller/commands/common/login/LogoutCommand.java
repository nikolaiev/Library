package com.controller.commands.common.login;

import com.controller.commands.Command;
import com.controller.responce.Dispatcher;
import com.controller.responce.RedirectDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.controller.constants.UrlsConst.REDIRECTED;

/**
 * Created by vlad on 03.04.17.
 */
public class LogoutCommand implements Command {
    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session=request.getSession();

        if(session!=null)
            session.invalidate();

        String redirectPath=request.getContextPath()+"/login";

        return new RedirectDispatcher(redirectPath);
    }
}
