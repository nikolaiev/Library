package com.controller.commands;

import com.dao.exception.DaoException;
import com.exception.ApplicationException;
import com.service.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 09.04.17.
 */
public abstract class CommandWrapper implements Command {

    protected abstract String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException;

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
        return request.getContextPath()+"/WEB-INF/view/errorPage.jsp";
    }

    private void processException(HttpServletRequest request, Exception e) {
        //TODO implement
    }

    private void processApplicationException(ApplicationException e, HttpServletRequest request) {
        //TODO implement
    }
}
