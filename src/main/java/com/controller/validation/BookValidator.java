package com.controller.validation;

import com.model.entity.book.Book;

/**
 * Created by vlad on 24.04.17.
 */
public class BookValidator extends AbstractValidator<Book> {
    @Override
    public boolean isValid(Book obj) {
        return true;
    }
}
