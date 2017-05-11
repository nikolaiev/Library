package com.controller.validation;

import com.model.entity.book.Publisher;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Vladyslav_Nikolaiev on 11-May-17.
 */
public class PublisherValidatorTest {
    PublisherValidator validator=new PublisherValidator();

    Publisher publisherOk=new Publisher("Title");
    Publisher publisherError=new Publisher("$(*&@#$^)@#$*@#$e");

    @Test
    public void isValidFalse() throws Exception {
        boolean result=validator.isValid(publisherError);
        assertFalse(result);
    }

    @Test
    public void isValidTrue() throws Exception {
        boolean result=validator.isValid(publisherOk);
        assertTrue(result);
    }

}