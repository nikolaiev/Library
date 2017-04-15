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
    List<Book> getBooksByAuthorLimitOffset(int authorId,int limit,int offset);
    List<Book> getBooksByGenreLimitOffset(BookGenre genre,int limit,int offset);
    List<Book> getBooksByPublisherLimitOffset(int publisherId,int limit,int offset);
    List<Book> getBooksByLanguageLimitOffset(BookLanguage language,int limit,int offset);
    List<Book> getAllLimitOffset(int limit,int offset);
}
