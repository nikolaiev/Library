package com.controller.responce;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 23.04.17.
 */
public class ErrorViewDispatcher implements ViewDispatcher {

    private final String POST_METHOD="POST";
    private final String ERROR_VIEW;

    public ErrorViewDispatcher(String error_view) {
        ERROR_VIEW = error_view;
    }

    @Override
    public void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(isPost(request)){
            /*add 500 status if POST request*/
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

        request.getRequestDispatcher(ERROR_VIEW).forward(request,response);
    }

    private boolean isPost(HttpServletRequest request){
        return request.getMethod().equals(POST_METHOD);
    }
}
