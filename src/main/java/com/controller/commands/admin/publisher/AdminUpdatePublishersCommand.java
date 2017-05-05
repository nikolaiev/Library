package com.controller.commands.admin.publisher;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.responce.Dispatcher;
import com.controller.responce.EmptyDispatcher;
import com.controller.responce.RedirectDispatcher;
import com.controller.responce.ValidationErrorViewDispatcher;
import com.controller.validation.PublisherValidator;
import com.model.entity.book.Publisher;
import com.service.PublisherService;
import com.service.impl.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Optional;

import static com.controller.constants.JspPathsConst.ADMIN_PUBLISHER_VIEW;
import static com.controller.constants.UrlsConst.ADMIN_PUBLISHERS;

/**
 * Created by Vladyslav_Nikolaiev on 05-May-17.
 */
public class AdminUpdatePublishersCommand extends CommandWrapper implements Command {
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String publisherTitle=paramExtractor.getStringParam(request,"publisher_title");
        int publisherId=paramExtractor.getIntParam(request,"publisher_id");

        Publisher updatedPublisher=new Publisher(publisherId,publisherTitle);
        PublisherValidator validator=new PublisherValidator();
        PublisherService publisherService= ServiceFactory.getInstance().getPublisherService();



        if(!validator.isValid(updatedPublisher)){
            List<Publisher> publishers=publisherService.getAll();
            request.setAttribute("publishers",publishers);
            return new ValidationErrorViewDispatcher(ADMIN_PUBLISHER_VIEW,validator);
        }

        //persisting object
        publisherService.update(updatedPublisher);

        //TODO replace with success code - LOCALIZATION!
        return new RedirectDispatcher(ADMIN_PUBLISHERS)
                .addGetParam("success_message","Publisher was successfully updated");
    }
}
