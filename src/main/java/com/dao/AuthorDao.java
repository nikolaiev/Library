package com.dao;

import com.model.entity.book.Author;

import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 20.03.17.
 */
public interface AuthorDao extends  GenericDao<Author>{
    List<Author> getAuthorsByName(String name);
    Optional<Author> getAuthorByNameSoname(String name, String soname);
}
