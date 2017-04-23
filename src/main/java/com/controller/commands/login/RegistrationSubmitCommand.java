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
import com.service.UserService;
import com.service.impl.UserServiceImpl;

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
    private static final String SUCCESSFUL_REGISTRATION="Registration was successful";
    private static final String LOG_ERROR_REGISTRATION_VALIDATION_ERROR="Registration data is not valid";

    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RegistrationData registrationData=getRegistrationDataFromReq(request);
        Validator<RegistrationData> registrationDataValidator=new RegisterDataValidator();

        if(!registrationDataValidator.isValid(registrationData)){
            return new ValidationErrorViewDispatcher(LOGIN_REG_VIEW,registrationDataValidator);
        }

        UserService service= UserServiceImpl.getInstance();

        /*find possible user duplicate*/
        Optional<User> userDuplicate =service.getUserByLogin(registrationData.getLogin());

        /*check password*/
        if(userDuplicate.isPresent()){
            //redirect to login page with error message
            registrationDataValidator.addError(LOG_ERROR_USER_ALREADY_EXISTS);
            return new ValidationErrorViewDispatcher(LOGIN,registrationDataValidator);
        }

        String password=registrationData.getPassword();
        String confPassword=registrationData.getPasswordConfirm();

        if(!password.equals(confPassword)){
            //TODO add params to page
            return new RedirectDispatcher(LOGIN);
        }
        else {
            /*creating new User*/
            User user=new User.Builder()
                    .setLogin(registrationData.getLogin())
                    .setName(registrationData.getName())
                    .setSoname(registrationData.getSoname())
                    .setPassword(password)
                    .setRole(UserRole.USER)
                    .build();

            service.create(user);
            //TODO pass message to page
            return new RedirectDispatcher(LOGIN);
        }
    }

    private RegistrationData getRegistrationDataFromReq(HttpServletRequest request) {
        ParamExtractor paramExtractor=new RequestParamExtractor();

        String name = paramExtractor.getStringParam(request,"name");
        String soname = paramExtractor.getStringParam(request,"soname");
        String password = paramExtractor.getStringParam(request,"password");
        String confPassword = paramExtractor.getStringParam(request,"confirm-password");
        String login = paramExtractor.getStringParam(request,"email");

        return new RegistrationData(login,password,confPassword,name,soname);
    }
}
