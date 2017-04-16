package com.service.impl;

import com.dao.AuthorDao;
import com.dao.TransactionManager;
import com.dao.impl.jdbc.AuthorDaoImpl;
import com.model.entity.book.Author;
import com.service.AuthorService;

import java.util.List;

/**
 * Created by vlad on 30.03.17.
 */
public class AuthorServiceImpl extends GenericService implements AuthorService {
    private static class InstanceHolder{

        private static AuthorServiceImpl INSTANCE=new AuthorServiceImpl();
    }

    public static AuthorService getInstance(){
        return InstanceHolder.INSTANCE;
    }

    @Override
    public List<Author> getAll() {
        return executeInNonTransactionalWrapper(transactionManager ->
        transactionManager.getAuthorDao().getAll());
    }

    @Override
    public void createAuthor(Author author) {
        //TODO implement
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeAuthor(Integer authorId) {
        //TODO implement
        throw new UnsupportedOperationException();
    }
}
