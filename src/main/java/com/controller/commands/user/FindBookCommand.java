package com.controller.commands.user;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.model.entity.book.*;
import com.service.AuthorService;
import com.service.BookService;
import com.service.PublisherService;
import com.service.impl.AuthorServiceImpl;
import com.service.impl.BookServiceImpl;
import com.service.impl.PublisherServiceImpl;

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

    @Override
    protected String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Optional<String> author_id= Optional.ofNullable(request.getParameter("author_id"));
        Optional<String> publisher_id= Optional.ofNullable(request.getParameter("publisher_id"));

        Integer authorId = author_id.map(Integer::parseInt).orElse(null);
        Integer publisherId = publisher_id.map(Integer::parseInt).orElse(null);

        String title = request.getParameter("title");
        BookGenre genre = BookGenre.getOrNull(request.getParameter("genre"));
        BookLanguage language = BookLanguage.getOrNull(request.getParameter("language"));

        int limit= getLimitFromRequest(request);
        int offset= getOffsetFromRequest(request,limit);

        /*services*/
        BookService bookService=BookServiceImpl.getInstance();
        AuthorService authorService= AuthorServiceImpl.getInstance();
        PublisherService publisherService= PublisherServiceImpl.getInstance();

        /*get data for jsp*/
        List<Book> books=bookService.getBooksByParams(title,authorId,genre,
                language,publisherId,limit,offset);

        List<Author> authors=authorService.getAll();
        List<Publisher> publishers=publisherService.getAll();

        int bookCount=bookService.getBooksCountByParams(title,authorId,genre,
                language,publisherId);

        int totalPages=(int)Math.ceil((double) bookCount/limit);

        request.setAttribute("books",books);
        request.setAttribute("authors",authors);
        request.setAttribute("publishers",publishers);
        request.setAttribute("languages",BookLanguage.values());
        request.setAttribute("genres",BookGenre.values());
        request.setAttribute("totalPages",totalPages);

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
