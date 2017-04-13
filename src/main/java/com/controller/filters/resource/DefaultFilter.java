package com.controller.filters.resource;

import javax.servlet.*;
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
        defaultRequestDispatcher.forward(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {

    }
}
