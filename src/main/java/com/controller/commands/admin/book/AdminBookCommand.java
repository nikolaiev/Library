package com.controller.commands.admin.book;

import com.controller.commands.Command;
import com.controller.responce.Dispatcher;
import com.controller.responce.ForwardViewDispatcher;
import com.model.entity.book.Author;
import com.model.entity.book.BookGenre;
import com.model.entity.book.BookLanguage;
import com.model.entity.book.Publisher;
import com.service.AuthorService;
import com.service.PublisherService;
import com.service.impl.AuthorServiceImpl;
import com.service.impl.PublisherServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by vlad on 10.04.17.
 */
public class AdminBookCommand implements Command {
    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
         /*services*/
        AuthorService authorService= AuthorServiceImpl.getInstance();
        PublisherService publisherService= PublisherServiceImpl.getInstance();

        /*get data for jsp*/
        List<Author> authors=authorService.getAll();
        List<Publisher> publishers=publisherService.getAll();

        request.setAttribute("authors",authors);
        request.setAttribute("publishers",publishers);
        request.setAttribute("languages", BookLanguage.values());
        request.setAttribute("genres", BookGenre.values());

        String view ="/WEB-INF/view/admin/bookPage.jsp";
        return new ForwardViewDispatcher(view);
    }
}
