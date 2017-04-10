package com.service.impl;

import com.model.entity.book.Book;
import com.service.BookService;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by vlad on 03.04.17.
 */
public class BookServiceImplTest {
    BookService service;

    @Before
    public void init(){
        service= BookServiceImpl.getInstance();
    }

    @Test
    public void getAllBooks() throws Exception {
        List<Book> result=service.getAllBooks();
        System.out.println(result.size());
        assertNotNull(result);
    }

    @Test
    public void getBooksByTitle() throws Exception {
        List<Book> result=service.getBooksByTitle("8uper");
        System.out.println(result.size());
        //System.out.println(result.get(0));
        assertNotNull(result);
    }

    @Test
    public void getBooksByAuthor() throws Exception {

    }

    @Test
    public void getBooksByPublisher() throws Exception {

    }

}