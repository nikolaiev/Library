package com.controller.commands.admin.book;

import com.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 10.04.17.
 */
public class AdminBookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO add authors list
        //TODO add genre list
        //TODO add language list
        return request.getContextPath()+"/WEB-INF/view/admin/bookPage.jsp";
    }
}
