package com.service;

import com.model.entity.book.Author;
import com.model.entity.book.Publisher;

import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 30.03.17.
 */
public interface AuthorService extends CrudService<Author> {
    List<Author> getAll();
    Optional<Author> getByNameSoname(String name,String soname);
}
