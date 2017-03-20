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
    T create() throws SQLException;

    T insert(T obj) throws SQLException;

    void update(T obj) throws SQLException;

    List<T> getAll() throws SQLException;

    //Optional<T> getById(int key) throws SQLException;
    Optional<T> getById(int key) throws SQLException;

    void removeById(int key) throws SQLException;
}
