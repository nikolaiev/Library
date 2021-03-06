package com.service.impl;

import com.model.entity.book.Book;
import com.model.entity.book.BookGenre;
import com.model.entity.book.BookLanguage;
import com.service.BookService;

import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 30.03.17.
 */
public class BookServiceImpl extends GenericService implements BookService {
    protected BookServiceImpl() {
    }

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
    public List<Book> getBooksByParams(String title, Integer authorId, BookGenre genre, BookLanguage language, Integer publisherId,
                                       int limit, int offset) {
        return executeInNonTransactionalWrapper((transactionManager)->
                transactionManager.getBookDao()
                        .getBooksByParams(title,authorId,genre,language,publisherId,limit,offset)
        );
    }

    @Override
    public int getBooksCountByParams(String title, Integer authorId, BookGenre genre, BookLanguage language, Integer publisherId) {
        return executeInNonTransactionalWrapper(transactionManager ->
        transactionManager.getBookDao()
                        .getBooksCountByParams(title,authorId,genre,language,publisherId)
        );
    }

    @Override
    public boolean updateIfPossible(Book book) {
        return executeInRepeatableReadWrapper(transactionManager -> transactionManager.getBookDao().updateIfPossible(book));
    }

    @Override
    public Book create(Book book) {
        return executeInNonTransactionalWrapper(transactionManager ->
            transactionManager.getBookDao().insert(book)
        );
    }

    @Override
    public Optional<Book> getById(int id) {
        return executeInNonTransactionalWrapper(transactionManager -> transactionManager.getBookDao().getById(id));
    }


    @Override
    public void update(Book book) {
        throw new UnsupportedOperationException("Update book is not supported. Use updateIfPossible(..) instead.");
    }

    @Override
    public void deleteById(int id) {
        throw new UnsupportedOperationException("BookService deleteById");
    }



}
