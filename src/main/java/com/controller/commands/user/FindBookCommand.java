package com.controller.commands.user;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.model.entity.book.Book;
import com.model.entity.book.BookGenre;
import com.service.BookService;
import com.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static java.util.Optional.of;
import static java.util.Optional.ofNullable;

/**
 * This class responsible fro GET command
 * Returns book catalog jsp
 * Created by vlad on 03.04.17.
 */
public class FindBookCommand extends CommandWrapper implements Command {
    //TODO make all filters work
    @Override
    protected String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //wrap into Optional to reduce code uglity!
        Optional<String> title = Optional.ofNullable(request.getParameter("title"));
        Optional<String> author = Optional.ofNullable(request.getParameter("author"));
        Optional<String> publisher= Optional.ofNullable(request.getParameter("publisher"));
        Optional<String> genre= Optional.ofNullable(request.getParameter("genre"));

        int limit= getLimitFromRequest(request);
        int offset= getOffsetFromRequest(request,limit);


        BookService bookService=BookServiceImpl.getInstance();
        //TODO rewrite to more specific search;

        List<Book> books= null;//bookService.getBooks(title,genre,author,publisher,limit,offset);


        request.setAttribute("books",books);
        return request.getContextPath()+"/WEB-INF/view/user/booksPage.jsp";
    }

    /**
     * Returns offset for book search
     * @param request requestObject
     * @param limit limit for book search
     * @return offset for book search
     */
    private int getOffsetFromRequest(HttpServletRequest request,int limit) {
        String res=request.getParameter("page");
        return res==null?0:(Integer.parseInt(res)-1)*limit;
    }

    /**
     * Returns limit for book search
     * @param request requestObject
     * @return limit for book search
     */
    private int getLimitFromRequest(HttpServletRequest request) {
        String res=request.getParameter("limit");
        return res==null?20:Integer.parseInt(res);
    }
}
