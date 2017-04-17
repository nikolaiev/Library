package com.dao.impl.jdbc;

import com.model.entity.book.BookGenre;
import org.junit.Before;
import org.junit.Test;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 * Created by vlad on 16.04.17.
 */
public class SelectQueryBuilderTest {
    BookDaoImpl bookDao;
    @Before
    public void init() throws SQLException {
        bookDao=(BookDaoImpl)BookDaoImpl.getInstance(JdbcPooledDataSource.getInstance().getConnection());
    }
    @Test
    public void getQuery() throws Exception {
        BookDaoImpl.SelectQueryBuilder queryBuilder=bookDao.new SelectQueryBuilder();
        PreparedStatement statement=queryBuilder.getQuery("book",null, BookGenre.ADVENTURES,null,1,10,20);
        assertNotNull(statement);
    }

    @Test
    public void addFilterParam() throws Exception {
        BookDaoImpl.SelectQueryBuilder queryBuilder=bookDao.new SelectQueryBuilder();
        queryBuilder.addFilterParam("","param1=?");
        queryBuilder.addFilterParam("abs","param2=?");
        queryBuilder.addFilterParam(123,"param3=?");
        PreparedStatement statement=queryBuilder.getPreparedStatement(10,20);
        assertNotNull(statement);
    }

    @Test
    public void getPreparedStatement() throws Exception {
        BookDaoImpl.SelectQueryBuilder queryBuilder=bookDao.new SelectQueryBuilder();
        PreparedStatement statement=queryBuilder.getPreparedStatement(10,20);
        assertNotNull(statement);
    }

}