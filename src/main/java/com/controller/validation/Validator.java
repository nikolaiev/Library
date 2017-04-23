package com.controller.validation;

import java.util.List;

/**
 * Created by vlad on 23.04.17.
 */
public interface Validator<T>{
    boolean isValid(T obj);

    List<String> getAllErrors();

    void addError(String error);
}
