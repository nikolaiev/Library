package com.service.impl;

import com.service.AuthorService;

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
}
