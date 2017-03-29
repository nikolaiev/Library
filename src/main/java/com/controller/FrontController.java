package com.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by vlad on 30.03.17.
 */
public class FrontController extends HttpServlet {
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {}

    @Override
    public void init(){

    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request , response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //response.sendRedirect("/");
        //request.getRequestDispatcher("/WEB-INF/view/errorPage.jsp").forward(request,response);
        request.getRequestDispatcher("index.jsp").forward(request,response);
    }
}
