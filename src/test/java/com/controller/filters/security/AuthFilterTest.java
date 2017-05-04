package com.controller.filters.security;

import com.model.entity.user.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static com.controller.constants.JspPathsConst.ERROR_VIEW;
import static com.controller.constants.UrlsConst.LOGIN;
import static org.mockito.Mockito.*;

/**
 * Test fro AuthFilter logic
 * Created by vlad on 27.04.17.
 */
@RunWith(MockitoJUnitRunner.class)
public class AuthFilterTest {
    AuthFilter authFilter =new AuthFilter();
    final String contextPath="";

    /*mocks*/
    HttpServletRequest request=mock(HttpServletRequest.class);
    HttpServletResponse response=mock(HttpServletResponse.class);
    FilterChain filterChain=mock(FilterChain.class);
    HttpSession session=mock(HttpSession.class);

    RequestDispatcher  requestDispatcher=mock(RequestDispatcher.class);
    ServletContext servletContext=mock(ServletContext.class);
    FilterConfig filterConfig=mock(FilterConfig.class);

    /*common when actions*/
    @Before
    public void init() throws ServletException {
        when(servletContext.getContextPath()).thenReturn(contextPath);
        when(filterConfig.getServletContext()).thenReturn(servletContext);

        authFilter.init(filterConfig);

        when(request.getSession()).thenReturn(session);
        when(request.getContextPath()).thenReturn(contextPath);
        when(request.getRequestDispatcher(ERROR_VIEW)).thenReturn(requestDispatcher);
    }

    /*Stateless tests*/

    @Test
    public void doFilterAdmin() throws Exception {

        int expectedTimesCall=1;

        when(session.getAttribute("userRole")).thenReturn(UserRole.ADMIN);
        when(request.getRequestURI()).thenReturn("/admin");
        when(request.getRequestDispatcher(ERROR_VIEW)).thenReturn(requestDispatcher);

        authFilter.doFilter(request,response,filterChain);

        verify(filterChain,times(expectedTimesCall)).doFilter(request,response);
    }

    @Test
    public void doFilterUser() throws Exception {

        int expectedTimesCall=1;

        when(session.getAttribute("userRole")).thenReturn(UserRole.USER);
        when(request.getRequestURI()).thenReturn("/user");

        authFilter.doFilter(request,response,filterChain);

        verify(filterChain,times(expectedTimesCall)).doFilter(request,response);
    }

    @Test
    public void doFilterAnonymous() throws Exception {

        int expectedTimesCall=1;

        when(session.getAttribute("userRole")).thenReturn(null);
        when(request.getRequestURI()).thenReturn("/user");

        authFilter.doFilter(request,response,filterChain);

        verify(response,times(expectedTimesCall)).sendRedirect(LOGIN);
    }

    @Test
    public void doFilterAdminRestricted() throws Exception {

        int expectedTimesCall=1;

        when(session.getAttribute("userRole")).thenReturn(UserRole.ADMIN);
        when(request.getRequestURI()).thenReturn("/user");

        authFilter.doFilter(request,response,filterChain);

        verify(requestDispatcher,times(expectedTimesCall)).forward(request,response);
    }

    @Test
    public void doFilterUserRestricted() throws Exception {

        int expectedTimesCall=1;

        when(session.getAttribute("userRole")).thenReturn(UserRole.USER);
        when(request.getRequestURI()).thenReturn("/admin");

        authFilter.doFilter(request,response,filterChain);

        verify(requestDispatcher,times(expectedTimesCall)).forward(request,response);
    }
}