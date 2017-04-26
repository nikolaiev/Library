package com.controller.filters.security;

import com.model.entity.user.UserRole;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.controller.constants.JspPathsConst.ERROR_VIEW;
import static com.controller.constants.UrlsConst.LOGIN;

/**
 * Created by vlad on 09.04.17.
 */
public class AuthFilter implements Filter {
    private static final Logger logger=Logger.getLogger(AuthFilter.class);
    private String deployPath;

    private static String FORBIDDEN_URL_REQUESTED="FORBIDDEN URL REQUESTED";
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        deployPath=filterConfig.getServletContext().getContextPath();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest=(HttpServletRequest)servletRequest;

        HttpSession session=httpRequest.getSession();
        //TODO do not use session
        UserRole role=(UserRole) session.getAttribute("userRole");
        String uri = httpRequest.getRequestURI();

        logger.info("Requested URI is "+uri);

        if(!isAuthorizedForUri(role,uri)){
            if(role==null){
                HttpServletResponse httpResponse=(HttpServletResponse)servletResponse;
                httpResponse.sendRedirect(httpRequest.getContextPath()+LOGIN);
            }
            else {
                httpRequest.setAttribute("error",FORBIDDEN_URL_REQUESTED);
                httpRequest.getRequestDispatcher(ERROR_VIEW)
                        .forward(servletRequest, servletResponse);
            }
            return;
        }

        logger.info("Process view for uri "+uri);

        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        //does not need implementation
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

        return !uri.startsWith(deployPath+"/user/")&&
                !uri.startsWith(deployPath+"/admin/");
    }

    private boolean checkUserUri(String uri) {
        return !uri.startsWith(deployPath+"/admin/")
                ||uri.startsWith(deployPath+"/static/");
    }

    private boolean checkAdminUri(String uri) {
        return  !uri.startsWith(deployPath+"/user/");
    }

}
