package com.controller.commands.admin.author;

import com.controller.commands.Command;
import com.controller.responce.Dispatcher;
import com.controller.responce.ForwardViewDispatcher;
import com.controller.responce.RedirectDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.controller.constants.JspPathsConst.ADMIN_AUTHOR_VIEW;

/**
 * Created by vlad on 10.04.17.
 */
public class AdminAuthorCommand implements Command{
    @Override
    public Dispatcher execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return new ForwardViewDispatcher(ADMIN_AUTHOR_VIEW);
    }
}
