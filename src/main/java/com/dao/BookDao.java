package com.dao;

import com.model.entity.book.*;

import java.util.List;

/**
 * Created by vlad on 17.03.17.
 */
public interface BookDao extends GenericDao<Book>{

    int getCountAvailable(int bookId);
    void grantBook(int bookId);
    void returnBook(int bookId);

    List<Book> getBooksByTitleLimitOffset(String title,int limit,int offset);
    List<Book> getAllLimitOffset(int limit,int offset);
}
