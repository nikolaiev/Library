package com.controller.validation;

import java.util.List;

/**
 * Created by vlad on 23.04.17.
 */
public interface Validator <T>{

    List<T> getAllErrors();
}
