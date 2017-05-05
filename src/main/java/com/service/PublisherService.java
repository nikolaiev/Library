package com.service;

import com.model.entity.book.Publisher;

import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 30.03.17.
 */
public interface PublisherService extends CrudService<Publisher>{
    List<Publisher> getAll();
    Optional<Publisher> getByTitle(String title);
}
