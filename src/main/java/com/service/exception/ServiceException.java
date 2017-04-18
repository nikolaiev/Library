package com.service.exception;

import com.exception.ApplicationException;

/**
 * Created by vlad on 09.04.17.
 */
public class ServiceException extends ApplicationException {

    public ServiceException(){

    }

    @Override
    public ServiceException addLogMessage(String logMessage) {
        super.addLogMessage(logMessage);
        return this;
    }

    @Override
    public ServiceException addMessageKey(String messageKey) {
        super.addMessageKey(messageKey);
        return this;
    }

    @Override
    public ServiceException addAdditionalMessage(String additionalMessage) {
        super.addAdditionalMessage(additionalMessage);
        return this;
    }
}
