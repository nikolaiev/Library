package com.controller.commands;

import com.controller.commands.helper.ParamExtractor;
import com.controller.commands.helper.RequestParamExtractor;
import com.dao.exception.DaoException;
import com.exception.ApplicationException;
import com.service.exception.ServiceException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * Created by vlad on 09.04.17.
 */
public abstract class CommandWrapper implements Command {

    private final static Logger logger=Logger.getLogger(CommandWrapper.class);

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
            //TODO think about page redirection
            return request.getContextPath() + "/WEB-INF/view/errorPage.jsp";
        }
        else{//POST request
            //TODO shit code rewrite!!
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

            String message=Optional.ofNullable(request.getAttribute("error_additional_message"))
                    .map(Object::toString).orElse("POST request error");

            response.getWriter().write(message);
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
    protected int getLimitFromRequest(HttpServletRequest request,String limitParamName,int defaultLimitValue) {
        return Optional.ofNullable(paramExtractor.getIntParamOrNull(request,limitParamName))
                .orElse(defaultLimitValue);
    }

    protected String escapeUrlCharacters(String message) {
        return message.replace(" ","%20");
    }

    private void processException(HttpServletRequest request, Exception e) {
        logger.error(e.getMessage());
        request.setAttribute("error_message", "Unknown error occurred!");
    }

    private void processApplicationException(ApplicationException e, HttpServletRequest request) {
        logger.error(e.toString());
        request.setAttribute("error_message", e.getMessageKey());
        request.setAttribute("error_additional_message", e.getAdditionalMessage());
    }


}
