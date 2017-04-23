package com.controller.validation;

import com.controller.validation.model.RegistrationData;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.controller.constants.RegExConst.LOGIN_REG_EX;
import static com.controller.constants.RegExConst.NAME_SONAME_REG_EX;

/**
 * Created by vlad on 23.04.17.
 */
public class RegisterDataValidator extends AbstractValidator<RegistrationData> {

    private static final String LOGIN_IS_NOT_VALID="Email is not valid!";
    private static final String NAME_IS_NOT_VALID = "Name is not valid";
    private static final String SONAME_IS_NOT_VALID = "Soname is not valid";
    private static final String PASSWORDS_ARE_DIFFERENT = "Passwords do not not match";


    @Override
    public boolean isValid(RegistrationData registrationData) {
        if(!isLoginValid(registrationData.getLogin())){
            addError(LOGIN_IS_NOT_VALID);
            return false;
        }

        if(!isNameSonameValid(registrationData.getName())){
            addError(NAME_IS_NOT_VALID);
            return false;
        }

        if(!isNameSonameValid(registrationData.getSoname())){
            addError(SONAME_IS_NOT_VALID);
            return false;
        }

        String pass=registrationData.getPassword();
        String passConf=registrationData.getPasswordConfirm();

        if(!isPasswordsEquals(pass,passConf)){
            addError(PASSWORDS_ARE_DIFFERENT);
            return false;
        }

        return true;
    }

    private boolean isNameSonameValid(String name){
        Pattern validLoginPattern=Pattern.compile(NAME_SONAME_REG_EX,Pattern.CASE_INSENSITIVE);
        Matcher matcher = validLoginPattern.matcher(name);
        return matcher.find();
    }

    private boolean isPasswordsEquals(String pass,String passConf){
        return pass.equals(passConf);
    }

    private boolean isLoginValid(String login){
        Pattern validLoginPattern=Pattern.compile(LOGIN_REG_EX,Pattern.CASE_INSENSITIVE);
        Matcher matcher = validLoginPattern.matcher(login);
        return matcher.find();
    }
}
