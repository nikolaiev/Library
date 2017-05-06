package com.dao.jdbc;

import com.dao.AuthorDao;
import com.model.entity.book.Author;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by vlad on 15.04.17.
 */
public class AuthorDaoImplTest  extends DaoTest{


    Logger logger= Logger.getLogger(AuthorDaoImplTest.class);
    @Test
    public void getAll() throws Exception {
        executeInNoTransactionalWrapper(transactionManager -> {
            List<Author> authorList=transactionManager.getAuthorDao().getAll();
            assertNotNull("Authors list must not be null",authorList);
        });
    }

    @Test
    public void getById() throws Exception {
        executeInNoTransactionalWrapper(transactionManager -> {
            AuthorDao authorDao=transactionManager.getAuthorDao();

            List<Author> authorList=authorDao.getAll();
            //if db is not empty
            Author author=authorList.get(0);
            int authorId=author.getId();
            Optional<Author> authorCopy=authorDao.getById(authorId);

            if(authorCopy.isPresent())
                assertEquals(authorCopy.get(),author);
            else
                assertFalse("Objects must be equals",true);
        });
    }

    @Test
    public void getAuthorByName() throws Exception {
    }

    @Test
    public void insert() throws Exception {
        executeInReadCommitedVoidRollbackWrapper(transactionManager -> {
            AuthorDao authorDao=transactionManager.getAuthorDao();
            String testName="asdklfjadgslf";
            String testSoname="asdklfjadgslf";
            Author author=new Author(testName,testSoname);

            author.setName(testName);

            author= authorDao.insert(author);

            assertNotEquals("inserted object' id must not be 0 ",0,author.getId());

            Optional<Author> authorOpt=authorDao.getById(author.getId());

            if(authorOpt.isPresent()) {
                assertEquals("Author name was not updated", authorOpt.get().getName(), testName);
                assertEquals("Author soname was not updated", authorOpt.get().getSoname(), testSoname);
            }

            else
                assertFalse("Objects was not inserted",true);
        });
    }

    @Test
    public void update() throws Exception {
        executeInReadCommitedVoidRollbackWrapper(transactionManager -> {
            AuthorDao authorDao=transactionManager.getAuthorDao();

            List<Author> authorList=authorDao.getAll();

            Author author=authorList.get(0);

            String testName="asdklfjadgslf";

            author.setName(testName);

            authorDao.update(author);

            Optional<Author> authorOpt=authorDao.getById(author.getId());

            if(authorOpt.isPresent())
                assertEquals("Object was not updated",authorOpt.get().getName(),testName);
            else
                assertFalse("Unexpected error occurred",true);
        });
    }

    @Test
    public void getAuthorByNameSoname() throws Exception {
        executeInReadCommitedVoidRollbackWrapper(transactionManager -> {
            AuthorDao authorDao=transactionManager.getAuthorDao();
            String testName="asdklfjadgslf";
            String testSoname="asdklfjadgslf";

            Author author=new Author(testName,testSoname);

            authorDao.insert(author);

            Optional<Author> authorOpt=authorDao.getAuthorByNameSoname(testName,testSoname);

            assertTrue("Author was not found",authorOpt.isPresent());
        });

    }

    @Test
    public void getAuthorByNameSonameNull() throws Exception {
        executeInReadCommitedVoidRollbackWrapper(transactionManager -> {
            AuthorDao authorDao=transactionManager.getAuthorDao();
            String testName="asdklfjadgslf";
            String testSoname="asdklfjadgslf";

            Author author=new Author(testName,testSoname);

            authorDao.insert(author);

            Optional<Author> authorOpt=authorDao.getAuthorByNameSoname(testName+"asd",testSoname+"asd");

            assertFalse("Author was found",authorOpt.isPresent());
        });

    }

}