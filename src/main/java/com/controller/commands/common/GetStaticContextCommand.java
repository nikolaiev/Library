package com.controller.commands.common;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by vlad on 16.04.17.
 */
public class GetStaticContextCommand extends CommandWrapper implements Command {
    @Override
    protected String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestURI=request.getRequestURI();
        return "/images/defaultBook.jpg";
    }
}
