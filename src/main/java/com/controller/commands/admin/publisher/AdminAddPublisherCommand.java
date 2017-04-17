package com.controller.commands.admin.publisher;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 03.04.17.
 */
public class AdminAddPublisherCommand extends CommandWrapper implements Command {
    @Override
    protected String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return null;
    }
}
