package com.controller.commands.admin.author;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.responce.Dispatcher;
import com.controller.responce.RedirectDispatcher;
import com.model.entity.book.Author;
import com.service.AuthorService;
import com.service.impl.AuthorServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.controller.constants.UrlsConst.ADMIN_AUTHORS;

/**
 * Created by vlad on 03.04.17.
 */
public class AdminAddAuthorCommand extends CommandWrapper implements Command{
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name=request.getParameter("name");
        String soname=request.getParameter("soname");
        Author author=new Author(name,soname);
        AuthorService authorService= AuthorServiceImpl.getInstance();
        authorService.create(author);

        return new RedirectDispatcher(ADMIN_AUTHORS);
    }
}
