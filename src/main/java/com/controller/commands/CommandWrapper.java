package com.controller.commands;

import com.controller.commands.helper.ParamExtractor;
import com.controller.commands.helper.RequestParamExtractor;
import com.dao.exception.DaoException;
import com.exception.ApplicationException;
import com.service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by vlad on 09.04.17.
 */
public abstract class CommandWrapper implements Command {

    protected ParamExtractor paramExtractor=new RequestParamExtractor();

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

    /**
     * Returns offset for book search
     * @param request requestObject
     * @param limit limit for book search
     * @return offset for book search
     */
    protected int getOffsetFromRequest(HttpServletRequest request,String offsetParamName,int limit) {
        return Optional.ofNullable(paramExtractor.getIntParamOrNull(request,"page"))
                .map(page->(page-1)*limit)
                .orElse(0);
    }

    /**
     * Returns limit for book search
     * @param request requestObject
     * @return limit for book search
     */
    protected int getLimitFromRequest(HttpServletRequest request,String limitParamName,int DEFAULT_LIMIT_VALUE) {
        return Optional.ofNullable(paramExtractor.getIntParamOrNull(request,limitParamName))
                .orElse(DEFAULT_LIMIT_VALUE);
    }

    private void processException(HttpServletRequest request, Exception e) {
        //TODO implement
        request.setAttribute("error",e);
    }

    private void processApplicationException(ApplicationException e, HttpServletRequest request) {
        //TODO implement
        request.setAttribute("error",e.getLogMessage()+e.getAdditionalMessage()+e.getMessageKey());
    }


}
