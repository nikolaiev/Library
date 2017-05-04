package com.dao.jdbc;

import com.dao.BookDao;
import com.dao.connection.jdbc.JdbcPooledDataSource;
import com.model.entity.book.Book;
import com.model.entity.book.BookLanguage;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.Assert.*;

/**
 * Created by vlad on 15.04.17.
 */
public class BookDaoImplTest extends DaoTest{

    private static Logger logger=Logger.getLogger(BookDaoImplTest.class);

    /*Stateless tests*/

    @Test
    public void update(){
        executeInReadCommitedVoidRollbackWrapper((daoManager -> {
            BookDao bookDao=daoManager.getBookDao();

            int limit=1;
            int offset=0;
            int expectedBookId=0;

            Book book=bookDao.getAllLimitOffset(limit,offset).get(0);
            logger.info("Instant is "+book.getInstant());
            expectedBookId=book.getId();
            bookDao.update(book);

            assertEquals("books was not updated ",expectedBookId,book.getId());

        }));

    }
    @Test
    public void insert(){
        executeInReadCommitedVoidRollbackWrapper((daoManager -> {
            BookDao bookDao=daoManager.getBookDao();

            int limit=1;
            int offset=0;

            Book book=bookDao.getAllLimitOffset(limit,offset).get(0);
            logger.info("Instant is "+book.getInstant());
            book.setId(0);
            Book newBook=bookDao.insert(book);
            assertNotNull("Returned book object must not be null",newBook);
            assertNotEquals("Id must not be 0",newBook.getId(),0);

        }));

    }
    @Test
    public void getAll() throws Exception {
        executeInNoTransactionalWrapper(transactionManager -> {
            BookDao bookDao=transactionManager.getBookDao();

            List<Book> books=bookDao.getAll();

            assertNotNull("Book list must not be null",books);

        });

    }

    @Test
    public void removeById() throws Exception {
        executeInReadCommitedVoidRollbackWrapper((daoManager -> {
            BookDao bookDao=daoManager.getBookDao();

            int limit=1;
            int offset=0;
            int bookId;

            Book book=bookDao.getAllLimitOffset(limit,offset).get(0);
            book.setId(0);
            book=bookDao.insert(book);

            bookId=book.getId();
            bookDao.removeById(book.getId());

            Optional<Book> bookDeleted=bookDao.getById(bookId);

            assertTrue("Book must have been removed",!bookDeleted.isPresent());
        }));
    }


    @Test
    public void getCountAvailable() throws Exception {
        executeInNoTransactionalWrapper(transactionManager -> {
            BookDao bookDao=transactionManager.getBookDao();
            int limit=1;
            int offset=0;
            Book book=bookDao.getAllLimitOffset(limit,offset).get(0);
            logger.info("Book id is "+book.getId());
            logger.info("Book count is "+book.getCount());

            Integer countAvailable=bookDao.getCountAvailable(book.getId());
            assertThat("count cant be less", countAvailable, greaterThanOrEqualTo(0));
        });
    }

    @Test
    public void grantBook() throws Exception {
        executeInRepeatableReadVoidRollbackWrapper((daoManager -> {
            BookDao bookDao=daoManager.getBookDao();

            int limit=1;
            int offset=0;
            int bookId = bookDao.getAllLimitOffset(limit,offset).get(0).getId();
            int countAvailable = bookDao.getCountAvailable(bookId);
            logger.info("countAvailable is "+countAvailable );

            bookDao.grantBook(bookId);
            int expectedCount=countAvailable-1;
            countAvailable=bookDao.getCountAvailable(bookId);
            assertEquals("New countAvail must be less on 1",expectedCount,countAvailable);


        }));
    }

    @Test
    public void returnBook() throws Exception {

        executeInRepeatableReadVoidRollbackWrapper((daoManager -> {
            BookDao bookDao=daoManager.getBookDao();

            int limit=1;
            int offset=0;
            int bookId = bookDao.getAllLimitOffset(limit,offset).get(0).getId();
            int count = bookDao.getCountAvailable(bookId);

            bookDao.returnBook(bookId);
            int expectedCount=count+1;
            count=bookDao.getCountAvailable(bookId);

            assertEquals("Return book function not working",expectedCount,count);

        }));
    }

    @Test
    public void getAllLimitOffset() throws Exception {
        executeInNoTransactionalWrapper(transactionManager -> {
            BookDao bookDao=transactionManager.getBookDao();

            List<Book> books=bookDao.getAllLimitOffset(2,0);
            assertNotNull(books);
        });
    }

    @Test
    public void getBooksByParams() throws Exception {
        executeInNoTransactionalWrapper(transactionManager -> {
            BookDao bookDao=transactionManager.getBookDao();
            int limit=100;
            int offset=0;
            BookLanguage language=BookLanguage.ENG;

            List<Book> books=bookDao.getBooksByParams("book",null,null,
                    language,null,limit,offset);

            assertNotNull(books);

            books.forEach(e->assertEquals(e.getLanguage(),language));
        });
    }

    @Test
    public void getBooksCountByParams() throws Exception {
        executeInNoTransactionalWrapper(transactionManager -> {
            BookDao bookDao=transactionManager.getBookDao();

            BookLanguage language=BookLanguage.ENG;
            int count=bookDao.getBooksCountByParams("book",null,null,
                    language,null);
            logger.info("count is "+count);
            assertThat("count must be greater than 0",count,greaterThanOrEqualTo(0));
        });



    }

    @Test
    public void updateIfPossible() throws Exception {
        executeInReadCommitedVoidRollbackWrapper((daoManager -> {
            BookDao bookDao=daoManager.getBookDao();

            int limit=1;
            int offset=0;
            int bookId;
            String newTitle="Test title";

            Book book = bookDao.getAllLimitOffset(limit,offset).get(0);

            book.setTitle(newTitle);
            bookId=book.getId();

            boolean isUpdated=bookDao.updateIfPossible(book);

            book=bookDao.getById(bookId).get();

            if(isUpdated){
                assertEquals(book.getTitle(),newTitle );
            }
        }));
    }

}