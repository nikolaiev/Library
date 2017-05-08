package com.controller.validation;

import com.model.entity.book.Book;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.controller.constants.RegExConst.BOOK_TITLE_REG_EX;
import static com.controller.constants.RegExConst.NAME_SONAME_REG_EX;

/**
 * Created by vlad on 24.04.17.
 */

public class BookValidator extends AbstractValidator<Book> {
    private static final String BOOK_TITLE_IS_NOT_VALID="Book title is not valid!";
    private static final String BOOK_COUNT_IS_NOT_VALID="Book count is not valid!";

    @Override
    public boolean isValid(Book book) {

        if(!isValidTitle(book)){
            addError(BOOK_TITLE_IS_NOT_VALID);
            return false;
        }

        if(!isValidCount(book)){
            addError(BOOK_COUNT_IS_NOT_VALID);
            return false;
        }

        return true;
    }

    private boolean isValidCount(Book book) {
        int count=book.getCount();
        return count >= 0;
    }

    private boolean isValidTitle(Book book) {
        String title=book.getTitle();
        Pattern validLoginPattern=Pattern.compile(BOOK_TITLE_REG_EX,Pattern.CASE_INSENSITIVE);
        Matcher matcher = validLoginPattern.matcher(title);
        return matcher.find();
    }
}
