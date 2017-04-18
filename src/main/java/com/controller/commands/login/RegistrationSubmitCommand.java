package com.controller.commands.login;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.model.entity.user.User;
import com.model.entity.user.UserRole;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

import javax.jws.soap.SOAPBinding;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 18.04.17.
 */
public class RegistrationSubmitCommand extends CommandWrapper implements Command {
    @Override
    protected String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //TODO implement validation!!!
        String name=request.getParameter("name");
        String soname=request.getParameter("soname");
        String password=request.getParameter("password");
        String confPassword=request.getParameter("confirm-password");
        String email=request.getParameter("email");

        if(!password.equals(confPassword)){
            String errorMessage=escapeUrlCharacters("Registration data is not valid");
            //redirect to login page with successful message
            response.sendRedirect("/login?error="+errorMessage);
            return "REDIRECTED";
        }

        User user=new User.Builder()
                .setLogin(email)
                .setName(name)
                .setSoname(soname)
                .setPassword(password)
                .setRole(UserRole.USER)
                .build();

        UserService service= UserServiceImpl.getInstance();

        service.createNewUser(user);

        String successMessage=escapeUrlCharacters("Registration was successful");
        //redirect to login page with successful message
        response.sendRedirect("/login?success_message="+successMessage);
        return "REDIRECTED";
    }

    //TODO move into abstract class
    private String escapeUrlCharacters(String message) {
        return message.replace(" ","%20");
    }
}
