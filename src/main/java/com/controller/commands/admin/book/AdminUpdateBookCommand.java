package com.controller.commands.admin.book;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.commands.helper.ParamExtractor;
import com.controller.commands.helper.RequestParamExtractor;
import com.controller.responce.Dispatcher;
import com.controller.responce.ForwardViewDispatcher;
import com.controller.responce.RedirectDispatcher;
import com.model.entity.book.Book;
import com.service.BookService;
import com.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

import static com.controller.constants.JspPathsConst.ADMIN_SINGLE_BOOK_VIEW;

/**
 * Created by vlad on 23.04.17.
 */
public class AdminUpdateBookCommand extends CommandWrapper implements Command {
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        ParamExtractor paramExtractor=new RequestParamExtractor();

        //throws exception if no such param
        int bookId=paramExtractor.getIntParam(request,"id");
        BookService service=BookServiceImpl.getInstance();
        Optional<Book> book=service.getById(bookId);
        request.setAttribute("book",book.get());
        return new ForwardViewDispatcher(ADMIN_SINGLE_BOOK_VIEW);
    }
}
