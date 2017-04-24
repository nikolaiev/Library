package com.controller.commands.admin.book;

import com.controller.exception.ControllerException;
import com.controller.responce.Dispatcher;
import com.controller.responce.RedirectDispatcher;
import com.controller.responce.ValidationErrorViewDispatcher;
import com.model.entity.book.*;
import com.service.BookService;
import com.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import static com.controller.constants.JspPathsConst.ADMIN_EDIT_BOOK_VIEW;
import static com.controller.constants.UrlsConst.ADMIN_BOOKS;

/**
 * Created by vlad on 10.04.17.
 */
public class AdminUpdateBookSubmitCommand extends AbstractAdminBookCommand {
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int bookId=paramExtractor.getIntParam(request,"id");

        Part filePart=request.getPart("book_image");
        File file=null;


        Integer count=paramExtractor.getIntParamOrNull(request,"count");
        String title=paramExtractor.getStringParamOrNull(request,"title");
        Date publishDate=paramExtractor.getDateParamOrNull(request,"publish_date");

        Integer authorId=paramExtractor.getIntParamOrNull(request,"author_id");
        Integer publisherId=paramExtractor.getIntParamOrNull(request,"publisher_id");
        BookGenre genre=paramExtractor.getEnumParamOrNull(request,"genre",BookGenre.class);
        BookLanguage language=paramExtractor.getEnumParamOrNull(request,"language",BookLanguage.class);

        BookService bookService= BookServiceImpl.getInstance();

        /*check book exists*/
        Book book=bookService.getById(bookId).orElseThrow(()-> new ControllerException()
                .addMessageKey("Book does not exist!")
                .addLogMessage("requested book does not exist"));

        //setting new params
        if(count!=null){
            book.setCount(count);
        }

        if(title!=null){
            book.setTitle(title);
        }

        if(publishDate!=null){
            LocalDate localDate = LocalDate.parse( new SimpleDateFormat("yyyy-MM-dd").format(publishDate) );
            book.setDate(localDate);
        }

        if(authorId!=null){
            Author author=createIdOnlyAuthor(authorId);
            book.setAuthor(author);
        }

        if(publisherId!=null){
            Publisher publisher=createIdOnlyPublisher(publisherId);
            book.setPublisher(publisher);
        }

        if(genre!=null){
            book.setGenre(genre);
        }

        if(language!=null){
            book.setLanguage(language);
        }

        if(filePart!=null){
            String extension=getFileExtension(filePart);
            String uniqueName = UUID.randomUUID().toString().replace("-","_")+"."+extension;
            file= new File(request.getServletContext().getInitParameter("upload.location")+uniqueName);
            book.setImage(uniqueName);
        }

        BookService service=BookServiceImpl.getInstance();
        boolean isUpdated=service.updateIfPossible(book);

        if(isUpdated) {
            if (file != null) {
                saveFilePart(filePart, file);
            }

            return new RedirectDispatcher(ADMIN_BOOKS).addGetParam("success","Book updated successfully!");
        }
        else {
            request.setAttribute("book",book);
            return new ValidationErrorViewDispatcher(ADMIN_EDIT_BOOK_VIEW,"Book count value is illegal");
        }
    }



}
