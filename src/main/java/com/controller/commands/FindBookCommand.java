package com.controller.commands;

import com.controller.responce.Dispatcher;
import com.controller.responce.ForwardViewDispatcher;
import com.model.entity.book.*;
import com.service.AuthorService;
import com.service.BookService;
import com.service.PublisherService;
import com.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


/**
 * This class responsible fro GET command
 * Returns book catalog jsp
 * Created by vlad on 03.04.17.
 */
public class FindBookCommand extends CommandWrapper implements Command {
    private static int DEFAULT_LIMIT_VALUE=20;
    /*different for admin and user*/
    private final String BOOKS_VIEW;

    public FindBookCommand(String view) {
        this.BOOKS_VIEW = view;
    }

    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {

        Integer authorId = paramExtractor.getIntParamOrNull(request,"author_id");
        Integer publisherId = paramExtractor.getIntParamOrNull(request,"publisher_id");

        String title = paramExtractor.getStringParamOrNull(request, "table_title");
        BookGenre genre = paramExtractor.getEnumParamOrNull(request,"genre",BookGenre.class);
        BookLanguage language = paramExtractor.getEnumParamOrNull(request,"language",BookLanguage.class);

        /*paramExtractor used inside*/
        int limit= getLimitFromRequest(request,"limit",DEFAULT_LIMIT_VALUE);
        int offset= getOffsetFromRequest(request,"page",limit);

        /*services*/
        BookService bookService= ServiceFactory.getInstance().getBookService();
        AuthorService authorService= ServiceFactory.getInstance().getAuthorService();
        PublisherService publisherService= ServiceFactory.getInstance().getPublisherService();

        /*get data for jsp*/
        List<Book> books=bookService.getBooksByParams(title,authorId,genre,
                language,publisherId,limit,offset);

        List<Author> authors=authorService.getAll();
        List<Publisher> publishers=publisherService.getAll();

        int bookCount=bookService.getBooksCountByParams(title,authorId,genre,
                language,publisherId);

        /*pagination count*/
        int totalPages=(int)Math.ceil((double) bookCount/limit);

        request.setAttribute("books",books);
        request.setAttribute("authors",authors);
        request.setAttribute("publishers",publishers);
        request.setAttribute("languages",BookLanguage.values());
        request.setAttribute("genres",BookGenre.values());
        request.setAttribute("totalPages",totalPages);
        request.setAttribute("totalCount",bookCount);
        request.setAttribute("defLimit",DEFAULT_LIMIT_VALUE);

        return new ForwardViewDispatcher(BOOKS_VIEW);
    }
}
