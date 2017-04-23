package com.controller.commands.admin.book;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.controller.responce.Dispatcher;
import com.controller.responce.RedirectDispatcher;
import com.model.entity.book.*;
import com.service.BookService;
import com.service.impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.UUID;


/**
 * PRG pattern is necessary to prevent double upload
 * Created by vlad on 03.04.17.
 */
public class AdminAddBookCommand extends CommandWrapper implements Command {
    @Override
    protected Dispatcher processExecute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        /*multipart data processing*/
        Part filePart=request.getPart("book_image");
        String extension=getFileExtension(filePart);
        String uniqueName = UUID.randomUUID().toString().replace("-","_")+"."+extension;
        File file = new File(request.getServletContext().getInitParameter("upload.location")+uniqueName);

        /*request params*/
        Integer authorId = Integer.parseInt(request.getParameter("author_id"));
        Integer publisherId = Integer.parseInt(request.getParameter("publisher_id"));
        Integer count = Integer.parseInt(request.getParameter("count"));
        String title = request.getParameter("title");
        LocalDate publishDate = LocalDate.parse(request.getParameter("publish_date"));
        BookGenre genre = BookGenre.getOrNull(request.getParameter("genre"));
        BookLanguage language = BookLanguage.getOrNull(request.getParameter("language"));

        BookService service= BookServiceImpl.getInstance();

        /*creating object to persist*/
        Author author=createIdOnlyAuthor(authorId);
        Publisher publisher=createIdOnlyPublisher(publisherId);

        Book book=new Book.Builder()
                .setImage(uniqueName)
                .setAuthor(author)
                .setGenre(genre)
                .setLanguage(language)
                .setPublisher(publisher)
                .setTitle(title)
                .setCount(count)
                .setDate(publishDate)
                .build();

        service.create(book);

        /*save uploaded image*/
        try(InputStream inputStream=filePart.getInputStream();
            FileOutputStream fileOutputStream=new FileOutputStream(file);
        ){
            byte[] buffer = new byte[100];
            int len;
            while ((len = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, len);
            }
        }

        return new RedirectDispatcher("/admin/books");
    }

    private String getFileExtension(Part filePart) {
        String[] temp=filePart.getSubmittedFileName().split("\\.");
        return temp[temp.length-1];
    }

    private Publisher createIdOnlyPublisher(int publisherId){
        Publisher publisher=new Publisher();
        publisher.setId(publisherId);
        return publisher;
    }

    private Author createIdOnlyAuthor(int authorId){
        Author author=new Author();
        author.setId(authorId);
        return author;
    }
}
