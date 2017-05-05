package com.controller.validation;

import com.model.entity.book.Publisher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.controller.constants.RegExConst.LOGIN_REG_EX;
import static com.controller.constants.RegExConst.PUBLISHER_TITLE_REG_EX;

/**
 * Created by Vladyslav_Nikolaiev on 05-May-17.
 */
public class PublisherValidator extends AbstractValidator<Publisher> {
    //TODO replace with code error (Localization)

    private static final String TITLE_IS_NOT_VALID="Publisher title is not valid";

    @Override
    public boolean isValid(Publisher publisher) {
        String publTitle=publisher.getTitle();

        if(!isTitleValid(publTitle)){
            addError(TITLE_IS_NOT_VALID);
            return false;
        }

        return true;
    }

    private boolean isTitleValid(String title){
        Pattern validLoginPattern=Pattern.compile(PUBLISHER_TITLE_REG_EX,Pattern.CASE_INSENSITIVE);
        Matcher matcher = validLoginPattern.matcher(title);
        return matcher.find();
    }
}
