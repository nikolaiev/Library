package com.controller.commands.common;

import com.controller.commands.Command;
import com.model.entity.book.Book;
import com.service.BookService;
import com.service.impl.BookServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Optional;

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

        BookService bookService=new BookServiceImpl();
        List<Book> books= bookService.getBooksByTitle(title.get());
        request.setAttribute("books",books);
        return "WEB-INF/view/user/booksPage.jsp";
    }
}
