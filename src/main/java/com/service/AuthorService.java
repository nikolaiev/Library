package com.service;

import com.model.entity.book.Author;

/**
 * Created by vlad on 30.03.17.
 */
public interface AuthorService {
    void createAuthor(Author author);
    void removeAuthor(Integer authorId);
}
