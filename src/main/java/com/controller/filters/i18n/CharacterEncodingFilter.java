package com.controller.filters.i18n;

import org.apache.log4j.Logger;

import javax.servlet.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by vlad on 09.04.17.
 */
public class CharacterEncodingFilter implements Filter {
    private static final String ENCODING = "utf-8";
    private static final Logger logger = Logger.getLogger(CharacterEncodingFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        try {
            servletRequest.setCharacterEncoding(ENCODING);
            servletResponse.setCharacterEncoding(ENCODING);

        } catch (UnsupportedEncodingException e) {
            logger.error(e);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
