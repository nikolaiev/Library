package com.controller.validation;

import com.controller.validation.AbstractValidator;
import com.model.entity.book.Author;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.controller.constants.RegExConst.NAME_SONAME_REG_EX;
import static com.controller.constants.RegExConst.PUBLISHER_TITLE_REG_EX;

/**
 * Created by vlad on 06.05.17.
 */
public class AuthorValidator extends AbstractValidator<Author> {
    private static final String NAME_IS_NOT_VALID="Author's name is not valid";
    private static final String SONAME_IS_NOT_VALID="Authors's soname is not valid";

    @Override
    public boolean isValid(Author author) {
        String name=author.getName();
        String soname=author.getSoname();

        if(!isNameValid(name)){
            addError(NAME_IS_NOT_VALID);
            return false;
        }

        if(!isSonameValid(soname)){
            addError(SONAME_IS_NOT_VALID);
            return false;
        }

        return true;
    }

    private boolean isSonameValid(String soname) {
        return isNameValid(soname);
    }

    private boolean isNameValid(String name) {
        Pattern validLoginPattern=Pattern.compile(NAME_SONAME_REG_EX,Pattern.CASE_INSENSITIVE);
        Matcher matcher = validLoginPattern.matcher(name);
        return matcher.find();
    }
}
