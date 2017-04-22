package com.controller.responce;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 23.04.17.
 */
public class RedirectViewDispatcher implements ViewDispatcher {
    private final String PAGE;

    public RedirectViewDispatcher(String page) {
        PAGE = page;
    }

    @Override
    public void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(PAGE);
    }
}
