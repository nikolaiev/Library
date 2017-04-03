package com.controller.commands.common;

import com.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by vlad on 03.04.17.
 */
public class GoInvalidUrlCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String url=request.getMethod()+":"+request.getRequestURI();
        request.setAttribute("url",url);
        request.setAttribute("testAttr","Hello testAttr!");
        return "WEB-INF/view/invalidUrlPage.jsp";
    }
}
