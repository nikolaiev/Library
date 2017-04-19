package com.service;

import com.model.entity.book.Author;

import java.util.List;

/**
 * Created by vlad on 30.03.17.
 */
public interface AuthorService extends CrudService<Author> {
    List<Author> getAll();
}
