package com.service.impl;

import com.dao.PublisherDao;
import com.model.entity.book.Publisher;
import com.service.PublisherService;

import java.util.List;

/**
 * Created by vlad on 30.03.17.
 */
public class PublisherServiceImpl extends GenericService implements PublisherService {

    private static class InstanceHolder{
        private static PublisherServiceImpl INSTANCE=new PublisherServiceImpl();

    }

    public static PublisherService getInstance(){
        return InstanceHolder.INSTANCE;
    }

    @Override
    public List<Publisher> getAll() {
        return executeInNonTransactionalWrapper(transactionManager ->
        transactionManager.getPublisherDao().getAll());
    }

}
