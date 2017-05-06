package com.controller.commands.admin.book;

import com.controller.commands.Command;
import com.controller.responce.Dispatcher;
import com.controller.responce.ForwardViewDispatcher;
import com.model.entity.book.*;
import com.service.AuthorService;
import com.service.PublisherService;
import com.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.controller.constants.JspPathsConst.ADMIN_ADD_BOOK_VIEW;


/**
 * Get command
 * Created by vlad on 03.04.17.
 */
public class AdminAddBookCommand implements Command {

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AuthorService authorService= ServiceFactory.getInstance().getAuthorService();
        PublisherService publisherService= ServiceFactory.getInstance().getPublisherService();

        List<Author> authors=authorService.getAll();
        List<Publisher> publishers=publisherService.getAll();


        request.setAttribute("authors",authors);
        request.setAttribute("publishers",publishers);
        request.setAttribute("languages", BookLanguage.values());
        request.setAttribute("genres", BookGenre.values());

        return new ForwardViewDispatcher(ADMIN_ADD_BOOK_VIEW);
    }
}
