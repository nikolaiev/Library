package com.service.impl;

import com.dao.jdbc.AuthorDaoImpl;
import com.model.entity.book.Author;
import com.service.AuthorService;

import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 30.03.17.
 */
public class AuthorServiceImpl extends GenericService implements AuthorService {
    protected AuthorServiceImpl() {
    }

    @Override
    public List<Author> getAll() {
        return executeInNonTransactionalWrapper(transactionManager ->
        transactionManager.getAuthorDao().getAll());
    }

    @Override
    public Author create(Author author) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Author> getById(int id) {
        throw new UnsupportedOperationException();
    }


    @Override
    public void update(Author author) {
        //TODO implement
        throw new UnsupportedOperationException();

    }


    @Override
    public void deleteById(int id) {
        //TODO implement
        throw new UnsupportedOperationException();

    }
}
