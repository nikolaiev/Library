package com.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.mockito.Mockito.*;
import static com.controller.constants.JspPathsConst.LOGIN_REG_VIEW;

/**
 * Created by vlad on 27.04.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class FrontControllerTest {

    HttpServletRequest request = mock(HttpServletRequest.class);
    HttpServletResponse response = mock(HttpServletResponse.class);

    @Before
    public void init() throws ServletException, IOException {
        when(request.getMethod()).thenReturn("GET:");
        when(request.getRequestURI()).thenReturn("/library/");
    }

    /*doGet doPost methods test*/
    @Test
    public void processRequest() throws Exception {
        new FrontController().doGet(request,response);
        verify(request, times(1)).getMethod();
        verify(request, times(1)).getRequestURI();
    }

}