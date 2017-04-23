package com.controller.commands.common.login;

import com.controller.commands.Command;
import com.controller.responce.Dispatcher;
import com.controller.responce.ForwardViewDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by vlad on 02.04.17.
 */
public class LoginCommand implements Command {
    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {

        return new ForwardViewDispatcher("/WEB-INF/view/loginPage.jsp");
    }
}
