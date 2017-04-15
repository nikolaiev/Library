package com.service;

import com.model.entity.book.Book;

import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 30.03.17.
 */
public interface BookService {
    List<Book> getAllBooks();
    List<Book> getAllBooks(int limit,int offset);
    List<Book> getBooksByTitle(String title,int limit,int offset);
}
