package com.controller.responce;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 23.04.17.
 */
public class EmptyDispatcher implements Dispatcher{

    @Override
    public void dispatch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //must be empty
    }
}
