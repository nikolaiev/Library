package com.controller.commands;

import com.controller.responce.Dispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 30.03.17.
 */
@FunctionalInterface
public interface Command {
    Dispatcher execute(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
