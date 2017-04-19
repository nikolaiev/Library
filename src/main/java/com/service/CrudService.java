package com.service;

import java.util.Optional;

/**
 * This interface declare CRUD service operations
 * Created by vlad on 19.04.17.
 */
public interface CrudService <T> {

    T create(T t);

    Optional<T> getById(int id);

    void update(T t);

    void deleteById(int id);
}
