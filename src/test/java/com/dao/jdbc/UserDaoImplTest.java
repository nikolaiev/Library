package com.dao.jdbc;

import com.dao.UserDao;
import com.model.entity.user.User;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Created by vlad on 15.04.17.
 */
public class UserDaoImplTest extends DaoTest{
    @Test
    public void insert() throws Exception {
        executeInReadCommitedVoidRollbackWrapper(transactionManager -> {
            UserDao userDao=transactionManager.getUserDao();
            User user=userDao.getAll().get(0);

            String testLogin="asdasdasd";
            user.setId(0);
            user.setLogin(testLogin);

            //inserting
            userDao.insert(user);
            int userId=user.getId();

            Optional<User> userOpt=userDao.getById(userId);

            if(userOpt.isPresent()){
                assertEquals(user,userOpt.get());
            }
            else
                assertTrue("Unexpected error occurred",false);

        });
    }

    @Test
    public void update() throws Exception {
        executeInReadCommitedVoidRollbackWrapper(transactionManager -> {
            UserDao userDao=transactionManager.getUserDao();
            User user=userDao.getAll().get(0);

            String testLogin="asdhaklsd";
            user.setLogin(testLogin);
            //updating
            userDao.update(user);
            int userId=user.getId();

            Optional<User> userOpt=userDao.getById(userId);

            if(userOpt.isPresent()){
                assertEquals(user,userOpt.get());
            }
            else
                assertTrue("Unexpected error occurred",false);

        });
    }

    @Test
    public void getAll() throws Exception {
        executeInNoTransactionalWrapper(transactionManager -> {
            UserDao userDao=transactionManager.getUserDao();
            List<User> userList=userDao.getAll();
            assertNotNull("Users' list must not be null",userList);
        });
    }

    @Test
    public void getById() throws Exception {
        executeInNoTransactionalWrapper(transactionManager -> {
            UserDao userDao=transactionManager.getUserDao();
            List<User> userList=userDao.getAll();
            User user=userList.get(0);
            int userId=user.getId();
            Optional<User> userCopy=userDao.getById(userId);

            if(userCopy.isPresent()){
                assertEquals("Object must be equals",user,userCopy.get());
            }
            else {
                assertTrue("Unexpected error occurred",false);
            }
        });
    }

    @Test
    public void getUserByLogin() throws Exception {
        executeInNoTransactionalWrapper(transactionManager -> {
            UserDao userDao=transactionManager.getUserDao();
            List<User> userList=userDao.getAll();
            User user=userList.get(0);

            String login=user.getLogin();
            Optional<User> userCopy=userDao.getUserByLogin(login);

            if(userCopy.isPresent()){
                assertEquals("Object must be equals",user,userCopy.get());
            }
            else {
                assertTrue("Unexpected error occurred",false);
            }
        });
    }

}