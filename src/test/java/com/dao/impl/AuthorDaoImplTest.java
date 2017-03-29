package com.dao.impl;

import com.dao.TransactionManager;
import com.dao.impl.jdbc.TransactionManagerFactoryImpl;
import com.model.entity.book.Author;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by vlad on 23.03.17.
 */
public class AuthorDaoImplTest {


    TransactionManager daoManager;
    Author author;
    @Before
    public void setUp() throws Exception {
        daoManager= TransactionManagerFactoryImpl.getInstance().createTransactionManager();
        author= ((Optional<Author>) daoManager.transaction((manager)->
           manager.getAuthorDao().getById(8)
        )).get();
    }

    @After
    public void tearDown() throws Exception {

    }

    /*@Test
    public void getAll() throws Exception {
        List<Author> authorList=(List<Author>)daoManager.transaction((manager)->
                manager.getAuthorDao().getAll()
        );

    }*/

    @Test
    public void getById() throws Exception {

        Author newAuthor=new Author("TestName","TestSoname");

        /*auto updates newAuthor*/
        daoManager.transaction((manager)->
                manager.getAuthorDao().insert(newAuthor)
        );

        Optional<Author> author2= (Optional<Author>)daoManager.transaction((manager)->
                manager.getAuthorDao().getById(newAuthor.getId())
        );

        assertEquals(author2.get(),newAuthor);

        daoManager.transaction((manager)->{
            manager.getAuthorDao().removeById(newAuthor.getId());
            return  null;
        });

    }

    @Test
    public void getAuthorByName() throws Exception {

    }

    @Test
    public void insert() throws Exception {
        Author newAuthor=new Author("TestName","TestSoname");
        Author author2=(Author) daoManager.transaction((manager)->
                manager.getAuthorDao().insert(newAuthor)
        );

        daoManager.transaction((manager)->{
           manager.getAuthorDao().removeById(newAuthor.getId());
           return null;
        });
    }

    @Test
    public void update() throws Exception {
        String name=author.getName();
        int id=author.getId();
        author.setName(name+"TOKEN");

        daoManager.transaction((manager)-> {
                    manager.getAuthorDao().update(author);
                    return null;
                }
        );

        Optional<Author> author2= (Optional<Author>)daoManager.transaction((manager)->
            manager.getAuthorDao().getById(id)
        );

        assertEquals(author2.get().getName(),author.getName());

        /*rolling back*/
        author.setName(name);
        daoManager.transaction((manager)-> {
                    manager.getAuthorDao().update(author);
                    return null;
                }
        );

    }

    @Test
    public void removeById_getAll() throws Exception {
        List<Author> authorList=(List<Author>)daoManager.transaction((manager)->
            manager.getAuthorDao().getAll()
        );

        int initialAuthorCount=authorList.size();

        Author newAuthor=new Author("TestName","TestSoname");

        /*auto updates newAuthor*/
        daoManager.transaction((manager)->
                manager.getAuthorDao().insert(newAuthor)
        );


        authorList=(List<Author>)daoManager.transaction((manager)->
                manager.getAuthorDao().getAll()
        );

        int newAuthorCount=authorList.size();
        assertEquals(newAuthorCount-1,initialAuthorCount);

        daoManager.transaction((manager)->{
            manager.getAuthorDao().removeById(newAuthor.getId());
            return  null;
        });

        authorList=(List<Author>)daoManager.transaction((manager)->
                manager.getAuthorDao().getAll()
        );

        int lastAuthorCount=authorList.size();


        assertEquals(lastAuthorCount,initialAuthorCount);
    }
}