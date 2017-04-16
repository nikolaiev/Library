package com.controller.commands;

import com.dao.exception.DaoException;
import com.exception.ApplicationException;
import com.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 09.04.17.
 */
public abstract class CommandWrapper implements Command {

    protected abstract String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            String view = processExecute(request,response);
            return  view;
        }
        catch (ApplicationException e) {
            processApplicationException(e, request);
        }
        catch (Exception e) {
            processException(request, e);
        }

        if(request.getMethod().equals("GET")) {
            return request.getContextPath() + "/WEB-INF/view/errorPage.jsp";
        }
        else{
            //TODO rewrite SHIT CODE
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(request.getAttribute("error").toString());
            return "REDIRECTED";
        }
    }

    private void processException(HttpServletRequest request, Exception e) {
        //TODO implement
        request.setAttribute("error",e);
    }

    private void processApplicationException(ApplicationException e, HttpServletRequest request) {
        //TODO implement
        request.setAttribute("error",e);
    }
}
