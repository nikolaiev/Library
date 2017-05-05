package com.controller.commands.admin.publisher;

import com.controller.commands.Command;
import com.controller.responce.Dispatcher;
import com.controller.responce.ForwardViewDispatcher;
import com.model.entity.book.Publisher;
import com.service.PublisherService;
import com.service.impl.PublisherServiceImpl;
import com.service.impl.ServiceFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.controller.constants.JspPathsConst.ADMIN_PUBLISHER_VIEW;

/**
 * Created by vlad on 10.04.17.
 */
public class AdminPublisherCommand implements Command {
    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PublisherService publisherService= ServiceFactory.getInstance().getPublisherService();
        List<Publisher> publishers=publisherService.getAll();
        request.setAttribute("publishers",publishers);
        return new ForwardViewDispatcher(ADMIN_PUBLISHER_VIEW);
    }
}
