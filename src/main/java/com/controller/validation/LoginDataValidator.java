package com.controller.validation;

import com.controller.validation.model.LoginData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.controller.constants.RegExConst.LOGIN_REG_EX;

/**
 * Created by vlad on 23.04.17.
 */
public class LoginDataValidator extends AbstractValidator <LoginData>  {

    private static final String LOGIN_IS_NOT_VALID="Email is not valid!";

    @Override
    public boolean isValid(LoginData loginData) {
        String login=loginData.getLogin();

        if(!isLoginValid(login)){
            addError(LOGIN_IS_NOT_VALID);
            return false;
        }

        return true;
    }


    private boolean isLoginValid(String login){
        Pattern validLoginPattern=Pattern.compile(LOGIN_REG_EX,Pattern.CASE_INSENSITIVE);
        Matcher matcher = validLoginPattern.matcher(login);
        return matcher.find();
    }

}
