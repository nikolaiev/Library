package com.dao;

import com.model.entity.book.Publisher;

import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 20.03.17.
 */
public interface PublisherDao extends GenericDao<Publisher>{
    Optional<Publisher> getPublisherByTitle(String title);
}
