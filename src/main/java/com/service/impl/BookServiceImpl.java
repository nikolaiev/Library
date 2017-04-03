package com.service.impl;

import com.dao.BookDao;
import com.dao.GenericDao;
import com.dao.TransactionManager;
import com.dao.TransactionManagerFactory;
import com.dao.impl.jdbc.TransactionManagerFactoryImpl;
import com.model.entity.book.Book;
import com.service.BookService;

import java.util.List;

/**
 * Created by vlad on 30.03.17.
 */
public class BookServiceImpl extends GenericService implements BookService {
    @Override
    public List<Book> getAllBooks() {

        return executeInNonTransactionalWrapper((transactionManager)->
            transactionManager.getBookDao().getAll()
        );

    }

    @Override
    public List<Book> getBooksByTitle(String title) {
        return executeInNonTransactionalWrapper((transactionManager)->
            transactionManager.getBookDao().getBooksByTitle(title)
        );
    }

    @Override
    public List<Book> getBooksByAuthor(String author) {
        return null;
    }

    @Override
    public List<Book> getBooksByPublisher(String publisher) {
        return null;
    }

}
