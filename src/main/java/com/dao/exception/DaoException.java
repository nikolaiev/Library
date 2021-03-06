package com.dao.exception;

import com.exception.ApplicationException;

import java.sql.SQLException;

/**
 * Created by vlad on 17.03.17.
 */
public class DaoException extends ApplicationException {

    public static final String DAO_ERROR = "error.dao";

    /**
     * Creates instance with default message key for view
     */
    public DaoException(){
        super(DAO_ERROR);
    }

    /**
     * Creates instance with default message key for view
     *
     * @param cause throwable instance
     */
    public DaoException(Exception cause) {
        super(DAO_ERROR, cause);
    }

    @Override
    public DaoException addLogMessage(String logMessage) {
        super.addLogMessage(logMessage);
        return this;
    }

    @Override
    public DaoException addMessageKey(String messageKey) {
        super.addMessageKey(messageKey);
        return this;
    }

    @Override
    public DaoException addAdditionalMessage(String additionalMessage) {
        super.addAdditionalMessage(additionalMessage);
        return this;
    }
}
