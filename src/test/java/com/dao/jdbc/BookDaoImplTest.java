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
    public void getAll() throws Exception {
        Connection connection= JdbcPooledDataSource.getInstance().getConnection();
        BookDao bookDao= DaoFactoryImpl.getInstance().getBookDao(connection);

        List<Book> books=bookDao.getAll();

        assertNotNull(books);
    }

    @Test
    public void removeById() throws Exception {
        executeInReadCommitedVoidRollbackWrapper((daoManager -> {
            BookDao bookDao=daoManager.getBookDao();

            int limit=1;
            int offset=0;
            int bookId;

            bookId=bookDao.getAllLimitOffset(limit,offset).get(0).getId();

            bookDao.removeById(bookId);

            Optional<Book> bookOpt=bookDao.getById(bookId);

            assertTrue(!bookOpt.isPresent());

        }));
    }


    @Test
    public void getCountAvailable() throws Exception {
        Connection connection= JdbcPooledDataSource.getInstance().getConnection();
        BookDao bookDao= DaoFactoryImpl.getInstance().getBookDao(connection);

        Integer index=1;
        Book book=bookDao.getAllLimitOffset(1,0).get(0);
        logger.info("Book id is "+book.getId());
        logger.info("Book count is "+book.getCount());
        Integer countAvailable=bookDao.getCountAvailable(book.getId());
        assertThat("count cant be less", index, greaterThanOrEqualTo(countAvailable));
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
        Connection connection=JdbcPooledDataSource.getInstance().getConnection();
        BookDao bookDao= DaoFactoryImpl.getInstance().getBookDao(connection);

        List<Book> books=bookDao.getAllLimitOffset(2,0);
        assertNotNull(books);
    }

    @Test
    public void getBooksByParams() throws Exception {
        Connection connection=JdbcPooledDataSource.getInstance().getConnection();
        BookDao bookDao= DaoFactoryImpl.getInstance().getBookDao(connection);

        int limit=100;
        int offset=0;
        BookLanguage language=BookLanguage.ENG;

        List<Book> books=bookDao.getBooksByParams("book",null,null,
                language,null,limit,offset);

        assertNotNull(books);

        books.forEach(e->assertEquals(e.getLanguage(),language));

    }

    @Test
    public void getBooksCountByParams() throws Exception {
        Connection connection=JdbcPooledDataSource.getInstance().getConnection();
        BookDao bookDao= DaoFactoryImpl.getInstance().getBookDao(connection);

        BookLanguage language=BookLanguage.ENG;
        int minCount=0;
        int count=bookDao.getBooksCountByParams("book",null,null,
                language,null);
        logger.info("count is "+count);
        assertThat("count must be greater than 0",count,greaterThanOrEqualTo(0));

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