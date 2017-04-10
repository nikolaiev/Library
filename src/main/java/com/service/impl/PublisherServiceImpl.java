package com.service.impl;

import com.dao.PublisherDao;
import com.service.PublisherService;

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
}
