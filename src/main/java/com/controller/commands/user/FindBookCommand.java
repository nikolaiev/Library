package com.controller.commands.user;

import com.controller.commands.Command;
import com.model.entity.book.Book;
import com.service.BookService;
import com.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

/**
 * Created by vlad on 03.04.17.
 */
public class FindBookCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Optional<String> title = Optional.ofNullable(request.getParameter("title"));
        Optional<String> author = Optional.ofNullable(request.getParameter("author"));
        Optional<String> publisher= Optional.ofNullable(request.getParameter("publisher"));

        int limit= getLimitFromRequest(request);
        int offset= getOffsetFromRequest(request,limit);

        BookService bookService=BookServiceImpl.getInstance();
        //TODO rewrite to more specific search;
        List<Book> books= bookService.getBooks(limit,offset);


        request.setAttribute("books",books);
        return "WEB-INF/view/user/booksPage.jsp";
    }

    private int getOffsetFromRequest(HttpServletRequest request,int limit) {
        String res=request.getParameter("page");
        return res==null?0:(Integer.parseInt(res)-1)*limit;
    }

    private int getLimitFromRequest(HttpServletRequest request) {
        String res=request.getParameter("limit");
        return res==null?20:Integer.parseInt(res);
    }
}
