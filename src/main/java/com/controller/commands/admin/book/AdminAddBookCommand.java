package com.controller.commands.admin.book;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.responce.Dispatcher;
import com.controller.responce.ForwardViewDispatcher;
import com.controller.responce.RedirectDispatcher;
import com.model.entity.book.*;
import com.service.AuthorService;
import com.service.BookService;
import com.service.PublisherService;
import com.service.impl.AuthorServiceImpl;
import com.service.impl.BookServiceImpl;
import com.service.impl.PublisherServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.controller.constants.JspPathsConst.ADMIN_ADD_BOOK_VIEW;
import static com.controller.constants.JspPathsConst.ADMIN_BOOK_VIEW;
import static com.controller.constants.JspPathsConst.ADMIN_EDIT_BOOK_VIEW;
import static com.controller.constants.UrlsConst.ADMIN_BOOKS;


/**
 * Created by vlad on 03.04.17.
 */
public class AdminAddBookCommand implements Command {

    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AuthorService authorService= AuthorServiceImpl.getInstance();
        PublisherService publisherService= PublisherServiceImpl.getInstance();

        List<Author> authors=authorService.getAll();
        List<Publisher> publishers=publisherService.getAll();


        request.setAttribute("authors",authors);
        request.setAttribute("publishers",publishers);
        request.setAttribute("languages", BookLanguage.values());
        request.setAttribute("genres", BookGenre.values());

        return new ForwardViewDispatcher(ADMIN_ADD_BOOK_VIEW);
    }
}
