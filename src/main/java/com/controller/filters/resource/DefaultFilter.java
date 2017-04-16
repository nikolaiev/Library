package com.controller.filters.resource;

import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by vlad on 12.04.17.
 */
public class DefaultFilter  implements Filter{
    private RequestDispatcher defaultRequestDispatcher;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        defaultRequestDispatcher=filterConfig.getServletContext()
                .getNamedDispatcher("default");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest) servletRequest;
            defaultRequestDispatcher.forward(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
