package com.controller.responce;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 23.04.17.
 */
public class RedirectDispatcher implements Dispatcher {
    private final String PAGE;

    public RedirectDispatcher(String page) {
        PAGE = page;
    }

    @Override
    public void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(PAGE);
    }
}
