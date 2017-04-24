package com.controller.commands.admin.book;

import com.controller.commands.Command;
import com.controller.commands.CommandWrapper;
import com.model.entity.book.Author;
import com.model.entity.book.Publisher;

import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Class contains common functions for Admin book oriented management operations
 * Created by vlad on 24.04.17.
 */
public abstract class AbstractAdminBookCommand extends CommandWrapper implements Command {
    protected String getFileExtension(Part filePart) {
        String[] temp=filePart.getSubmittedFileName().split("\\.");
        return temp[temp.length-1];
    }

    protected Publisher createIdOnlyPublisher(int publisherId){
        Publisher publisher=new Publisher();
        publisher.setId(publisherId);
        return publisher;
    }

    protected Author createIdOnlyAuthor(int authorId){
        Author author=new Author();
        author.setId(authorId);
        return author;
    }

    protected void saveFilePart(Part filePart,File file) throws IOException {
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
    }
}
