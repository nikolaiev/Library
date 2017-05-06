package com.service.impl;

import com.model.entity.book.Author;
import com.service.AuthorService;

import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 30.03.17.
 */
public class AuthorServiceImpl extends GenericService implements AuthorService {
    AuthorServiceImpl() {
    }

    @Override
    public List<Author> getAll() {
        return executeInNonTransactionalWrapper(transactionManager ->
        transactionManager.getAuthorDao().getAll());
    }

    @Override
    public Optional<Author> getByNameSoname(String name, String soname) {
        return executeInNonTransactionalWrapper(transactionManager ->
            transactionManager.getAuthorDao().getAuthorByNameSoname(name,soname)
        );
    }

    @Override
    public Author create(Author author) {
        return  executeInTransactionalWrapper(transactionManager ->
                transactionManager.getAuthorDao().insert(author)
        );
    }

    @Override
    public Optional<Author> getById(int id) {
        throw new UnsupportedOperationException();
    }


    @Override
    public void update(Author author) {
        executeInTransactionalVoidWrapper(transactionManager ->
                transactionManager.getAuthorDao().update(author)
        );
    }


    @Override
    public void deleteById(int authorId) {
        executeInNonTransactionalVoidWrapper(transactionManager -> {
            transactionManager.getAuthorDao().removeById(authorId);
        });
    }
}
