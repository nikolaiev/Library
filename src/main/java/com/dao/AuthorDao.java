package com.dao;

import com.model.entity.book.Author;

import java.util.Optional;

/**
 * Created by vlad on 20.03.17.
 */
public interface AuthorDao extends  GenericDao<Author>{
    Optional<Author> getAuthorByName(String name);
}
