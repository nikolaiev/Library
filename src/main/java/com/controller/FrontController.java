package com.controller;

import com.controller.commands.Command;
import com.controller.commands.CommandHolder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 30.03.17.
 */
public class FrontController extends HttpServlet {
    private static CommandHolder commandHolder;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FrontController() {}

    @Override
    public void init(){
        commandHolder=new CommandHolder();
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
        String url=request.getMethod()+":"+request.getRequestURI();
        Command command=commandHolder.getCommand(url);
        String view=command.execute(request,response);

        if(!view.equals("REDIRECTED"))
            request.getRequestDispatcher(view).forward(request,response);
    }
}
