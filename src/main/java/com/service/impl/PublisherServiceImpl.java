package com.service.impl;

import com.dao.PublisherDao;
import com.model.entity.book.Publisher;
import com.service.PublisherService;

import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 30.03.17.
 */
public class PublisherServiceImpl extends GenericService implements PublisherService {

    protected PublisherServiceImpl() {
    }

    @Override
    public List<Publisher> getAll() {
        return executeInNonTransactionalWrapper(transactionManager ->
        transactionManager.getPublisherDao().getAll());
    }

    @Override
    public Optional<Publisher> getByTitle(String title) {
        return executeInNonTransactionalWrapper(transactionManager ->
            transactionManager.getPublisherDao().getPublisherByTitle(title)
        );
    }

    @Override
    public Publisher create(Publisher publisher) {
        return executeInNonTransactionalWrapper(transactionManager->
            transactionManager.getPublisherDao().insert(publisher)
        );
    }

    @Override
    public Optional<Publisher> getById(int id) {
        throw new UnsupportedOperationException();
    }


    @Override
    public void update(Publisher publisher) {
        executeInTransactionalVoidWrapper(transactionManager->
            transactionManager.getPublisherDao().update(publisher)
        );

    }

    @Override
    public void deleteById(int id) {
        executeInNonTransactionalVoidWrapper(transactionManager ->
            transactionManager.getPublisherDao().removeById(id)
        );
    }
}
