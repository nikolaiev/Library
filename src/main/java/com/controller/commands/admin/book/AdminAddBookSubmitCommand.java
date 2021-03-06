package com.controller.commands.admin.book;

import com.controller.exception.ControllerException;
import com.controller.responce.Dispatcher;
import com.controller.responce.RedirectDispatcher;
import com.controller.responce.ValidationErrorViewDispatcher;
import com.controller.validation.BookValidator;
import com.controller.validation.Validator;
import com.model.entity.book.*;
import com.service.BookService;
import com.service.impl.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.time.Instant;
import java.util.UUID;

import static com.controller.constants.JspPathsConst.ADMIN_ADD_BOOK_VIEW;
import static com.controller.constants.UrlsConst.ADMIN_BOOKS;

/**
 * POST request handler
 * Created by vlad on 24.04.17.
 */
//TODO add validation !expmpl count -1
public class AdminAddBookSubmitCommand extends AbstractAdminBookCommand {
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        /*multipart data processing*/
        Part filePart = request.getPart("book_image");

        if(filePart ==null){
            throw new ControllerException()
                    .addMessageKey("Uploaded file does not exists!");
        }

        String extension = getFileExtension(filePart);
        String uniqueName = UUID.randomUUID().toString().replace("-", "_") + "." + extension;
        File file = new File(request.getServletContext().getInitParameter("upload.location") + uniqueName);

        /*request params*/
        Integer authorId = paramExtractor.getIntParam(request,"author_id");
        Integer publisherId = paramExtractor.getIntParam(request,"publisher_id");
        Integer count = paramExtractor.getIntParam(request,"count");
        String title = paramExtractor.getStringParam(request, "table_title");

        Instant publishInstant= paramExtractor.getInstantParam(request,"publish_date");
        BookGenre genre = paramExtractor.getEnumParamOrNull(request,"genre",BookGenre.class);
        BookLanguage language = paramExtractor.getEnumParamOrNull(request,"language",BookLanguage.class);


        /*creating object to persist*/
        Author author = createIdOnlyAuthor(authorId);
        Publisher publisher = createIdOnlyPublisher(publisherId);

        Book book = new Book.Builder()
                .setImage(uniqueName)
                .setAuthor(author)
                .setGenre(genre)
                .setLanguage(language)
                .setPublisher(publisher)
                .setTitle(title)
                .setCount(count)
                .setInstant(publishInstant)
                .build();

        Validator<Book> validator=new BookValidator();

        /*check for valid data*/
        if(!validator.isValid(book)){
            return new ValidationErrorViewDispatcher(ADMIN_ADD_BOOK_VIEW,validator);
        }

        BookService service = ServiceFactory.getInstance().getBookService();
        service.create(book);

        /*save uploaded image*/
        saveFilePart(filePart, file);

        return new RedirectDispatcher(ADMIN_BOOKS).addGetParam("success", "Book added!");
    }
}
