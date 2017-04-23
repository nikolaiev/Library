package com.controller.responce;

import com.controller.validation.Validator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 23.04.17.
 */
public class ValidationErrorViewDispatcher implements Dispatcher {
    private final String POST_METHOD="POST";

    /*page after validation errors*/
    private final String VIEW;
    private Validator validator;

    public ValidationErrorViewDispatcher(String viewAfterValidationError, Validator validator) {

        VIEW = viewAfterValidationError;
        this.validator = validator;
    }

    @Override
    public void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if(isPost(request)){
            //add 500 status if POST request
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }

        request.setAttribute("validation_errors", validator.getAllErrors());
        request.getRequestDispatcher(VIEW).forward(request,response);
    }

    private boolean isPost(HttpServletRequest request){
        return request.getMethod().equals(POST_METHOD);
    }
}