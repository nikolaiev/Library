package com.service.impl;

import com.dao.BookDao;
import com.dao.GenericDao;
import com.dao.TransactionManager;
import com.dao.TransactionManagerFactory;
import com.dao.impl.jdbc.TransactionManagerFactoryImpl;
import com.model.entity.book.Book;
import com.service.BookService;

import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 30.03.17.
 */
public class BookServiceImpl extends GenericService implements BookService {

    private static class InstanceHolder{
        private static BookServiceImpl INSTANCE=new BookServiceImpl();

    }

    public static BookService getInstance(){
        return InstanceHolder.INSTANCE;
    }

    private BookServiceImpl(){}

    @Override
    public List<Book> getAllBooks() {

        return executeInNonTransactionalWrapper((transactionManager)->
            transactionManager.getBookDao().getAll()
        );

    }

    @Override
    public List<Book> getAllBooks(int limit, int offset) {
        return  executeInNonTransactionalWrapper((transactionManager ->
        transactionManager.getBookDao().getAllLimitOffset(limit,offset)));
    }

    @Override
    public List<Book> getBooksByTitle(String title,int limit,int offset) {
        return executeInNonTransactionalWrapper((transactionManager)->
            transactionManager.getBookDao().getBooksByTitleLimitOffset(title,limit,offset)
        );
    }

}
