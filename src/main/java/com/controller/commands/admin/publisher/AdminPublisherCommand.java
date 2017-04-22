package com.controller.commands.admin.publisher;

import com.controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 10.04.17.
 */
public class AdminPublisherCommand implements Command {
    //TODO implement
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return request.getContextPath()+"/WEB-INT/view/admin/publisherPage.jsp";
    }
}
