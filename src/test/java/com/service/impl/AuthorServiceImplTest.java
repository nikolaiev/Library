package com.service.impl;

import com.model.entity.book.Author;
import com.service.AuthorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by vlad on 24.04.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceImplTest {
    @Mock
    AuthorService authorServiceMock =mock(AuthorService.class);
    AuthorService authorService=new AuthorServiceImpl();

    @Before
    public void init(){
        when(authorServiceMock.getAll()).thenReturn(new ArrayList<Author>(){{
            add(new Author());
            add(new Author());
        }});

        when(authorServiceMock.getByNameSoname(anyString(),anyString()))
                .thenReturn(Optional.of(new Author()));

        when(authorServiceMock.create(anyObject())).thenReturn(new Author());
    }


    @Test
    public void getAll() throws Exception {
        List<Author> authorList= authorServiceMock.getAll();
        assertNotNull(authorList);
    }

    @Test
    public void create() throws Exception {
        //inserting
        Author author=authorServiceMock.create(new Author());
        assertNotNull(author);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getById() throws Exception {
        authorService.getById(1);
    }

    @Test
    public void update() throws Exception {
        authorServiceMock.update(new Author());
    }

    @Test
    public void deleteById() throws Exception {
        authorServiceMock.deleteById(10);
    }

}