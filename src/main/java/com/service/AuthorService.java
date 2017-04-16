package com.service;

import com.model.entity.book.Author;

import java.util.List;

/**
 * Created by vlad on 30.03.17.
 */
public interface AuthorService {
    List<Author> getAll();
    void createAuthor(Author author);
    void removeAuthor(Integer authorId);
}
