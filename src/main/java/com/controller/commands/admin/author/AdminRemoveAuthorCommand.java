package com.controller.commands.admin.author;

import com.controller.commands.Command;
import com.model.entity.book.Author;
import com.service.AuthorService;
import com.service.impl.AuthorServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 10.04.17.
 */
public class AdminRemoveAuthorCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Integer authorId=Integer.valueOf(request.getParameter("authorId"));
        AuthorService authorService= AuthorServiceImpl.getInstance();
        authorService.removeAuthor(authorId);
        return request.getContextPath() + "/admin/authors";
    }
}
