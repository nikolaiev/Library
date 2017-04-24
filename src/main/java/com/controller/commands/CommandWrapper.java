package com.controller.commands;

import com.controller.commands.helper.ParamExtractor;
import com.controller.commands.helper.RequestParamExtractor;
import com.controller.responce.ErrorViewDispatcher;
import com.controller.responce.Dispatcher;
import com.exception.ApplicationException;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.controller.constants.JspPathsConst.ERROR_VIEW;

/**
 * Created by vlad on 09.04.17.
 */

public abstract class CommandWrapper implements Command {

    protected ParamExtractor paramExtractor=new RequestParamExtractor();

    private final static Dispatcher DEFAULT_ERROR_DISPATCHER =
            new ErrorViewDispatcher(ERROR_VIEW);

    private final static Logger logger=Logger.getLogger(CommandWrapper.class);


    protected abstract Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try{
            return  processExecute(request,response);
        }
        catch (ApplicationException e) {
            processApplicationException(e, request);
        }
        catch (Exception e) {
            processException(e,request);
        }

        return DEFAULT_ERROR_DISPATCHER;

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

    private void processException(Exception e, HttpServletRequest request ) {
        logger.error(e.getMessage());
        request.setAttribute("error_message", "Unknown error occurred! "+e.toString());
    }

    private void processApplicationException(ApplicationException e, HttpServletRequest request) {
        logger.error(e.toString());
        request.setAttribute("error_message", e.getMessageKey());
        request.setAttribute("error_additional_message", e.getAdditionalMessage());
    }
}
