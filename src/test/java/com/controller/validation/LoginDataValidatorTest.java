package com.controller.validation;

import com.controller.validation.model.LoginData;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vladyslav_Nikolaiev on 11-May-17.
 */
public class LoginDataValidatorTest {
    LoginDataValidator validator=new LoginDataValidator();
    LoginData loginDataError=new LoginData("asdfasdf","123sss$$$");
    LoginData loginDataOk=new LoginData("test@i.ua","123Password");

    @Test
    public void isValidTrue() throws Exception {
        boolean result=validator.isValid(loginDataOk);
        assertTrue(result);
    }

    @Test
    public void isValidFalse() throws Exception {
        boolean result=validator.isValid(loginDataError);
        assertFalse(result);
    }

}