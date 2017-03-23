package com.dao.impl;

import com.dao.DaoManager;
import com.dao.DaoManagerFactory;
import com.dao.impl.jdbc.DaoManagerFactoryImpl;
import com.model.entity.book.Author;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by vlad on 23.03.17.
 */
public class AuthorDaoImplTest {
    DaoManager daoManager;
    Author author;
    @Before
    public void setUp() throws Exception {
        daoManager=DaoManagerFactoryImpl.getInstance().getDaoManager();
        author=new Author("Vlad","Nikolaiev");
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getAll() throws Exception {
        Author author2=(Author) daoManager.transaction((manager)->
                manager.getAuthorDao().insert(author)
        );
        System.out.println(author2.getId());
        assert(author2.getId()!=0);
    }

    @Test
    public void getById() throws Exception {

    }

    @Test
    public void getAuthorByName() throws Exception {

    }

    @Test
    public void insert() throws Exception {

    }

    @Test
    public void update() throws Exception {

    }

    @Test
    public void removeById() throws Exception {

    }

}