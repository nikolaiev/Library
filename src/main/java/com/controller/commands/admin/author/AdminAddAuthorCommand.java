package com.controller.commands.admin.author;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.responce.Dispatcher;
import com.controller.responce.RedirectDispatcher;
import com.controller.responce.ValidationErrorViewDispatcher;
import com.controller.validation.AuthorValidator;
import com.model.entity.book.Author;
import com.model.entity.book.Publisher;
import com.service.AuthorService;
import com.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static com.controller.constants.JspPathsConst.ADMIN_AUTHOR_VIEW;
import static com.controller.constants.JspPathsConst.ADMIN_PUBLISHER_VIEW;
import static com.controller.constants.UrlsConst.ADMIN_AUTHORS;

/**
 * Created by vlad on 03.04.17.
 */
public class AdminAddAuthorCommand extends CommandWrapper implements Command{
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name=request.getParameter("author_name");
        String soname=request.getParameter("author_soname");
        Author author=new Author(name,soname);
        AuthorValidator validator=new AuthorValidator();


        if(!validator.isValid(author)){
            placeViewData(request);
            return new ValidationErrorViewDispatcher(ADMIN_AUTHOR_VIEW,validator);
        }

        //check possible duplication
        AuthorService authorService= ServiceFactory.getInstance().getAuthorService();
        Optional<Author> authorCopy=authorService.getByNameSoname(name,soname);

        //TODO localization!
        if(authorCopy.isPresent()) {
            placeViewData(request);
            return new ValidationErrorViewDispatcher(ADMIN_AUTHOR_VIEW, "Author already exists");
        }

        authorService.create(author);
        return new RedirectDispatcher(ADMIN_AUTHORS)
                .addGetParam("success_message","Author was successfully added");
    }

    private void placeViewData(HttpServletRequest request){
        AuthorService authorService= ServiceFactory.getInstance().getAuthorService();

        List<Author> authors=authorService.getAll();
        request.setAttribute("authors",authors);
    }
}
