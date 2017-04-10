package com.service;

import com.model.entity.book.Book;

import java.util.List;

/**
 * Created by vlad on 30.03.17.
 */
public interface BookService {
    List<Book> getAllBooks();
    List<Book> getBooks(int limit,int offset);
    List<Book> getBooksByTitle(String title);
    List<Book> getBooksByAuthor(String author);
    List<Book> getBooksByPublisher(String publisher);
}
