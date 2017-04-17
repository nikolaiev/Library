package com.controller.commands.admin.book;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Post command from CRUD
 * Created by vlad on 10.04.17.
 */
public class AdminRemoveBookCommand extends CommandWrapper implements Command {
    @Override
    protected String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //TODO remove book from DB
        return "/admin/books";
    }
}
