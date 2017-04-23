package com.controller.validation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad on 23.04.17.
 */
public abstract  class AbstractValidator <T> implements Validator<T>{

    List<String> errors;

    public AbstractValidator(){
        errors=new ArrayList<>();
    }

    @Override
    public List<String> getAllErrors() {
        return errors;
    }

    @Override
    public void addError(String error) {
        this.errors.add(error);
    }
}
