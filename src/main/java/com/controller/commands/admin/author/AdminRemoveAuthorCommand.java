package com.controller.commands.admin.author;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.model.entity.book.Author;
import com.service.AuthorService;
import com.service.impl.AuthorServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 10.04.17.
 */
public class AdminRemoveAuthorCommand extends CommandWrapper implements Command {
    @Override
    protected String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Integer authorId=Integer.valueOf(request.getParameter("authorId"));
        AuthorService authorService= AuthorServiceImpl.getInstance();
        authorService.deleteById(authorId);
        return request.getContextPath() + "/admin/authors";
    }
}
