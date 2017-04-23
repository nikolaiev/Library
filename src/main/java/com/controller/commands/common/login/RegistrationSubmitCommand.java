package com.controller.commands.common.login;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.responce.Dispatcher;
import com.controller.responce.RedirectDispatcher;
import com.model.entity.user.User;
import com.model.entity.user.UserRole;
import com.service.UserService;
import com.service.impl.UserServiceImpl;
import jdk.nashorn.internal.ir.Optimistic;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.controller.constants.UrlsConst.REDIRECTED;

/**
 * Created by vlad on 18.04.17.
 */
public class RegistrationSubmitCommand extends CommandWrapper implements Command {
    private static final String LOG_ERROR_USER_ALREADY_EXISTS="User already exists";
    private static final String SUCCESSFUL_REGISTRATION="Registration was successful";
    private static final String LOG_ERROR_REGISTRATION_VALIDATION_ERROR="Registration data is not valid";
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //TODO implement validation!!!
        String name=request.getParameter("name");
        String soname=request.getParameter("soname");
        String password=request.getParameter("password");
        String confPassword=request.getParameter("confirm-password");
        String login=request.getParameter("email");

        UserService service= UserServiceImpl.getInstance();

        /*find possible user duplicate*/
        Optional<User> userDuplicate =service.getUserByLogin(login);

        /*check password*/
        if(userDuplicate.isPresent()){
            String errorMessage=escapeUrlCharacters(LOG_ERROR_USER_ALREADY_EXISTS);
            //redirect to login page with successful message
            String redirectPage=request.getContextPath()+"/login?error="+errorMessage;
            return new RedirectDispatcher(redirectPage);
        }

        if(!password.equals(confPassword)){
            String errorMessage=escapeUrlCharacters(LOG_ERROR_REGISTRATION_VALIDATION_ERROR);
            String redirectPage=request.getContextPath()+"/login?error="+errorMessage;
            return new RedirectDispatcher(redirectPage);
        }
        else {
            /*creating new User*/
            User user=new User.Builder()
                    .setLogin(login)
                    .setName(name)
                    .setSoname(soname)
                    .setPassword(password)
                    .setRole(UserRole.USER)
                    .build();
            service.create(user);

            String successMessage=escapeUrlCharacters(SUCCESSFUL_REGISTRATION);
            //redirect to login page with successful message
            String redirectPage=request.getContextPath()+"/login?success_message="+successMessage;

            return new RedirectDispatcher(redirectPage);
        }
    }
}
