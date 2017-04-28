package com.controller.filters.i18n;

import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vlad on 09.04.17.
 */
public class LocalizationFilter implements Filter {
    private static Logger logger=Logger.getLogger(LocalizationFilter.class);

    //TODO place constant into configClass
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        ProgramLocale locale=getLocaleOrDefaultFromRequest(servletRequest);
        logger.info("Requested locale "+locale);
        HttpServletRequest request=(HttpServletRequest)servletRequest;
        HttpSession session=request.getSession();
        session.setAttribute("locale", locale.getLocale());

        filterChain.doFilter(servletRequest,servletResponse);
    }

    /**
     * Return request locale or default locale value
     * @param servletRequest request object
     * @return locale from request or locale default value
     */
    private ProgramLocale getLocaleOrDefaultFromRequest(ServletRequest servletRequest) {
        String localeString = servletRequest.getParameter("locale");
        return ProgramLocale.getOrDefault(localeString);
    }

    @Override
    public void destroy() {}
}
