package com.controller.commands.login;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.commands.helper.ParamExtractor;
import com.controller.commands.helper.RequestParamExtractor;
import com.controller.responce.Dispatcher;
import com.controller.responce.RedirectDispatcher;
import com.controller.responce.ValidationErrorViewDispatcher;
import com.controller.validation.RegisterDataValidator;
import com.controller.validation.Validator;
import com.controller.validation.model.RegistrationData;
import com.model.entity.user.User;
import com.model.entity.user.UserRole;
import com.service.impl.ServiceFactory;
import com.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.controller.constants.JspPathsConst.LOGIN_REG_VIEW;
import static com.controller.constants.UrlsConst.LOGIN;

/**
 * Created by vlad on 18.04.17.
 */
public class RegistrationSubmitCommand extends CommandWrapper implements Command {
    private static final String LOG_ERROR_USER_ALREADY_EXISTS="User already exists";

    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RegistrationData registrationData=getRegistrationDataFromReq(request);
        Validator<RegistrationData> registrationDataValidator=new RegisterDataValidator();


        if(!registrationDataValidator.isValid(registrationData)){
            request.setAttribute("isRegistrationAttempt",true);
            return new ValidationErrorViewDispatcher(LOGIN_REG_VIEW,registrationDataValidator);
        }

        UserService service= ServiceFactory.getInstance().getUserService();

        /*find possible user duplicate*/
        Optional<User> userDuplicate =service.getUserByLogin(registrationData.getLogin());

        /*check login availability*/
        if(userDuplicate.isPresent()){
            //redirect to login page with error message
            request.setAttribute("isRegistrationAttempt",true);
            registrationDataValidator.addError(LOG_ERROR_USER_ALREADY_EXISTS);
            return new ValidationErrorViewDispatcher(LOGIN_REG_VIEW,registrationDataValidator);
        }

        String password=registrationData.getPassword();
        /*creating new User*/
        User user=new User.Builder()
                .setLogin(registrationData.getLogin())
                .setName(registrationData.getName())
                .setSoname(registrationData.getSoname())
                .setPassword(password)
                .setRole(UserRole.USER)
                .build();

        service.create(user);

        return new RedirectDispatcher(LOGIN)
                .addGetParam("success_message","Registration was successful!");

    }

    private RegistrationData getRegistrationDataFromReq(HttpServletRequest request) {
        String name = paramExtractor.getStringParam(request,"name");
        String soname = paramExtractor.getStringParam(request,"soname");
        String password = paramExtractor.getStringParam(request,"password");
        String confPassword = paramExtractor.getStringParam(request,"confirm-password");
        String login = paramExtractor.getStringParam(request,"email");

        return new RegistrationData(login,password,confPassword,name,soname);
    }
}
