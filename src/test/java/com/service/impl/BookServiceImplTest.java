package com.service.impl;

import com.model.entity.book.Book;
import com.model.entity.book.BookGenre;
import com.model.entity.book.BookLanguage;
import com.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.*;

/**
 * Created by vlad on 03.04.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {
    @Mock
    BookService bookService=mock(BookService.class);

    @Before
    public void init(){
        when(bookService.getAllBooks()).thenReturn(new ArrayList<Book>(){{
            add(new Book());
            add(new Book());
        }});

        when(bookService.getAllBooks(anyInt(),anyInt())).thenReturn(new ArrayList<Book>(){{
            add(new Book());
            add(new Book());
        }});


        int bookId=1;
        when(bookService.getById(bookId)).thenReturn(
                Optional.of(new Book.Builder()
                        .setId(bookId)
                        .build())
        );

        int publisherId=2;
        int authorId=1;
        int count=2;
        String title="book";

        when(bookService.getBooksCountByParams(title,authorId, BookGenre.DETECTIVE,
                BookLanguage.ENG,publisherId)).thenReturn(count);

        int limit=2;
        int offset=10;

        when(bookService.getBooksByParams(title,authorId, BookGenre.DETECTIVE,
                BookLanguage.ENG,publisherId,limit,offset)).thenReturn(new ArrayList<Book>(){{
            add(new Book());
            add(new Book());
        }});

        when(bookService.updateIfPossible(any(Book.class))).thenReturn(
                true
        );


    }

    @Test
    public void getBooksByParams() throws Exception {
        int limit=10;
        int offset=10;
        Integer authorId=null;
        Integer publisherId=null;

        List<Book> books=bookService.getBooksByParams("book",authorId,BookGenre.DETECTIVE,BookLanguage.ENG,
                publisherId,limit,offset);

        assertNotNull(books);

        int expectedInvocationTimes=1;
        verify(bookService,times(expectedInvocationTimes)).getBooksByParams("book",authorId,BookGenre.DETECTIVE,BookLanguage.ENG,
                publisherId,limit,offset);


    }

    @Test
    public void getBooksCountByParams() throws Exception {
        int publisherId=2;
        int authorId=1;
        int expectedCount=2;
        String title="book";

        int actualCount=bookService.getBooksCountByParams("book",authorId,BookGenre.DETECTIVE,BookLanguage.ENG,
                publisherId);


        int expectedInvocationTimes=1;

        assertEquals(expectedCount,actualCount);

        verify(bookService,times(expectedInvocationTimes)).getBooksCountByParams("book",authorId,BookGenre.DETECTIVE,BookLanguage.ENG,
                publisherId);
    }

    @Test
    public void updateIfPossible() throws Exception {

        Book book=new Book.Builder().build();

        boolean isUpdateExpected=true;

        boolean isUpdatedActual=bookService.updateIfPossible(book);


        int expectedInvocationTimes=1;

        assertEquals(isUpdateExpected,isUpdatedActual);

        verify(bookService,times(expectedInvocationTimes)).updateIfPossible(book);
    }

    @Test
    public void create() throws Exception {
        Book book=new Book();
        bookService.create(book);
        int expectedInvocationTimes=1;
        verify(bookService,times(expectedInvocationTimes)).create(book);
    }

    @Test
    public void getById() throws Exception {
        int bookId=1;
        Optional<Book> book=bookService.getById(bookId);
        int expectedInvocationTimes=1;
        assertNotNull(book.get());
        verify(bookService,times(expectedInvocationTimes)).getById(bookId);
    }


    @Test
    public void getAllBooks() throws Exception {
        List<Book> result=bookService.getAllBooks();
        assertNotNull(result);
    }

    @Test
    public void getBooksByTitle() throws Exception {
        List<Book> result=bookService.getAllBooks(20,0);
        assertNotNull(result);
    }
}