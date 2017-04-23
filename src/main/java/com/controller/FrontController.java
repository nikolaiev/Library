package com.controller;

import com.controller.commands.Command;
import com.controller.responce.Dispatcher;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 30.03.17.
 */
public class FrontController extends HttpServlet {

    private static final Logger logger=Logger.getLogger(FrontController.class);
    private static CommandHolder commandHolder;

    /**
     * public constructor
     */
    public FrontController() {}

    @Override
    public void init(){
        commandHolder=new CommandHolder(getServletContext().getContextPath());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request , response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url=request.getMethod()+":"+request.getRequestURI();

        logger.info("url requested "+url);

        Command command=commandHolder.getCommand(url);

        Dispatcher dispatcher=command.execute(request,response);

        dispatcher.dispatch(request,response);
    }
}
