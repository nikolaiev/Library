package com.controller.validation;

import com.model.entity.book.Author;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vladyslav_Nikolaiev on 11-May-17.
 */
public class AuthorValidatorTest {
    Author authorErrorSoname=new Author("Name","o234j12h3g41j2");
    Author authorErrorName=new Author("kshfkljsd123fk","o234j12h3g41j2");
    Author authorOk=new Author("Name","Soname");
    AuthorValidator validator=new AuthorValidator();

    @Test
    public void isValidFalseName() throws Exception {
        boolean result=validator.isValid(authorErrorName);
        assertFalse(result);
    }
    @Test
    public void isValidFalseSoname() throws Exception {
        boolean result=validator.isValid(authorErrorSoname);
        assertFalse(result);
    }

    @Test
    public void isValidTrue() throws Exception {
        boolean result=validator.isValid(authorOk);
        assertTrue(result);
    }

}