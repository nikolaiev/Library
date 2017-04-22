package com.controller.responce;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Describes view dispatching management
 *
 * Created by vlad on 21.04.17.
 */
public interface ViewDispatcher {
    void dispatch(HttpServletRequest request, HttpServletResponse response);
}
