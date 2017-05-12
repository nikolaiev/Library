package com.dao.jdbc;

import com.dao.PublisherDao;
import com.model.entity.book.Publisher;
import org.junit.Ignore;
import org.junit.Test;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by vlad on 15.04.17.
 */
public class PublisherDaoImplTest extends  DaoTest{
    @Test
    public void insertChangeId() throws Exception {
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
    public void insertChangeCount() throws Exception {
        executeInReadCommitedVoidRollbackWrapper(transactionManager -> {
            PublisherDao publisherDao=transactionManager.getPublisherDao();
            int beginCount=publisherDao.getAll().size();
            String publisherTitle="Test publisher";
            Publisher publisher=new Publisher(publisherTitle);

            publisherDao.insert(publisher);
            int endCount=publisherDao.getAll().size();

            assertEquals("Object was not inserted",beginCount+1,endCount);
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

    @Test
    public void getPublisherByTitle() throws Exception {
        executeInReadCommitedVoidRollbackWrapper(transactionManager -> {

            PublisherDao publisherDao = transactionManager.getPublisherDao();
            String publisherTitle = "Test publisher";

            Publisher publisher = new Publisher(publisherTitle);
            publisherDao.insert(publisher);

            Optional<Publisher> publisherOptional=transactionManager.getPublisherDao().getPublisherByTitle(publisherTitle);
            assertEquals(publisher.getTitle() ,publisherOptional.get().getTitle());
        });

    }

}