package com.dao;

import com.model.entity.book.Author;
import com.model.entity.book.Book;

import java.util.List;

/**
 * Created by vlad on 17.03.17.
 */
public interface BookDao extends GenericDao<Book>{
    int getCountAvailable();
    List<Book> getBooksByAuthor(Author author);

}
