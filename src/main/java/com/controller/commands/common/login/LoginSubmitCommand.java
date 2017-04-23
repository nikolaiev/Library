package com.controller.commands.common.login;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.responce.Dispatcher;
import com.controller.responce.ForwardViewDispatcher;
import com.controller.responce.RedirectDispatcher;
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
    UserService service=new UserServiceImpl();
    Map<UserRole,String> defaultLoggedInPage=new HashMap<>();
    {
        defaultLoggedInPage.put(UserRole.ADMIN,"/admin/books");
        defaultLoggedInPage.put(UserRole.USER,"/user/books");
    }
    @Override
    public Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO list
        /*
        * 1. validate data from request
        * 2. check if already logged in
        * 3. get user from DB
        *   3.1 if user does not exists redirect to login page with error
        *   else
        *   3.1 login user end send jsp by his role
        * */

        /*clearing request data*/
        if(isNotValidLoginData(request)){
            //TODO write code
        }

        if(isUserLoggedIn(request)){
            UserRole role=(UserRole)request.getSession().getAttribute("userRole");
            return new ForwardViewDispatcher(defaultLoggedInPage.get(role));
        }

        //user is attempts to log in first time and has valid login data
        String login=request.getParameter("login");
        String password=request.getParameter("password");
        Optional<User> user=service.getUserByLogin(login);

        if(user.isPresent()){
            if(user.get().getPassword().equals(password)){
                HttpSession session=request.getSession(true);

                UserRole role=user.get().getRole();

                session.setAttribute("userId",user.get().getId());
                session.setAttribute("userRole",role);

                String redirectPage=request.getContextPath()+defaultLoggedInPage.get(role);

                return new RedirectDispatcher(redirectPage);
            }
        }
        //TODO use error flag but not message
        String errorMessage=escapeUrlCharacters("Login or password is incorrect!");
        String redirectPage=request.getContextPath()+"/login?error="+errorMessage;

        return new RedirectDispatcher(redirectPage);
    }

    private boolean isUserLoggedIn(HttpServletRequest request){
        if(request.getSession()!=null
                && request.getSession().getAttribute("userId")!=null)
            return true;

        return false;
    }

    private boolean isNotValidLoginData(HttpServletRequest request){
        //TODO implement
        return true;
    }
}
