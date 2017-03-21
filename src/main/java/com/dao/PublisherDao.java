package com.dao;

import com.model.entity.book.Publisher;

import java.util.Optional;

/**
 * Created by vlad on 20.03.17.
 */
public interface PublisherDao {
    Optional<Publisher> getPublisherByTitle(String title);
}
