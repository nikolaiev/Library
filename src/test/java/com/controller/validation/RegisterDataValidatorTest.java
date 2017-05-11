package com.controller.validation;

import com.controller.validation.model.RegistrationData;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vladyslav_Nikolaiev on 11-May-17.
 */
public class RegisterDataValidatorTest {
    RegisterDataValidator validator=new RegisterDataValidator();
    RegistrationData registrationDataOk=new RegistrationData("test@i.ua","1111",
            "1111","Name","Soname");
    RegistrationData registrationDataError=new RegistrationData("testi.ua","1111",
            "1111","Name","Soname");

    @Test
    public void isValidTrue() throws Exception {
        boolean result=validator.isValid(registrationDataOk);
        assertTrue(result);
    }

    @Test
    public void isValidFalse() throws Exception {
        boolean result=validator.isValid(registrationDataError);
        assertFalse(result);
    }
}