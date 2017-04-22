package com.controller.commands.common;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;

import static com.controller.constants.UrlsConst.REDIRECTED;

/**
 * Created by vlad on 16.04.17.
 */
public class GetStaticFileCommand extends CommandWrapper implements Command {
    private final static String DEFAULT_IMAGE="defaultBook.jpg";
    @Override
    protected String processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String filename = URLDecoder.decode(request.getPathInfo().substring(1), "UTF-8");
        File file = new File(request.getServletContext().getInitParameter("upload.location"), filename);

        if(!file.exists())
            file=new File(request.getServletContext().getInitParameter("upload.location"),DEFAULT_IMAGE);

        response.setHeader("Content-Type", request.getServletContext().getMimeType(filename));
        response.setHeader("Content-Length", String.valueOf(file.length()));
        response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
        Files.copy(file.toPath(), response.getOutputStream());

        return REDIRECTED;
    }
}
