package com.controller.security;

import com.model.entity.user.UserRole;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by vlad on 09.04.17.
 */
public class AuthFilter implements Filter {
    private static String FORBIDDEN_URL_REQUESTED="FORBIDDEN URL REQUESTED";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest=(HttpServletRequest)servletRequest;
        HttpSession session=httpRequest.getSession();
        UserRole role=(UserRole) session.getAttribute("userRole");
        String uri = httpRequest.getRequestURI();

        if(!isAuthorizedForUri(role,uri)){
            //TODO переделать
            httpRequest.setAttribute("error",FORBIDDEN_URL_REQUESTED);
            httpRequest.getRequestDispatcher("/forbidden").forward(servletRequest,servletResponse);
            return;
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }

    private boolean isAuthorizedForUri(UserRole role, String uri){
        if(role==UserRole.ADMIN){
            return checkAdminUri(uri);
        }
        else if(role==UserRole.USER){
            return checkUserUri(uri);
        }

        return checkUnauthorizedUri(uri);
    }

    private boolean checkUnauthorizedUri(String uri) {
        return uri.startsWith("/login");
    }

    private boolean checkUserUri(String uri) {
        return uri.startsWith("/user/")
                ||uri.startsWith("/forbidden")
                ||uri.startsWith("/logout");
    }

    private boolean checkAdminUri(String uri) {
        return  uri.startsWith("/admin/")
                ||uri.startsWith("/forbidden")
                ||uri.startsWith("/logout");
    }

    @Override
    public void destroy() {

    }
}
