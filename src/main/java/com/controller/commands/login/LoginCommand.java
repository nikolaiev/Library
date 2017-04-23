package com.controller.commands.login;

import com.controller.commands.Command;
import com.controller.responce.Dispatcher;
import com.controller.responce.ForwardViewDispatcher;
import com.controller.responce.RedirectDispatcher;
import com.model.entity.user.UserRole;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.controller.constants.JspPathsConst.LOGIN_REG_VIEW;
import static com.controller.constants.UrlsConst.ADMIN_BOOKS;
import static com.controller.constants.UrlsConst.USER_BOOKS;

/**
 * Created by vlad on 02.04.17.
 */
public class LoginCommand implements Command {
    private static final Logger logger=Logger.getLogger(LoginCommand.class);

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) {

        HttpSession session=request.getSession();
        UserRole role=(UserRole) session.getAttribute("userRole");

        /*redirect if user logged in*/
        if(role!=null){

            logger.warn("Authorized user process /login path");

            if(role.equals(UserRole.USER)){
                return  new RedirectDispatcher(USER_BOOKS);
            }
            if(role.equals(UserRole.ADMIN)){
                return  new RedirectDispatcher(ADMIN_BOOKS);
            }
        }

        //user is not logged in
        return new ForwardViewDispatcher(LOGIN_REG_VIEW);
    }
}
