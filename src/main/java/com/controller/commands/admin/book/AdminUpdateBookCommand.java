package com.controller.commands.admin.book;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.exception.ControllerException;
import com.controller.responce.Dispatcher;
import com.controller.responce.ForwardViewDispatcher;
import com.model.entity.book.*;
import com.service.AuthorService;
import com.service.BookService;
import com.service.PublisherService;
import com.service.impl.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.controller.constants.JspPathsConst.ADMIN_EDIT_BOOK_VIEW;

/**
 * Created by vlad on 23.04.17.
 */
public class AdminUpdateBookCommand extends CommandWrapper implements Command {
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
         //throws exception if no such param

        int bookId=getMappedIntParamFromRequest(request);

        BookService service= ServiceFactory.getInstance().getBookService();
        AuthorService authorService= ServiceFactory.getInstance().getAuthorService();
        PublisherService publisherService= ServiceFactory.getInstance().getPublisherService();

        Optional<Book> book=service.getById(bookId);
        List<Author> authors=authorService.getAll();
        List<Publisher> publishers=publisherService.getAll();


        request.setAttribute("authors",authors);
        request.setAttribute("publishers",publishers);
        request.setAttribute("languages", BookLanguage.values());
        request.setAttribute("genres", BookGenre.values());

        request.setAttribute("book",book.orElseThrow(()->
            new ControllerException().addMessageKey("Unexpected error occurred")
        ));
        return new ForwardViewDispatcher(ADMIN_EDIT_BOOK_VIEW);
    }
}
