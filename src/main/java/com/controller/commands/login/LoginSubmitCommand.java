package com.controller.commands.login;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.responce.Dispatcher;
import com.controller.responce.ForwardViewDispatcher;
import com.controller.responce.RedirectDispatcher;
import com.controller.responce.ValidationErrorViewDispatcher;
import com.controller.validation.LoginDataValidator;
import com.controller.validation.Validator;
import com.controller.validation.model.LoginData;
import com.model.entity.user.User;
import com.model.entity.user.UserRole;
import com.service.UserService;
import com.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static com.controller.constants.UrlsConst.REDIRECTED;

/**
 * Created by vlad on 03.04.17.
 */

/*POST method*/
public class LoginSubmitCommand extends CommandWrapper implements Command{
    private UserService service=new UserServiceImpl();
    private final static String LOGIN_VIEW="/WEB-INF/view/loginPage.jsp";


    private Map<UserRole,String> defaultLoggedInPage=new HashMap<>();

    {
        defaultLoggedInPage.put(UserRole.ADMIN,"/admin/books");
        defaultLoggedInPage.put(UserRole.USER,"/user/books");
    }

    @Override
    public Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        LoginData loginData=getLoginDataFromReq(request);
        Validator<LoginData> loginDataValidator=new LoginDataValidator();

        /*clearing request data*/
        if(!loginDataValidator.isValid(loginData)){
            return new ValidationErrorViewDispatcher(LOGIN_VIEW,loginDataValidator);
        }

        /*attempt to login one more time*/
        if(isUserLoggedIn(request)){
            UserRole role=(UserRole)request.getSession().getAttribute("userRole");
            return new ForwardViewDispatcher(defaultLoggedInPage.get(role));
        }

        //user is attempts to log in first time and has valid login data
        String login=loginData.getLogin();
        String password=loginData.getPassword();

        Optional<User> user=service.getUserByLogin(login);

        if(user.isPresent()){
            if(user.get().getPassword().equals(password)){
                HttpSession session=request.getSession(true);

                UserRole role=user.get().getRole();

                session.setAttribute("userId",user.get().getId());
                session.setAttribute("userRole",role);

                return new RedirectDispatcher(defaultLoggedInPage.get(role));
            }
        }

        return new ValidationErrorViewDispatcher(LOGIN_VIEW,loginDataValidator);
    }

    private LoginData getLoginDataFromReq(HttpServletRequest request) {

        String login=request.getParameter("login");
        String password=request.getParameter("password");

        return new LoginData(login,password);
    }

    private boolean isUserLoggedIn(HttpServletRequest request){
        return request.getSession(false) != null
                && request.getSession().getAttribute("userId") != null;

    }

}
