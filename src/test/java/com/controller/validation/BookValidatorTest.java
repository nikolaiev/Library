package com.controller.validation;

import com.model.entity.book.Author;
import com.model.entity.book.Book;
import com.model.entity.book.BookLanguage;
import com.model.entity.book.Publisher;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZoneOffset;

import static org.junit.Assert.*;

/**
 * Created by Vladyslav_Nikolaiev on 11-May-17.
 */
public class BookValidatorTest {
    Book bookError = new Book.Builder()
            .setAuthor(new Author())
            .setCount(-1)
            .setGenre(null)
            .setId(-123)
            .setImage("")
            .setLanguage(BookLanguage.ENG)
            .setPublisher(new Publisher())
            .setInstant(LocalDateTime.now().toInstant(ZoneOffset.UTC))
            .setTitle("Test error book")
            .build();

    Book bookOk = new Book.Builder()
            .setAuthor(new Author())
            .setCount(10)
            .setGenre(null)
            .setId(1)
            .setImage("")
            .setLanguage(BookLanguage.ENG)
            .setPublisher(new Publisher())
            .setInstant(LocalDateTime.now().toInstant(ZoneOffset.UTC))
            .setTitle("Test error book")
            .build();

    BookValidator validator=new BookValidator();

    @Test
    public void isValidFalse() throws Exception {
        boolean result=validator.isValid(bookError);
        assertFalse(result);
    }

    @Test
    public void isValidTrue() throws Exception {
        boolean result=validator.isValid(bookOk);
        assertTrue(result);
    }

}