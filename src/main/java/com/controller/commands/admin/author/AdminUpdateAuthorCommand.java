package com.controller.commands.admin.author;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.responce.Dispatcher;
import com.controller.responce.RedirectDispatcher;
import com.controller.responce.ValidationErrorViewDispatcher;
import com.controller.validation.AuthorValidator;
import com.model.entity.book.Author;
import com.service.AuthorService;
import com.service.impl.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.controller.constants.JspPathsConst.ADMIN_AUTHOR_VIEW;
import static com.controller.constants.JspPathsConst.ADMIN_PUBLISHER_VIEW;
import static com.controller.constants.UrlsConst.ADMIN_AUTHORS;

/**
 * Created by vlad on 05.05.17.
 */
//TODO add validator
public class AdminUpdateAuthorCommand extends CommandWrapper implements Command {
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String name=request.getParameter("author_name");
        String soname=request.getParameter("author_soname");
        int authorId=paramExtractor.getIntParam(request,"author_id");
        Author author=new Author(authorId,name,soname);
        AuthorValidator validator=new AuthorValidator();


        if(!validator.isValid(author)){
            placeViewData(request);
            return new ValidationErrorViewDispatcher(ADMIN_AUTHOR_VIEW,validator);
        }

        AuthorService authorService= ServiceFactory.getInstance().getAuthorService();
        authorService.update(author);

        return new RedirectDispatcher(ADMIN_AUTHORS)
                .addGetParam("success_message","Author was successfully updated");
    }

    private void placeViewData(HttpServletRequest req){
        AuthorService authorService= ServiceFactory.getInstance().getAuthorService();

        List<Author> authors=authorService.getAll();
        req.setAttribute("authors",authors);
    }
}
