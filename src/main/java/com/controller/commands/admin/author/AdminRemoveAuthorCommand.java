package com.controller.commands.admin.author;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.responce.Dispatcher;
import com.controller.responce.RedirectDispatcher;
import com.service.AuthorService;
import com.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.controller.constants.UrlsConst.ADMIN_AUTHORS;

/**
 * Created by vlad on 10.04.17.
 */
public class AdminRemoveAuthorCommand extends CommandWrapper implements Command {
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer authorId=Integer.valueOf(request.getParameter("authorId"));
        AuthorService authorService= ServiceFactory.getInstance().getAuthorService();
        authorService.deleteById(authorId);
        return new RedirectDispatcher(ADMIN_AUTHORS);
    }
}
