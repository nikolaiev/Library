package com.controller.commands.login;

import com.controller.commands.Command;
import com.controller.responce.Dispatcher;
import com.controller.responce.ForwardViewDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.controller.constants.JspPathsConst.LOGIN_REG_VIEW;

/**
 * Created by vlad on 02.04.17.
 */
public class LoginCommand implements Command {
    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {

        return new ForwardViewDispatcher(LOGIN_REG_VIEW);
    }
}
