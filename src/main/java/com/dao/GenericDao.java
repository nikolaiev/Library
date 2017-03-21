package com.dao;

import javax.swing.text.html.Option;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 * Created by vlad on 17.03.17.
 */
public interface GenericDao <T>{
    //T create();

    T insert(T obj);

    void update(T obj);

    List<T> getAll();

    Optional<T> getById(int key);

    void removeById(int key);
}
