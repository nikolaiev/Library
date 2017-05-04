package com.dao.jdbc;

import com.dao.PublisherDao;
import com.model.entity.book.Publisher;
import org.junit.Ignore;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by vlad on 15.04.17.
 */
public class PublisherDaoImplTest extends  DaoTest{
    @Test
    public void insert() throws Exception {
        executeInReadCommitedVoidRollbackWrapper(transactionManager -> {
            PublisherDao publisherDao=transactionManager.getPublisherDao();
            String publisherTitle="Test publisher";

            Publisher publisher=new Publisher(publisherTitle);
            publisherDao.insert(publisher);

            assertNotEquals("Inserted obj id must not be 0",0,publisher.getId());
            assertEquals("Titles must be equals",publisherTitle,publisher.getTitle());
        });
    }

    @Test
    public void update() throws Exception {
        executeInReadCommitedVoidRollbackWrapper(transactionManager -> {
            PublisherDao publisherDao=transactionManager.getPublisherDao();
            String publisherTitle="Test publisher";
            String publisherNewTitle="Test publisher Updated";

            Publisher publisher=new Publisher(publisherTitle);
            //inserting object
            publisherDao.insert(publisher);

            //updating object
            publisher.setTitle(publisherNewTitle);
            publisherDao.update(publisher);

            Optional<Publisher> publisherOpt=publisherDao.getById(publisher.getId());

            if(publisherOpt.isPresent())
                assertEquals(publisherNewTitle,publisherOpt.get().getTitle());
            else
                assertTrue ("Unexpected situation occurred",false);
        });
    }

    @Test
    public void getAll() throws Exception {
        executeInNoTransactionalWrapper(transactionManager -> {
            List<Publisher> publisherList=transactionManager.getPublisherDao().getAll();
            assertNotNull("Publishers' list must not be null",publisherList);
        });
    }

    @Test
    public void getById() throws Exception {
        executeInNoTransactionalWrapper(transactionManager -> {
            List<Publisher> publisherList=transactionManager.getPublisherDao().getAll();
            assertNotNull("Publishers' list must not be null",publisherList);
        });
    }

    @Ignore
    @Test
    public void getPublisherByTitle() throws Exception {
    }

}