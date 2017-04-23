package com.controller.commands.admin.book;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.responce.Dispatcher;
import com.controller.responce.RedirectDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 10.04.17.
 */
public class AdminUpdateBookCommand extends CommandWrapper implements Command {
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO update book
        return new RedirectDispatcher(request.getContextPath()+"/admin/books");
    }
}
