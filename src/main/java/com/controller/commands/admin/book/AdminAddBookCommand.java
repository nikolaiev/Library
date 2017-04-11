package com.controller.commands.admin.book;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 03.04.17.
 */
public class AdminAddBookCommand extends CommandWrapper implements Command {
    @Override
    protected String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO add book to bd
        //return request.getContextPath()+"/WEB-INF/view/admin/bookPage.jsp";
        return "/admin/books";
    }
}
