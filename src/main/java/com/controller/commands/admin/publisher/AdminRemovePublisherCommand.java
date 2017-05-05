package com.controller.commands.admin.publisher;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.responce.Dispatcher;
import com.controller.responce.EmptyDispatcher;
import com.controller.responce.RedirectDispatcher;
import com.dao.BookDao;
import com.dao.jdbc.BookDaoImpl;
import com.model.entity.book.Publisher;
import com.service.BookService;
import com.service.PublisherService;
import com.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Service;
import java.io.IOException;

import static com.controller.constants.UrlsConst.ADMIN_PUBLISHERS;

/**
 * Created by vlad on 10.04.17.
 */
public class AdminRemovePublisherCommand extends CommandWrapper implements Command {
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BookService bookService= ServiceFactory.getInstance().getBookService();
        int publisherId=paramExtractor.getIntParam(request,"publisher_id");

        /*find books, which have requested publisher*/
        int relativeBooksCount=bookService.getBooksCountByParams(null,null,null,null,publisherId);

        if(relativeBooksCount>0){
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return new EmptyDispatcher();
        }

        //publisher should be removed
        PublisherService publisherService=ServiceFactory.getInstance().getPublisherService();
        publisherService.deleteById(publisherId);

        //200
        response.setStatus(HttpServletResponse.SC_OK);
        return new EmptyDispatcher();
    }
}
