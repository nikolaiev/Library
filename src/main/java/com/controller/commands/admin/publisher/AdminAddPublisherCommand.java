package com.controller.commands.admin.publisher;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.responce.Dispatcher;
import com.controller.responce.RedirectDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 03.04.17.
 */
public class AdminAddPublisherCommand extends CommandWrapper implements Command {
    //TODO implement
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return new RedirectDispatcher("/admin/publishers");
    }
}
