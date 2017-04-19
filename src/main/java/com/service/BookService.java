package com.service;

import com.model.entity.book.Book;
import com.model.entity.book.BookGenre;
import com.model.entity.book.BookLanguage;

import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 30.03.17.
 */
public interface BookService  extends CrudService<Book>{
    List<Book> getAllBooks();

    List<Book> getAllBooks(int limit,int offset);

    List<Book> getBooksByParams(String title, Integer authorId,
                                BookGenre genre, BookLanguage language,
                                Integer publisherId,
                                int limit, int offset);
    int getBooksCountByParams(String title, Integer authorId,
                              BookGenre genre, BookLanguage language,
                              Integer publisherId);
}
