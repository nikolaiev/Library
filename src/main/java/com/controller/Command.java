package com.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.HttpRetryException;

/**
 * Created by vlad on 30.03.17.
 */
@FunctionalInterface
public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response) ;
}
